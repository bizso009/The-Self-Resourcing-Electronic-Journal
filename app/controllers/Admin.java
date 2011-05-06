package controllers;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;

@CRUD.For(User.class)
public class Admin extends CRUD{
	@Before
	public static void init()
    {
		Application.init("editor");
    }
    
}
