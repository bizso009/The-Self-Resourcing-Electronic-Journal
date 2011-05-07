package notifiers;

import models.Article;
import models.JournalNumber;
import models.JournalVolume;
import models.User;
import play.Play;
import play.mvc.Mailer;

public class Mails extends Mailer
{

    private static String emailFrom = (String)Play.configuration.get("mail.smtp.user");

    public static void welcome(User user)
    {
        try
        {
            setSubject("Welcome %s", user.firstName);
            setFrom(emailFrom);
            addRecipient(user.email);
            send(user);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void subscriptionArticle(User user, Article article)
    {
        try
        {
            setSubject("New article to view");
            setFrom(emailFrom);

            addRecipient(user.email);
            send(user, article);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void subscriptionJournal(User user, JournalVolume journalVolume)
    {
        try
        {
            setSubject("New journal to view");
            setFrom(emailFrom);

            addRecipient(user.email);
            send(user, journalVolume);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}