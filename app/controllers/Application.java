package controllers;

<<<<<<< HEAD

=======
>>>>>>> 481fd032befbd7779e0ffecff3e62f055ff1e80f
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import models.JournalNumber;
import models.JournalVolume;
import play.mvc.Before;
import play.mvc.Controller;


public class Application extends Controller
{
<<<<<<< HEAD

=======
>>>>>>> 481fd032befbd7779e0ffecff3e62f055ff1e80f
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
    	render();
    }
    
    public static void getJournalVolumes()
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