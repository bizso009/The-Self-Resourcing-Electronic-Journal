package jobs;
import misc.ComplexChecks;
import models.Article;
import play.jobs.Job;

@SuppressWarnings("rawtypes")
public class AsyncCheckAndPublish extends Job
{
    private Article article;
    
    public AsyncCheckAndPublish(Article a)
    {
        this.article = a;
    }

    @Override
	public void doJob()
    {
        ComplexChecks.publishIfNeeded(article);
    }
    
    
}
