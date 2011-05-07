package controllers;

import java.util.List;

import models.Article;
import models.JournalNumber;
import models.JournalVolume;
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
        	if(!session.contains("firstname") || !session.contains("id")){
        		User user = (User)User.find("byEmail", Security.connected()).first();
        		session.put("id", user.id);
        		session.put("firstname", user.firstName);
        	}
        	
        	renderArgs.put("user", session.get("firstname"));
        	renderArgs.put("id", session.get("id"));
        }
        
        if(userRole != null){
        	if(!conn || !Security.check(userRole)){
        		renderArgs.put("err", "AUTH FAIL : You do not authorised to view this page");
        	}
        }
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
}