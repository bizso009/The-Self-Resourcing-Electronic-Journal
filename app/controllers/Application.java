package controllers;

import play.mvc.Before;
import play.mvc.Controller;


public class Application extends Controller
{
    @SuppressWarnings("boxing")
	@Before
    public static void setConnectedUser()
    {
    	renderArgs.put("loggedin", Security.isConnected());
        if (Security.isConnected())
        {
            renderArgs.put("user", Security.connected());   
        }
    }

    public static void index()
    {
        render();
    }
    
}