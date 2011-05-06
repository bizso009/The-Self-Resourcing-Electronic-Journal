package controllers;

import java.util.List;

import models.Article;
import models.Person;
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
		int totalEditors = Person.find("role_id=?", 3).fetch().size();
		int totalAuthors = Person.find("role_id=?", 2).fetch().size();
		render(numberUsers, totalArticles, totalEditors, totalAuthors);
	}
}
