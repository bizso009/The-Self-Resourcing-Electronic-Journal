package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
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

}