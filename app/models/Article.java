package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jobs.NotifyKeywordSubscriptions;

import play.db.jpa.Blob;
import play.db.jpa.Model;

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
		new NotifyKeywordSubscriptions(this).now();
	}
	@ManyToOne
	public Submission submission;

	@ManyToMany(mappedBy = "articles")
	public List<User> authors;

	@ManyToMany(mappedBy = "articles")
	public List<Keyword> keywords;

	@OneToMany(mappedBy = "article")
	public List<Review> reviews;

	public Article(String title, Blob articlePdf, Date dateSubmitted,
			Date datePublished, String summary, Long submissionID) {
		super();
		this.title = title;
		this.articlePdf = articlePdf;
		this.dateSubmitted = dateSubmitted;
		this.datePublished = datePublished;
		this.summary = summary;
	}

	

	public Article() {
		//const
	}



	public static List<Article> getArticleByJournal(int journalNumber_id) {
		return Article.find("journalNumber_id=?", journalNumber_id).fetch();
	}

}