package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class PersonDetail extends Model {
 
	
	public String firstName;
	public String lastName;
	public String email;
	public String affiliation;
	
	@ManyToOne
	public Article article;

	public PersonDetail(String firstName, String lastName, String email,
			String affiliation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.affiliation = affiliation;
	}

	public PersonDetail() {
		
	}
  
}