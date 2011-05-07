package controllers;

import java.util.List;

import models.Article;
import models.JournalDetails;
import models.JournalNumber;
import models.JournalVolume;
import models.User;
import play.db.jpa.Blob;
import play.mvc.Before;
import play.mvc.Controller;

public class Admin extends Controller{
	@Before
	public static void init()
    {
		Application.init("editor");
    }
	
	public static void index()
	{
		
		int numberUsers = User.findAll().size();
		int publishedArticles = Article.find("JournalNumber_id is null").fetch().size();
		int unpublishedArticles = Article.find("JournalNumber_id is not null").fetch().size();
		int totalEditors = User.find("role_id=?", 3).fetch().size();
		int totalAuthors = User.find("role_id=?", 2).fetch().size();
		render(numberUsers, publishedArticles, unpublishedArticles, totalEditors, totalAuthors);
	}
	
	public static void volumes()
	{
		List<JournalVolume> volumes = JournalVolume.findAll();
		render(volumes);
	}
	
	public static void numbers()
	{
		List<JournalNumber> numbers = JournalNumber.findAll();
		render(numbers);
	}
	
	public static void publishedarticles()
	{
		List<Article> articles = Article.find("journalNumber_id is not null").fetch();
		render(articles);
	}
	
	public static void unpublishedarticles(long pubid)
	{
		if(pubid != 0){
			Article art = Article.findById(pubid);
			art.publish();
		}
		List<Article> articles = Article.find("journalNumber_id is null").fetch();
		render(articles);
	}
	
	public static void users(String act, long id)
	{
		if(act != null && id != 0){
			User user = User.findById(id);
			if(act.equals("e")){
				user.makeEditor();
			}else if(act.equals("a")){
				user.makeAuthor();
			}
			user.save();
		}
		
		
		List<User> userslist = User.findAll();
		render(renderArgs, userslist);
	}
	
	public static void site(){
		render();
	}
	
	public static void updateSettings(String journalTitle, String journalInfo, Blob journalTemplate){
		JournalDetails journalDetails = JournalDetails.findById((long)1);
		if(journalDetails != null && journalInfo != null && journalTemplate != null){
			journalDetails.title = journalTitle;
			journalDetails.info = journalInfo;
			journalDetails.templateLocation = journalTemplate;
			journalDetails.save();
			site();
		}
	}
}
