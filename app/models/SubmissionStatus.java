package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class SubmissionStatus extends Model {
 
	public static enum Status {
		SELECTED, DOWNLOADED, REVIEW 
	}
 
}