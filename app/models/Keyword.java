package models;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;
 
@Entity
public class Keyword extends Model {
 
	

	public String word;
	
	@ManyToMany
	public List<Article> articles;
  
	public Keyword(String word) {
		super();
		this.word = word;
	}

	public Keyword() {
		//const
	}
 
}