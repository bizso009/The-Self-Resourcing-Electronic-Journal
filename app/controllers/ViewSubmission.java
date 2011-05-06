package controllers;

import play.db.jpa.Blob;
import play.mvc.*;
import misc.ComplexChecks;
import models.*;
import java.util.*;

public class ViewSubmission extends Controller
{
    public static void view(Long subID)
    {
        List<Article> articles = Article.find("submission_id = ?", subID).fetch();
        User user = Security.loggedUser();
        boolean canSelectForReview = false;
        boolean canDownloadForReview = false;
        boolean canCancelReview = false;
        boolean canWriteReview = false;
        Submission s = Submission.findById(subID);
        if (articles != null)
        {
            if (articles.size() > 0)
            {
                canSelectForReview = ComplexChecks.canUserSelectArticleForReview(user, articles.get(articles.size() - 1));
                canDownloadForReview = ComplexChecks.isUserReviewingArticle(user, articles.get(articles.size() - 1));
                
                ReviewerAssignment ra = ComplexChecks.getReviewerAssignmentForSubmission(user, s);
                if (ra!=null)
                {
                    if (!ra.assigned)
                    {
                        canCancelReview = true;
                    }
                }
            }
        }
        canWriteReview = canDownloadForReview && (!canSelectForReview)&&(!canCancelReview);
        if (user.reviews!=null)
        {
            for (int i=0; i<user.reviews.size(); i++)
            {
                Review r = user.reviews.get(i);
                //if (r.article.submission.id == )
            }
        }
        render(user, canSelectForReview, canDownloadForReview, canCancelReview, canWriteReview, articles);
    }

    public static void download(Long id)
    {
        Article a = Article.findById(id);
        if (a.journalNumber==null)
            return;
        renderBinary(a.articlePdf.get());
    }

    public static void selectForReview(Long id)
    {
        Submission s = Submission.findById(id);
        if (s == null)
            return;
        User u = Security.loggedUser();
        if ( ComplexChecks.getReviewerAssignmentForSubmission(u, s)!=null)
        {
            return;
        }
        ReviewerAssignment ra = new ReviewerAssignment();
        ra.reviewer = u;
        ra.submission = s;
        ra.save();
        redirect("/reviewDashboard/index");
    }
    
    public static void cancelReview(Long id)
    {
        Submission s = Submission.findById(id);
        if (s == null)
            return;
        User u = Security.loggedUser();
        ReviewerAssignment ra = ComplexChecks.getReviewerAssignmentForSubmission(u, s);        
        ra.delete();
        redirect("/reviewDashboard/index");
    }

    public static void downloadForReview(Long id)
    {
        Article a = Article.findById(id);
        if (a == null)
            return;
        User u = Security.loggedUser();
        if ( !ComplexChecks.isUserReviewingArticle(u, a))
        {
            return;
        }
        ReviewerAssignment ra = ComplexChecks.getReviewerAssignmentForSubmission(u, a.submission);
        ra.assigned = true;
        ra.dateAssigned = new Date();
        ra.save();
        renderBinary(a.articlePdf.get());
    }
}
