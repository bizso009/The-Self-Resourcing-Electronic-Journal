package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="Reviews")
public class Review extends Model
{
    /*
    @Id
    @Column(name="ID")    
    public Integer id;
    */
    
    @ManyToOne
    public Article article;
    
    @ManyToOne
    public User reviewer;
    
    @ManyToOne
    public Mark mark;
    
    public int expertiseLevel;
    
    @Lob
    public String contentSummary;
    
    @Lob
    public String goodPoints;
    
    @Lob
    public String badPoints;
    
    @Lob
    public String detailedErrorList;
    
    @ManyToOne
    public Conversation authorConversation;
    
    @ManyToOne
    public Conversation editorConversation;
    
    public boolean locked;
    
    public Date dateSubmitted;
}
