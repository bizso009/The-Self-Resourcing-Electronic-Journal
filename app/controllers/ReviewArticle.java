package controllers;

import java.util.Date;
import java.util.List;
import misc.ComplexChecks;
import models.Article;
import models.Mark;
import models.Review;
import models.User;
import models.Conversation;
import models.UserRole;
import play.mvc.*;
import jobs.*;

public class ReviewArticle extends Controller
{
	@Before
	public static void init(){
		Application.init(null);
	}
	
    public static void start(Long articleID)
    {
        //Check authentication
        if (Security.anon())
        {
            return;
        }
        //Check authorization
        if (!Security.check(UserRole.AUTHOR_REVIEWER))
        {
            return;
        }
        
        User u = Security.loggedUser();
        // TODO: Check if the reviewer is assigned to the article...
        Article a = Article.findById(articleID);
        if (!ComplexChecks.isUserReviewingArticle(u, a)) return;
        if (a.submission.isPublished()) return;
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
        r.reviewer = Security.loggedUser();
        r.expertiseLevel = expertiseLevel;
        r.mark = Mark.findById(mark);
        r.contentSummary = contentSummary;
        r.goodPoints = goodPoints;
        r.badPoints = badPoints;
        r.detailedErrorList = detailedErrorList;
        r.authorConversation = Conversation.findById(authorConvID);
        r.editorConversation = Conversation.findById(editorConvID);
        r.dateSubmitted = new Date();
        r.locked = false;
        r.save();
        new AsyncCheckAndPublish(r.article).now();
        render();
    }
}
