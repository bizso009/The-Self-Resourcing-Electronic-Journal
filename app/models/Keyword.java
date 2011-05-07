package models;
 
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.data.validation.Required;
import play.db.jpa.Model;
 
@Entity
public class Keyword extends Model {
 
	
	@Required
	@Column(unique = true)
	public String word;
	
	@ManyToMany(mappedBy = "keywords")
	public List<Article> articles;
  
	@ManyToMany
	public Set<User> users;
	
	public Keyword(String word) {
		super();
		this.word = word;
	}

	public static Keyword getKeyword(String word){
		Keyword keyword = find("byWord",word).first();
		if (keyword == null){
			keyword = new Keyword(word);
			keyword.save();
		}
		return keyword;
	}
	public Keyword() {
		//const
	}
 
}