package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class Keyword extends Model {
 
	public String word;
	
//	@ManyToMany(cascade=CascadeType.ALL)
//	public List<Article> articles;
//  
// 
}