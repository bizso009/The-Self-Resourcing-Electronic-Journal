package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Article extends Model {
 
	public String title;
	public String pdfFileLink;
	public Date dateSubmitted;
	public Date datePublished;
	public String authorDetails;
	public String summary;
	public int journalNumberID;
	public int submissionID;
	
 
}