package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
<<<<<<< HEAD
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
 
=======
public class Article extends Model
{

    public String             title;
    public Blob               articlePdf;
    public Date               dateSubmitted;
    public Date               datePublished;
    @Lob
    public String             summary;

    @ManyToOne
    public JournalNumber      journalNumber;

    @ManyToOne
    public Submission         submission;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    public List<PersonDetail> authorDetails;

    @ManyToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    public List<Keyword>      keywords;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    public List<Review>       reviews;

    public Article(String title, Blob articlePdf, Date dateSubmitted, Date datePublished, String summary, long submissionID)
    {
        super();
        this.title = title;
        this.articlePdf = articlePdf;
        this.dateSubmitted = dateSubmitted;
        this.datePublished = datePublished;
        this.summary = summary;
        Submission s = Submission.findById(submissionID);
        if (s == null)
        {
            s = new Submission();
            s.save();
        }
        this.submission = s;
        this.journalNumber = null;
    }

    public Article()
    {
    }

>>>>>>> 0d4915537a6228799bbfb1243815a4692574015b
}