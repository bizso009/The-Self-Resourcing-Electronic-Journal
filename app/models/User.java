package models;

import java.util.List;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import controllers.Secure.Security;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import misc.CommonUtil;
import notifiers.Mails;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import play.Logger;
import play.Play;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;
import play.libs.Mail;

@SuppressWarnings("serial")
@Entity
@Table(name = "Person")
public class User extends Model {

	public String password;

	public boolean subscribed;

	@ManyToOne
	public UserRole role;

	public String firstName;
	public String lastName;
	public String affiliation;

	@Column(unique = true)
	@Required
	public String email;

	@ManyToMany(mappedBy = "authors")
	public List<Article> articles;

	@ManyToMany(mappedBy = "users")
	public Set<Keyword> keywords;
	
	@OneToMany(mappedBy = "reviewer")
	public List<ReviewerAssignment> assignments;

	public User(String email,String firstName, String lastName, String affiliation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.affiliation = affiliation;
		this.email = email;
		this.keywords = new HashSet<Keyword>();
	}

	@OneToMany(mappedBy = "reviewer")
	public List<Review> reviews;
	
	@OneToMany(mappedBy = "author")
	public List<Submission> submissions;

	public User(String email, String firstName, String lastName,
			String affiliation, String password, UserRole role) {
		this(email, firstName, lastName, affiliation);
		setPassword(password);
		this.role = role;
	}

	public User() {
		// const
	}
	
	
	public void sendConfirmationEmail() throws EmailException {
		Mails.welcome(this);
	}

	public static User connect(String email, String password) {
		User user = find("byEmail", email).first();
		if (user != null && user.password != null && user.password.equals(password)){
			return user;
		}
		return null;
	}

	public String getPassword() {
		return Crypto.decryptAES(this.password);
		// return this.password;
	}

	public void setPassword(String password) {
		// this.password = password;
		this.password = Crypto.encryptAES(password);
	}

	public void convertToAuthor() {
		this.password = CommonUtil.randomString();
		this.role = UserRole.findByRole(UserRole.AUTHOR_REVIEWER);
		this.save();
	}
	
	public void makeEditor() {
		this.role = UserRole.findByRole(UserRole.EDITOR);
		this.save();
	}
	
	public void makeAuthor(){
		this.role = UserRole.findByRole(UserRole.AUTHOR_REVIEWER);
		this.save();
	}

	public boolean isAuthor() {
		return this.password != null
				&& (this.role.compareTo(UserRole.authorReviewer()) >=0);
	}

	public void addKeyword(String key) {
		Keyword keyword = Keyword.getKeyword(key);
		this.keywords.add(keyword);
		
	}
}
