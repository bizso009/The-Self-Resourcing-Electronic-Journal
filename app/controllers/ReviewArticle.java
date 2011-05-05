package controllers;

import java.util.Date;
import java.util.List;
import models.Article;
import models.Mark;
import models.Review;
import models.User;
import models.Conversation;
import play.mvc.*;

public class ReviewArticle extends Controller
{
    public static void start(Long articleID)
    {
        //Check authentication
        if (Security.anon())
        {
            return;
        }
        //Check authorization
        if (Security.check("reader"))
        {
            return;
        }
        
        User u = User.findById(Long.parseLong(Security.connected()));
        // Check if the reviewer is assigned to the article...
        Conversation authConv = new Conversation().save();
        Long authorConvID = authConv.id;
        Conversation editConv = new Conversation().save();
        Long editorConvID = editConv.id;
        List<Mark> possibleMarks = Mark.findAll();
        render(articleID,possibleMarks,authorConvID,editorConvID);
    }
    
    public static void post(Long articleID, Integer expertiseLevel, Long mark, String contentSummary, 
            String goodPoints, String badPoints, String detailedErrorList,
            Long authorConvID, Long editorConvID)
    {
        Review r = new Review();
        r.article = Article.findById(articleID);
        r.reviewer = User.findById(Long.parseLong(Security.connected()));
        r.expertiseLevel = expertiseLevel;
        r.mark = Mark.findById(mark);
        r.contentSummary = contentSummary;
        r.goodPoints = goodPoints;
        r.badPoints = badPoints;
        r.detailedErrorList = detailedErrorList;
        r.authorConversation = Conversation.findById(authorConvID);
        r.editorConversation = Conversation.findById(editorConvID);
        r.dateSubmitted = new Date();
        r.locked = true;
        r.save();
        render();
    }
}
