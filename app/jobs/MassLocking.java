package jobs;

import play.jobs.Every;
import play.jobs.Job;
import misc.ComplexChecks;
import models.*;
import java.util.*;

@Every("1min")
public class MassLocking extends Job
{
    @Override
    public void doJob()
    {
        List<Article> articles = Article.findAll();
        if (articles == null)
            return;
        for (int i=0; i<articles.size(); i++)
        {
            Article article = articles.get(i);
            List<Review> reviews = article.reviews;
            if (reviews != null)
            {
                for (int j=0; j<reviews.size(); j++)
                {
                    Review review = reviews.get(j);
                    if ( !review.locked)
                    {
                        /*if ((review.dateSubmitted.getYear() * 12 + review.dateSubmitted.getDate()) > (1 + article.dateSubmitted.getYear() * 12 + article.dateSubmitted
                                .getDate()))
                        {*/
                            review.locked = true;
                            review.save();
                        /*}*/
                    }
                }
            }
        }
    }
}
