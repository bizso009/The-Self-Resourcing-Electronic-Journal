package controllers;

import play.db.jpa.Blob;
import play.mvc.*;
import models.*;
import java.util.*;

public class ViewSubmission extends Controller
{
    public static void view(Long subID)
    {
        List<Article> articles = Article.find("submission_id = ?", subID).fetch();
        render(articles);
    }
    
    public static void download(Blob pdf)
    {
        renderBinary(pdf.get());
    }
}
