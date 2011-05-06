package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Admin extends Controller{
	@Before
	public static void init()
    {
		Application.init();
    }
    
	public static void index() {
		render();
	}//FIXME when logged in still displays guest in header
}
