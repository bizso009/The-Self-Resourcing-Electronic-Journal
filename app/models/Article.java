package models;
 
import java.util.*;

import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Article extends Model {
 
	public String title;
	public Blob pdfFileLink;
	public Date dateSubmitted;
	public Date datePublished;
	public String summary;
	public int journalNumberID;
	public int submissionID;
	
    @OneToMany(mappedBy="article", cascade=CascadeType.ALL)
	public List<AuthorDetail> authorDetails; 
	
 
}