package controllers;

import java.util.List;

import models.User;
import play.mvc.Before;

@CRUD.For(User.class)
public class Admin extends CRUD{
	@Before
	public static void init()
    {
		Application.init(null);
    }
	
	public static void index()
	{
		List<User> users = User.findAll();
		render(users);
	}
}
