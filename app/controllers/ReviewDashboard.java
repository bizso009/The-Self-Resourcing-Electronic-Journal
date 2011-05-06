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
        public String title;
        public String status;
        public String date;
        public String keywords;
    }
    
    public static void index()
    {        
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
        //DB.getConnection();
        render(selectedArticles);
    }
}
