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
import models.PersonDetail;
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
		
		User user = null;
		String pass = null;
		//save person details
		for (int i=0; i<firstName.length; i++) {
			PersonDetail personDetail = new PersonDetail(
					firstName[i],
					lastName[i],
					email[i],
					affiliation[i]);
			personDetail.save();
			
			//is main author
			if (authNumber[i].equals(author)){
				user = new User();
				user.passwordHash = Codec.hexMD5(pass = CommonUtil.randomString()); 
				user.personDetail = personDetail;
				user.role = UserRole.findByRole(UserRole.Role.READER);
				user.save();
				//TODO check for existing user
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
		//TODO check for submission
		article.keywords = articleKeywords;
		article.save();
		
		try {
			sendEmail(user.id.toString(), pass, user.personDetail.email);
		} catch (EmailException e) {
			validation.email(user.personDetail.email);
			Logger.error(e, "email exception");
		}
		render();
	}

	public static void sendEmail(String username, String pass, String toMail) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setFrom("noreply@journal.org");
		email.addTo(toMail);
		email.setSubject("Registration");
		email.setMsg("Self-Resourcing-Electronic-Journal\n" +
						"Your username: "+username+"\n " +
						"Your password: "+pass);
		Mail.send(email); 
	}

}
