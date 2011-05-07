package controllers;

import play.db.DB;
import play.mvc.*;
import java.util.*;
import java.sql.*;
import models.*;

public class ReviewDashboard extends Controller
{
    public static class DisplayArticle
    {
        public String link;
        public String title;
        public String status;
        public String date;
        public String keywords;
    }

    public static void index()
    {   
    	Application.init("author/reviewer");
        User u = Security.loggedUser();
        if (u == null)
        {
            renderTemplate("/pleaselog.html");
        }
        ArrayList<DisplayArticle> selectedArticles = new ArrayList<DisplayArticle>();
        ArrayList<DisplayArticle> availableArticles = new ArrayList<DisplayArticle>();
        if (u.assignments!=null)
        {
            for (int i=0; i<u.assignments.size(); i++)
            {
                ReviewerAssignment ra = u.assignments.get(i);
                Submission s = ra.submission;
                DisplayArticle selArt = new DisplayArticle();
                selArt.status = "Selected";
                selArt.link = "/viewSubmission/view?subID="+ra.submission.id;
                if (ra.assigned)
                {
                    selArt.status = "Downloaded";
                }
                if (s.articles!=null)
                {
                    Article a = s.articles.get(s.articles.size()-1);
                    selArt.title = a.title;
                    if (a.journalNumber==null)
                    {
                        List<Review> aReviews = a.reviews;
                        if (a.reviews!=null)
                        {
                            for (int j=0; j<aReviews.size(); j++)
                            {
                                Review r = aReviews.get(j);
                                if (r.reviewer.id==u.id)
                                {
                                    selArt.status = "Reviewed";
                                    break;
                                }
                            }
                        }
                    }
                    else
                    {
                        selArt.status = "Published";
                    }
                }
                selectedArticles.add(selArt);
            }
        }
        
        Connection c = DB.getConnection();
        try
        {
            PreparedStatement ps = c.prepareStatement("SELECT A.id FROM `Article` as A WHERE "+
                    "A.journalNumber_id IS NULL "+
                    "AND NOT EXISTS(SELECT id FROM Reviewer_for_submission WHERE submission_id=A.submission_id and reviewer_id=?) "+
                    "AND A.id=(SELECT MAX(AA.id) FROM `Article` as AA Where AA.submission_id=A.submission_id)");
            ps.setLong(1, u.id);
            ResultSet rs = ps.executeQuery();
            rs.beforeFirst();
            while (rs.next())
            {
                DisplayArticle avArt = new DisplayArticle();
                Article a = Article.findById(rs.getLong("id"));
                avArt.title = a.title;
                avArt.link = "/viewSubmission/view?subID="+a.submission.id;
                List<Keyword> keys = a.keywords;
                StringBuilder sb = new StringBuilder();
                if (keys!=null)
                {
                    for (int i=0; i<keys.size(); i++)
                    {
                        if (i>0) sb.append(", ");
                        sb.append(keys.get(i).word);                        
                    }
                }
                avArt.keywords = sb.toString();
                avArt.date = (a.dateSubmitted.getYear()+1900)+"-"+a.dateSubmitted.getDate()+"-"+a.dateSubmitted.getDay();
                availableArticles.add(avArt);
            }
            rs.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        render(selectedArticles,availableArticles);
    }
}
