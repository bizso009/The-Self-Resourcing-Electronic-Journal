package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import models.Article;
import models.Keyword;
import models.PersonDetail;

import play.db.jpa.Blob;
import play.mvc.Controller;

public class SubmitArticle extends Controller {

	public static void index() {
		if (Security.isConnected()) {
			render();
		} else {
			redirect("../displayInfo");
		}

	}

	public static void displayInfo() {
		render();
	}

	public static void submit(String author, String[] authNumber,
			String[] firstName, String[] lastName, String[] email,
			String[] affiliation, String title, String keywords,
			Blob articlePdf, String summary) {
		
		//save person details
		for (int i=0; i<firstName.length; i++) {
			new PersonDetail(
					firstName[i],
					lastName[i],
					email[i],
					affiliation[i]).save();
			
		}
		
		//save keywords
		String[] keys = keywords.split(",");
		List<Keyword> articleKeywords = new ArrayList<Keyword>();
		for (String key : keys){
			Keyword currKey = new Keyword(key);
			articleKeywords.add(currKey);
			currKey.save();
		}
		
		//save article
		Article article = new Article(title, articlePdf, new Date(), null, summary, 1, 1);
		article.keywords = articleKeywords;
		article.save();
		
	}

}
