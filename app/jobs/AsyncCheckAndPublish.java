package jobs;
import play.jobs.Job;
import misc.ComplexChecks;
import models.*;

public class AsyncCheckAndPublish extends Job
{
    private Article article;
    
    public AsyncCheckAndPublish(Article a)
    {
        this.article = a;
    }

    public void doJob()
    {
        ComplexChecks.publishIfNeeded(article);
    }
    
    
}
