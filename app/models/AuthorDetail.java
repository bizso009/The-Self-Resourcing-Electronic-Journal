package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class AuthorDetail extends Model {
 
	public String firstName;
	public String lastName;
	public String email;
	public String affiliation;
	
	@ManyToOne
	public Article article;
  
 
}