package jobs;
import play.*;
import play.jobs.*;
import java.util.*;
import misc.CommonUtil;
import models.*;

@SuppressWarnings("rawtypes")
@Every("30min")
public class GenerateNumbers extends Job
{
    public static JournalNumber createNumber()
    {
        JournalNumber n = new JournalNumber();
        n.publishDate = new Date();
        return n;
    }
  
    @SuppressWarnings("deprecation")
    public void doJob()
    {
        JournalVolume latestVolume = CommonUtil.getLatestVolume();
        if (latestVolume == null)
        {
            return;
        }
        
        JournalNumber latestNumber = CommonUtil.getLatestNumberFromVolume(latestVolume);
        JournalNumber newNumber = createNumber();
        newNumber.volume = latestVolume;        
        
        if (latestNumber == null)
        {
            newNumber.save();
        }
        else
        {
            if (latestNumber.publishDate.getMonth()<newNumber.publishDate.getMonth())
            {
                newNumber.save();
            }
        }        
    }
}
