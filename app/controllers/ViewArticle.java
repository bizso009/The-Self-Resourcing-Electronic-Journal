package controllers;

import models.Article;
import play.data.validation.Required;
import play.mvc.*;

public class ViewArticle extends Controller {

	public static void view(@Required Long id) {
		Article article = Article.findById(id);
		render(article);
	}

	

}
