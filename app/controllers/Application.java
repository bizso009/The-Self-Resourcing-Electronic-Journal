package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Article;
import models.JournalDetails;
import models.JournalNumber;
import models.JournalVolume;
import models.Submission;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;

public class Application extends Controller
{
    @SuppressWarnings("boxing")
    @Before
    public static void init(String userRole)
    {
    	boolean conn = Security.isConnected();
    	renderArgs.put("loggedin", conn);
        if (conn)
        {
        	if(!session.contains("firstname") || !session.contains("userid")){
        		User user = (User)User.find("byEmail", Security.connected()).first();
        		session.put("userid", user.id);
        		session.put("firstname", user.firstName);
        	}
        	
        	renderArgs.put("user", session.get("firstname"));
        	renderArgs.put("id", session.get("userid"));
        }
        
        if(userRole != null){
        	if(!conn || !Security.check(userRole)){
        		renderArgs.put("err", "AUTH FAIL : You do not authorised to view this page");
        	}
        }
        
        JournalDetails journalDetails = JournalDetails.findById((long)1);
        renderArgs.put("journalTitle",journalDetails.title );
        renderArgs.put("journalInfo", journalDetails.info);
        renderArgs.put("journalTemplate", journalDetails.templateLocation.toString());
    }
    public static void index()
    {
    	render();
    }
    
    public static void getJournalVolumes()
    {
    	List<JournalVolume> volumes = JournalVolume.findAll();
   	 	render(volumes);
    }
    
    public static void getJournalNumbers(int volume_id)
    {
    	List<JournalNumber> numbers = JournalNumber.getJournalNumbeByVolume(volume_id);
    	render(numbers);
    }
    
    public static void getArticles(long journalNumber_id)
    {
    	List<Article> articles = Article.getArticleByJournal(journalNumber_id);
    	render(articles);
    }
    
    public static void mysubmissions(){
    	List <Submission> submissions = Submission.find("byAuthor_id", session.get("userid")).fetch();
    	List <Article> articles = new ArrayList<Article>();
    	for(Submission sub: submissions){
    		articles.add((Article)Article.find("bySubmission_id", sub.id).first());
    	}
    	render(articles);
    }
}