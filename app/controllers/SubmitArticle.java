package controllers;

import java.io.File;

import play.mvc.Controller;


public class SubmitArticle extends Controller {

    public static void index() 
    {
    	if(Security.isConnected())
    	{
    		render();
    	}else
    	{
    		redirect("../displayInfo");
    	}
        
    }
    
	public static void displayInfo() 
	{
		render();		
	}
	
	public static void submit(String[] author, String[] firstName, String[] lastName, String[] email, String[] affil)
	{
		
	}

}
