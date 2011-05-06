package controllers;

import play.mvc.Before;
import play.mvc.Controller;


public class Application extends Controller
{
<<<<<<< HEAD
    @SuppressWarnings("boxing")
	@Before
    public static void setConnectedUser()
=======
    @Before
    public static void init(String userRole)
>>>>>>> 3f61d287d86cb816570ef17cb576a03d003664c6
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