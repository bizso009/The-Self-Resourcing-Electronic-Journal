package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Before;

import misc.CommonUtil;
import models.Article;
import models.Keyword;
import models.Person;
import models.Submission;
import models.User;
import models.UserRole;

import play.Logger;
import play.db.jpa.Blob;
import play.libs.Codec;
import play.libs.Mail;
import play.mvc.Controller;

public class SubmitArticle extends Controller {

	@Before
	public static void init() {
		Application.init(null);
	}
	public static void index() {
		Application.init(null);
		render();
	}

	public static void submit(String author, String[] authNumber,
			String[] firstName, String[] lastName, String[] email,
			String[] affiliation, String title, String keywords,
			Blob articlePdf, String summary, Long subID) {

		User mainAuth = null;
		List<Person> authors = new ArrayList<Person>();
		// iterate over parameters
		for (int i = 0; i < firstName.length; i++) {
			Person currPerson = Person.find("byEmail", email[i]).first();
			//if main author
			if (authNumber[i].equals(author)) {
				//main author doesn't exist
				if (currPerson == null) {
					User user = new User(email[i], CommonUtil.randomString(),
							firstName[i], lastName[i], affiliation[i]);
					user.role = UserRole.findByRole(UserRole.AUTHOR_REVIEWER);
					user.save();
					currPerson = user;
					mainAuth = user;
				} else {
					mainAuth = (User) currPerson;
				} 
			//ordinary person
			} else {
				if (currPerson == null){
					currPerson = new Person(firstName[i], lastName[i],
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
