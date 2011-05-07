package jobs;

import java.util.Date;
import java.util.List;
import java.util.Set;

import notifiers.Mails;

import misc.CommonUtil;
import models.Article;
import models.JournalVolume;
import models.Keyword;
import models.User;
import play.jobs.Every;
import play.jobs.Job;

public class NotifyKeywordSubscriptions extends Job
{

	private Article article;
    public NotifyKeywordSubscriptions(Article article) {
		this.article = article;
	}

	@Override
    public void doJob()
    {
		List<Keyword> keys = article.keywords;
        for (Keyword key : keys){
        	Set<User> users = key.users;
        	for (User user : users){
        		Mails.subscriptionArticle(user,article);
        	}
        }
    }
}
