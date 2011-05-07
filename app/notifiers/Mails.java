package notifiers;

import models.Article;
import models.JournalNumber;
import models.JournalVolume;
import models.User;
import play.Play;
import play.mvc.Mailer;

public class Mails extends Mailer {

	private static String emailFrom = (String) Play.configuration.get("mail.smtp.user");

	public static void welcome(User user) {
		setSubject("Welcome %s", user.firstName);
		setFrom(emailFrom);
		addRecipient(user.email);
		send(user);
	}

	public static void subscriptionArticle(User user, Article article) {
		setSubject("New article to view");
		setFrom(emailFrom);

		addRecipient(user.email);
		send(user,article);
	}
	
	public static void subscriptionJournal(User user, JournalVolume journalVolume){
		setSubject("New journal to view");
		setFrom(emailFrom);

		addRecipient(user.email);
		send(user,journalVolume);
	}
}