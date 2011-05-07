package jobs;

import java.util.Date;
import java.util.List;

import notifiers.Mails;

import misc.CommonUtil;
import models.JournalVolume;
import models.User;
import play.jobs.Every;
import play.jobs.Job;

public class NotifyJournalSubscriptions extends Job {
	
	private JournalVolume journalvolume;
	
	public NotifyJournalSubscriptions(JournalVolume journalvolume) {
		this.journalvolume = journalvolume;
	}

	public void doJob() {
		List<User> users = User.find("bySubscribed", true).fetch();
		for (User user : users){
			Mails.subscriptionJournal(user, journalvolume);
		}
	}
}
