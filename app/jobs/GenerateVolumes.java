package jobs;
import java.util.*;

import misc.CommonUtil;
import models.JournalVolume;
import play.jobs.Every;
import play.jobs.Job;

@SuppressWarnings("rawtypes")
@Every("1h")
public class GenerateVolumes extends Job
{
    @SuppressWarnings("deprecation")
    public static JournalVolume createVolume()
    {
        JournalVolume v = new JournalVolume();
        v.publishYear = new Date();
        v.title = "Volume " + (v.publishYear.getYear()+1900);
        return v;
    }

    @Override
	@SuppressWarnings("deprecation")
    public void doJob()
    {
        JournalVolume latestVolume = CommonUtil.getLatestVolume();
        if (latestVolume == null)
        {
            createVolume().save();
            return;
        }
        JournalVolume newVolume = createVolume();
        if (newVolume.publishYear.getYear() > latestVolume.publishYear.getYear())
        {
            newVolume.save();
        }
    }
}
