package controllers;

import play.mvc.Controller;

public class Subscribe extends Controller{
	
	public static void index()
	{
		render();
	}
	
	public static void subscribe(String firstName, String lastName, String email, String keywords, String subscription)
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
			
			render(firstName, lastName, email);
		}
	}

}
