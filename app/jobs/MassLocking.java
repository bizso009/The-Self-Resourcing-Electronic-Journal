package jobs;

import play.jobs.Every;
import play.jobs.Job;
import misc.ComplexChecks;
import models.*;
import java.util.*;

@Every("5min")
public class MassLocking extends Job
{
    @Override
    public void doJob()
    {
        List<Article> articles = Article.findAll();
        if (articles == null)
            return;
        for (Article article : articles)
        {
            List<Review> reviews = article.reviews;
            if (reviews != null)
            {
                for (Review review : reviews)
                {
                    if ( !review.locked)
                    {
                        if ((review.dateSubmitted.getYear() * 12 + review.dateSubmitted.getDate()) > (1 + article.dateSubmitted.getYear() * 12 + article.dateSubmitted
                                .getDate()))
                        {
                            review.locked = true;
                            review.save();
                        }
                    }
                }
            }
        }
    }
}
