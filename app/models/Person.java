package models;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;
 
@Entity
public class Person extends Model {
 
	
	public String firstName;
	public String lastName;
	public String affiliation;
	
	@Column(unique = true)
	@Required
	public String email;
	
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Article> articles;

	@OneToOne
	public User user;
	
	public Person(String firstName, String lastName, String email,
			String affiliation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.affiliation = affiliation;
	}

	
	public Person() {
		//const
	}
  
}