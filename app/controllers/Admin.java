package controllers;

import play.mvc.Controller;

public class Admin extends Controller{
	public static void index() {
		render();
	}//FIXME when logged in still displays guest in header
}
