package controllers;

import play.mvc.*;

public class Articles extends Controller {

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
