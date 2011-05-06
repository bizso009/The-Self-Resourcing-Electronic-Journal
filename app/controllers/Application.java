package controllers;

import play.mvc.Before;
import play.mvc.Controller;


public class Application extends Controller
{

    @SuppressWarnings("boxing")
    @Before
    public static void init(String userRole)
    {
    	boolean conn = Security.isConnected();
    	renderArgs.put("loggedin", conn);
        if (conn)
        {
        	renderArgs.put("user", Security.connected());   
        }
        
        if(userRole != null){
        	if(!conn || !Security.check(userRole)){
        		renderArgs.put("err", "You do not authorization to view this page");
        	}
        }
    }

    public static void index()
    {
        render(renderArgs);
    }
}