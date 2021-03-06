package controllers;

import java.util.List;
import java.util.Set;

import notifiers.Mails;
import models.Article;
import models.JournalNumber;
import models.Keyword;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;

public class Subscribe extends Controller{
	
	@Before
	public static void init(){
		Application.init(null);
	}
	
	public static void index()
	{
		render();
	}
	
	public static void subscribe(String firstName, String lastName, String email, String keywords, boolean subscription)
	{
		validation.required(firstName);
		validation.required(lastName);
		validation.required(email);
		validation.email(email);
		validation.required(keywords);
		
		if(validation.hasErrors())
		{
			params.flash();
			validation.keep();
			index();
		}else
		{
			
			User user = User.find("byEmail", email).first();
			if (user == null){
				user = new User(email,firstName,lastName,null);
			}
			for (String key: keywords.split(",")){
				user.addKeyword(key);
			}
			user.subscribed = subscription;
			user.save();
			
			//TEST
//			User u2 = User.findById(user.id);
//			System.out.println("SIZES SIZE SZIES::::: "+u2.keywords.size());
//			Article article = Article.<Article>findById(44L);
//			article.journalNumber = new JournalNumber().save();;
//			
////			
////			Keyword key = Keyword.getKeyword("test");
////			article.keywords.add(key);
////			article.save();
////			article = Article.<Article>findAll().get(0);
////			article.save();
////			
			render(user);
		}
	}
	public static void unsubscribe(Long userId){
		User user = User.findById(userId);
		if (user != null){
			user.subscribed = false;
			user.keywords.clear();
			user.save();
		}
	}

}
