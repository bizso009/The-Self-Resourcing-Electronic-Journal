package models;
 
import java.util.*;

import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Article extends Model {
 


	public String title;
	public Blob articlePdf;
	public Date dateSubmitted;
	public Date datePublished;
	@Lob
	public String summary;
	public int journalNumberID;
	public int submissionID;
	
    @OneToMany(mappedBy="article", cascade=CascadeType.ALL)
	public List<PersonDetail> authorDetails; 
    
	@ManyToMany(mappedBy="articles",cascade=CascadeType.ALL)
	public List<Keyword> keywords;
	
	public Article(String title, Blob articlePdf, Date dateSubmitted,
			Date datePublished, String summary, int journalNumberID,
			int submissionID) {
		super();
		this.title = title;
		this.articlePdf = articlePdf;
		this.dateSubmitted = dateSubmitted;
		this.datePublished = datePublished;
		this.summary = summary;
		this.journalNumberID = journalNumberID;
		this.submissionID = submissionID;
	}

	public Article() {
	}
 
}