package controllers;

<<<<<<< HEAD
=======
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.JournalNumber;
import models.JournalVolume;
>>>>>>> f9f3dd96f9f99f0a0e5ed39dd2934d365b4dea12
import play.mvc.Before;
import play.mvc.Controller;


public class Application extends Controller
{

<<<<<<< HEAD
    @SuppressWarnings("boxing")
    @Before
=======
	@Before
>>>>>>> f9f3dd96f9f99f0a0e5ed39dd2934d365b4dea12
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
    	 List<JournalVolume> volumes = JournalVolume.findAll();
    	 render(volumes);
    }
    
    public static void getJournalNumbers(int volume_id)
    {
    	List<JournalNumber> numbers = JournalNumber.getJournalNumbeByVolume(volume_id);
    	render(numbers);
    }
}