package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.*;

public class ComplexChecks
{
    public static boolean checkForPublication(Article a)
    {
        if (a == null)
        {
            return false;
        }
        if (a.journalNumber != null)
        {
            return false;
        }
        List<Article> submissionArticles = Article.find("bySubmission", a.submission).fetch();
        Collections.sort(submissionArticles, new CommonUtil.IDOrdering());
        if (submissionArticles.get(submissionArticles.size() - 1).id != a.id)
        {
            return false;
        }
        List<Review> revs = a.reviews;
        ArrayList<Review> lockedReviews = new ArrayList<Review>();
        int champion = 0;
        int weakPos = 0;
        int weakNeg = 0;
        int detractor = 0;

        for (int i = 0; i < revs.size(); i++ )
        {
            Review r = revs.get(i);
            if (r.locked)
            {
                lockedReviews.add(r);
                if (r.mark.id == 2)
                    champion++ ;
                if (r.mark.id == 1)
                    weakPos++ ;
                if (r.mark.id == -1)
                    weakNeg++ ;
                if (r.mark.id == -2)
                    detractor++ ;
            }
        }
        if (lockedReviews.size() < 3)
        {
            return false;
        }
        if ((detractor >= 2) && (champion == 0))
        {
            return false;
        }
        if ((detractor == 0) && (champion >= 2))
        {
            return true;
        }
        if ((detractor >= 1) && (weakNeg >= 1) && (champion == 0))
        {
            return false;
        }
        if ((champion >= 1) && (weakPos >= 1) && (detractor == 0))
        {
            return true;
        }
        return false;
    }

    public static void publishIfNeeded(Article a)
    {
        if (checkForPublication(a))
        {
            a.journalNumber = CommonUtil.getLatestNumberFromVolume(CommonUtil.getLatestVolume());
            a.datePublished = new Date();
            a.save();
        }
    }

    public static ReviewerAssignment getReviewerAssignmentForSubmission(User u, Submission s)
    {
        if (u.assignments != null)
        {
            for (int i = 0; i < u.assignments.size(); i++ )
            {
                ReviewerAssignment rac = u.assignments.get(i);
                if (rac.submission.id == s.id)
                {
                    return rac;
                }
            }
        }
        return null;
    }

    public static boolean isUserReviewingArticle(User u, Article a)
    {
        return (getReviewerAssignmentForSubmission(u, a.submission) != null);
    }

    public static boolean canUserSelectArticleForReview(User u, Article a)
    {
        // Reviewers and editors only
        if (u.role.name == UserRole.READER)
            return false;
        if (u.assignments != null)
        {
            return ((!isUserReviewingArticle(u, a))&&(u.id!=a.submission.author.id));
        }
        return true;
    }
}
