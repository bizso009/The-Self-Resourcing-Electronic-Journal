package controllers;

import play.db.jpa.Blob;
import play.mvc.*;
import misc.ComplexChecks;
import models.*;
import java.util.*;

public class ViewSubmission extends Controller
{
    public static class ChatSettings
    {
        public boolean showAuthorConv;
        public int authorRO;
        public boolean showEditorConv;
        public int editorRO;
    }

    public static void view(Long subID)
    {
        List<Article> articles = Article.find("submission_id = ?", subID).fetch();
        User user = Security.loggedUser();
        boolean canSelectForReview = false;
        boolean canDownloadForReview = false;
        boolean canCancelReview = false;
        boolean canWriteReview = false;        
        Submission s = Submission.findById(subID);
        boolean isPublished = s.isPublished();
        if (articles != null)
        {
            if (articles.size() > 0)
            {
                canSelectForReview = (!isPublished)&&ComplexChecks.canUserSelectArticleForReview(user, articles.get(articles.size() - 1));
                canDownloadForReview = ComplexChecks.isUserReviewingArticle(user, articles.get(articles.size() - 1));

                ReviewerAssignment ra = ComplexChecks.getReviewerAssignmentForSubmission(user, s);
                if (ra != null)
                {
                    if ( !ra.assigned)
                    {
                        canCancelReview = true;
                    }
                }
            }
        }
        canWriteReview = canDownloadForReview && ( !canCancelReview);
        if (user.reviews != null)
        {
            for (int i = 0; i < user.reviews.size(); i++ )
            {
                Review r = user.reviews.get(i);
                if (r.article.submission.id == s.id)
                {
                    if (r.article.id == s.articles.get(s.articles.size() - 1).id)
                    {
                        if ((r.locked)&&(!r.rejected))
                        {
                            canWriteReview = false;
                            break;
                        }
                    }
                }
            }
        }
        HashMap<Long, ArrayList<Review>> reviewMap = new HashMap<Long, ArrayList<Review>>();
        HashMap<Long, ChatSettings> chatMap = new HashMap<Long, ChatSettings>();
        boolean isEditor = Security.check(UserRole.EDITOR);
        if (articles != null)
        {
            for (int i = 0; i < articles.size(); i++ )
            {
                Article a = articles.get(i);
                ArrayList<Review> showReviews = new ArrayList<Review>();
                List<Review> dbReviews = a.reviews;
                if (dbReviews != null)
                {
                    for (int j = dbReviews.size() - 1; j >= 0; j-- )
                    {
                        Review r = dbReviews.get(j);
                        ChatSettings cs = new ChatSettings();
                        if (isEditor || isPublished)
                        {
                            if (isEditor || (isPublished && r.locked))
                            {
                                showReviews.add(r);
                                if (isPublished)
                                {
                                    cs.showAuthorConv = true;
                                    cs.authorRO = 1;
                                }
                                if (isEditor)
                                {
                                    cs.showAuthorConv = true;
                                    cs.authorRO = 0;
                                    cs.showEditorConv = true;                                    
                                }
                                chatMap.put(r.id, cs);
                            }
                        }
                        else
                        {
                            if ((r.reviewer.id == user.id)||(r.locked&&(a.submission.author.id == user.id)))
                            {
                                if (r.rejected) break;
                                showReviews.add(r);
                                cs.showAuthorConv = r.locked;
                                if (r.reviewer.id == user.id)
                                {
                                    cs.showAuthorConv = true;
                                    cs.showEditorConv = true;                                    
                                }
                                if (!Security.isConnected())
                                {
                                    cs.authorRO = 1;
                                }
                                chatMap.put(r.id, cs);
                                break;
                            }
                        }
                    }
                }
                reviewMap.put(a.id, showReviews);
            }
        }
        boolean canReject = isEditor&&!isPublished;
        render(user, subID, isPublished, canSelectForReview, canDownloadForReview, canCancelReview, canWriteReview, articles, reviewMap, chatMap, canReject);
    }

    public static void reject(Long id)
    {
        //Rejects a review
        if (!Security.check(UserRole.EDITOR))
            return;
        Review r = Review.findById(id);
        r.rejected = true;
        r.save();
        render();
    }
    
    public static void download(Long id)
    {
        Article a = Article.findById(id);
        if (a.journalNumber == null)
            return;
        renderBinary(a.articlePdf.get());
    }

    public static void selectForReview(Long id)
    {
        Submission s = Submission.findById(id);
        if (s == null)
            return;
        User u = Security.loggedUser();
        if (ComplexChecks.getReviewerAssignmentForSubmission(u, s) != null)
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
        // TODO: Enable later
        // renderBinary(a.articlePdf.get());
    }
}
