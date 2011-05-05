package controllers;

import play.mvc.*;

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
	
	public static void submit()
	{
		
	}

}
