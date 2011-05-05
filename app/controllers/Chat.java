package controllers;

import play.data.validation.Required;
import play.mvc.*;

public class Chat extends Controller
{

    public static void contents()
    {
        render();
    }

    public static void view()
    {
        render();
    }
    
    public static void post(String msg)
    {
        render(msg);
    }
}
