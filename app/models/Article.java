package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
<<<<<<< HEAD
public class Article extends Model {

	public String title;
	public Blob articlePdf;
	public Date dateSubmitted;
	public Date datePublished;
	@Lob
	public String summary;

	@ManyToOne
	public JournalNumber journalNumber;

	@ManyToOne
	public Submission submission;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	public List<Person> authorDetails;

	@ManyToMany(mappedBy = "articles", cascade = CascadeType.ALL)
	public List<Keyword> keywords;

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	public List<Review> reviews;

	public Article(String title, Blob articlePdf, Date dateSubmitted,
			Date datePublished, String summary, Long submissionID) {
		super();
		this.title = title;
		this.articlePdf = articlePdf;
		this.dateSubmitted = dateSubmitted;
		this.datePublished = datePublished;
		this.summary = summary;

		// check for submission
		initSubmission(submissionID);

	}

	private void initSubmission(Long submissionID) {
		if (submissionID == null) {
			this.submission = Submission.newSubmission();
		} else {
			Submission s = Submission.findById(submissionID);
			if (s == null) {
				this.submission = Submission.newSubmission();
			} else {
				this.submission = s;
			}
		}
	}

	public Article() {
		//const
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
        this.title = title;
        this.articlePdf = articlePdf;
        this.dateSubmitted = dateSubmitted;
        this.datePublished = datePublished;
        this.summary = summary;
        try
        {
            Submission s = Submission.findById(submissionID);
            if (s == null)
            {
                s = new Submission();
                s.save();
            }
            this.submission = s;
        }
        catch (NullPointerException ex)
        {

        }        
        this.journalNumber = null;
    }

    public Article()
    {
    }
>>>>>>> 3f61d287d86cb816570ef17cb576a03d003664c6

}