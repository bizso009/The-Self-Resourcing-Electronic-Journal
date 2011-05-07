package controllers;

import java.util.List;

import models.Article;
import models.JournalNumber;
import models.JournalVolume;
import models.User;
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
		int totalArticles = Article.findAll().size();
		int totalEditors = User.find("role_id=?", 3).fetch().size();
		int totalAuthors = User.find("role_id=?", 2).fetch().size();
		render(numberUsers, totalArticles, totalEditors, totalAuthors);
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
	
	public static void articles()
	{
		List<Article> articles = Article.findAll();
		render(articles);
	}
	
	public static void users()
	{
		
	}
}
