package models;

import java.util.Date;
import play.data.validation.Required;
import play.db.jpa.Model;
import javax.persistence.*;

@Entity
@Table(name="Reviews")
public class Review extends Model
{
    /*
    @Id
    @Column(name="ID")    
    public Integer id;
    */
    
    @Column(name="ArticleID")
    public Article article;
    
    @Column(name="ReviewerID")
    public User reviewer;
    
    @Column(name="MarkID")
    public Mark mark;
    
    @Required
    @Column(name="ExpertiseLevel")
    public Integer expertiseLevel;
    
    @Lob
    @Column(name="ContentSummary")
    public String contentSummary;
    
    @Lob
    @Column(name="GoodPoints")
    public String goodPoints;
    
    @Lob
    @Column(name="BadPoints")
    public String badPoints;
    
    @Lob
    @Column(name="DetailedErrorList")
    public String detailedErrorList;
    
    @Column(name="AuthorConvID")
    public Integer AuthorConvID;
    
    @Column(name="EditorConvID")
    public Integer EditorConvID;
    
    @Column(name="Locked")
    public Boolean locked;
    
    @Column(name="DateSubmitted")
    public Date dateSubmitted;
}
