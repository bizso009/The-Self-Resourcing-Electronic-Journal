package controllers;

import play.mvc.Controller;

public class Subscribe extends Controller{
	
	public static void index()
	{
		render();
	}
	
	public static void subscribe(String firstName, String lastName, String email, String keywords, String subscription)
	{
		render(subscription);
	}

}
