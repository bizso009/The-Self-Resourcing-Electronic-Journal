package jobs;

import play.jobs.Every;
import play.jobs.Job;
import misc.ComplexChecks;
import models.*;
import java.util.*;

@Every("1min")
public class MassPublishChecks extends Job
{
    @Override
    public void doJob()
    {
        List<Article> articles = Article.findAll();
        if (articles==null) return;
        for (Article article: articles)
        {
             ComplexChecks.publishIfNeeded(article);
        }
    }    
}
