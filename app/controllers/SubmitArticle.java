package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Article;
import models.Keyword;
import models.Submission;
import models.User;

import org.apache.commons.mail.EmailException;

import play.Logger;
import play.db.jpa.Blob;
import play.mvc.Before;
import play.mvc.Controller;

public class SubmitArticle extends Controller {

	@Before
	public static void index() {
		Application.init(null);
		render();
	}

	public static void submit(String author, String[] authNumber,
			String[] firstName, String[] lastName, String[] email,
			String[] affiliation, String title, String keywords,
			Blob articlePdf, String summary, Long subID) {

		User mainAuth = null;
		List<User> authors = new ArrayList<User>();
		// iterate over parameters
		for (int i = 0; i < firstName.length; i++) {
			User currPerson = User.find("byEmail", email[i]).first();
			//if main author
			if (authNumber[i].equals(author)) {
				//main author doesn't exist
				if (currPerson == null) {
					User user = new User(email[i],
							firstName[i], lastName[i], affiliation[i]);
					user.convertToAuthor();
					currPerson = user;
				} else {
					if (!currPerson.isAuthor()){
						currPerson.convertToAuthor();
					}
				} 
				mainAuth = currPerson;
			//ordinary person
			} else {
				if (currPerson == null){
					currPerson = new User(firstName[i], lastName[i],
							email[i], affiliation[i]);
					currPerson.save();
				}
			}
			authors.add(currPerson);
		}

		// check submission
		Submission submission = Submission.getSubmission(subID);
		if (submission.author == null) {
			submission.author = mainAuth;
			submission.save();
		}

		// save keywords
		String[] keys = keywords.split(",");
		List<Keyword> articleKeywords = new ArrayList<Keyword>();
		for (String key : keys) {
			Keyword currKey = new Keyword(key);
			articleKeywords.add(currKey);
			currKey.save();
		}

		// save article
		Article article = new Article(title, articlePdf, new Date(), null,
				summary, subID);
		article.keywords = articleKeywords;
		article.authors = authors;
		article.submission = submission;
		article.save();

		//send email
		try {
			mainAuth.sendConfirmationEmail();
		} catch (EmailException e) {
			Logger.error(e, "email exception");
			e.printStackTrace();
		}
		
		render();
	}

}
