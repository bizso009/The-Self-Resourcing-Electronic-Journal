package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import misc.CommonUtil;
import models.Article;
import models.Keyword;
import models.Person;
import models.User;
import models.UserRole;

import play.Logger;
import play.db.jpa.Blob;
import play.libs.Codec;
import play.libs.Mail;
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
			Blob articlePdf, String summary, Long subID) {
		
		
		//save person details
		for (int i=0; i<firstName.length; i++) {
			
			//main contact
			if (authNumber[i].equals(author)){
			    User.registerUser(
			    		email[i],
			    		CommonUtil.randomString(), 
			    		firstName[i], 
			    		lastName[i],
			    		affiliation[i]);
			} else {
				new Person(
					firstName[i],
					lastName[i],
					email[i],
					affiliation[i]).save();
			}
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
		Article article = new Article(title, articlePdf, new Date(), null, summary, subID);
		article.keywords = articleKeywords;
		article.save();
	
		render();
	}


}
