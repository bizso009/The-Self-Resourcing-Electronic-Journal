package misc;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import play.db.jpa.Model;
import models.*;

public class CommonUtil
{
    public static String sanitizeForHTML(String data)
    {
        return data.trim().replaceAll("&", "&amp;").replaceAll("\r", "\n").replaceAll("\n\n", "\n").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;").replaceAll("\n", "<br/>").trim();
    }

    public static String randomString()
    {
        return UUID.randomUUID().toString().split("-")[0];

    }
    
    public static JournalVolume getLatestVolume()
    {
        List<JournalVolume> volumes = JournalVolume.findAll();
        if (volumes.size()==0)
        {
            return null;
        }
        else
        {
            CommonUtil.JVYearOrdering jvyear = new CommonUtil.JVYearOrdering();
            Collections.sort(volumes, jvyear);
            return volumes.get(volumes.size()-1);
        }
    }
    
    public static JournalNumber getLatestNumberFromVolume(JournalVolume volume)
    {
        if (volume == null)
        {
            return null;
        }
        
        if (volume.numbers == null)
        {
            return null;
        }
        else
        {
            List<JournalNumber> numbers = volume.numbers;
            Collections.sort(numbers, new JNMonthOrdering());
            return numbers.get(numbers.size()-1);
        }
    }
    
    public static class IDOrdering implements Comparator<Model>
    {
        @Override
        public int compare(Model o1, Model o2)
        {
            if (o1.id > o2.id)
                return 1;
            if (o1.id < o2.id)
                return -1;
            return 0;
        }
    }
    
    public static class JVYearOrdering implements Comparator<JournalVolume>
    {
        @SuppressWarnings("deprecation")
        @Override
        public int compare(JournalVolume o1, JournalVolume o2)
        {
            if (o1.publishYear.getYear() > o2.publishYear.getYear())
                return 1;
            if (o1.publishYear.getYear() < o2.publishYear.getYear())
                return -1;
            if (o1.publishYear.getYear() == o2.publishYear.getYear())
            {
                return new IDOrdering().compare(o1, o2);
            }
            return 0;
        }
    }
    
    public static class JNMonthOrdering implements Comparator<JournalNumber>
    {
        @SuppressWarnings("deprecation")
        @Override
        public int compare(JournalNumber o1, JournalNumber o2)
        {
            if (o1.publishDate.getMonth() > o2.publishDate.getMonth())
                return 1;
            if (o1.publishDate.getMonth() < o2.publishDate.getMonth())
                return -1;
            if (o1.publishDate.getMonth() == o2.publishDate.getMonth())
            {
                return new IDOrdering().compare(o1, o2);
            }
            return 0;
        }        
    }
}
