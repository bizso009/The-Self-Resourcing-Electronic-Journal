package models;
 
import javax.persistence.Entity;

import play.db.jpa.Model;
 
@Entity
public class SubmissionStatus extends Model {
 
	public String name;
	
	
	
	public static final String SELECTED = "selected";
	public static final String DOWNLOADED = "downloaded";
	public static final String REVIEWED = "reviewed";
	
 
}