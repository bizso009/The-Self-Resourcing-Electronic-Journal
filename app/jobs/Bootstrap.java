package jobs;
import play.*;
import play.jobs.*;
import play.test.*;
import models.*;

@SuppressWarnings("rawtypes")
@OnApplicationStart
public class Bootstrap extends Job
{

    public void doJob()
    {
        new GenerateVolumes().doJob();
        new GenerateNumbers().doJob();
        new MassLocking().now();
        new MassPublishChecks().now();
        /*	Article article = new Article();
            article.title = "test";
            article.journalNumberID = 1;
            article.submissionID = 1;
            article.save();
            
            AuthorDetail ad1 = new AuthorDetail();
            ad1.firstName = "test";
            ad1.article = article;
            ad1.save();
        }*/
    }
}