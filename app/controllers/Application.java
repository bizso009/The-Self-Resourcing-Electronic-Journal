package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Application extends Controller
{
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
        		renderArgs.put("emsg", "You do not authorization to view this page");
        		index();
        	}
        }
    }

    public static void index()
    {
        render();
    }
}