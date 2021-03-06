package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import notifiers.Mails;

import jobs.NotifyKeywordSubscriptions;
import misc.CommonUtil;
import jobs.NotifyKeywordSubscriptions;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import jobs.*;

@Entity
public class Article extends Model {

	public String title;
	public Blob articlePdf;
	public Date dateSubmitted;
	public Date datePublished;
	@Lob
	public String summary;

	@ManyToOne
	public JournalNumber journalNumber;

	public void setJournalNumber(JournalNumber journalNumber){
		this.journalNumber = journalNumber;
		
		//LaziyIntialization exception
		
		//
		new NotifyKeywordSubscriptions(this).now();
	}
	@ManyToOne
	public Submission submission;

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Person_Article")
	public List<User> authors;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "Keyword_Article")
	public List<Keyword> keywords;

	@OneToMany(mappedBy = "article")
	public List<Review> reviews;

	public Article(String title, Blob articlePdf, Date dateSubmitted,
			Date datePublished, String summary, Long submissionID) {
		this.title = title;
		this.articlePdf = articlePdf;
		this.dateSubmitted = dateSubmitted;
		this.datePublished = datePublished;
		this.summary = summary;
		this.keywords = new ArrayList<Keyword>();
	}
	
	public Article() {
		//const
	}

	public static List<Article> getArticleByJournal(long journalNumber_id) {
		JournalNumber number = JournalNumber.findById(journalNumber_id);
		List<Article> articles = number.articles;
		return articles;
	}
	
	public void publish(){
		 this.journalNumber = CommonUtil.getLatestNumberFromVolume(CommonUtil.getLatestVolume());
         this.datePublished = new Date();
         this.save();
	}

}