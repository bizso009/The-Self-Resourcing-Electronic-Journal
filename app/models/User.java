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

	@ManyToMany
	public List<Article> articles;

	@OneToMany(mappedBy = "reviewer")
	public List<ReviewerAssignment> assignments;

	public User(String email,String firstName, String lastName, String affiliation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.affiliation = affiliation;
		this.email = email;
	}

	@OneToMany(mappedBy = "reviewer")
	public List<Review> reviews;

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
		String emailFrom = (String) Play.configuration.get("mail.smtp.user");

		SimpleEmail email = new SimpleEmail();
		email.setFrom(emailFrom);
		email.addTo(this.email);
		email.setSubject("Registration");
		email.setMsg("Self-Resourcing-Electronic-Journal\n" + "Your username: "
				+ this.email + "\n " + "Your password: " + this.password);
		Mail.send(email);
	}

	public static User connect(String email, String password) {
		return find("byEmailAndPassword", email, password).first();
	}

	public String getPassword() {
		return Crypto.decryptAES(this.password);
		// return this.password;
	}

	public void setPassword(String password) {
		// this.password = password;
		this.password = Crypto.encryptAES(password);
	}

	@Override
	public void _delete() {
		UserRole editor = UserRole.findByRole(UserRole.EDITOR);
		if (role.equals(editor) && editor.users.size() == 1) {
			throw new IllegalStateException(
					"Cannot delete last editor in database");
		}
		super._delete();
	}

	public void convertToAuthor() {
		this.password = CommonUtil.randomString();
		this.role = UserRole.findByRole(UserRole.AUTHOR_REVIEWER);
		this.save();
	}

	public boolean isAuthor() {
		return this.password != null
				&& this.role.equals(UserRole.authorReviewer());
	}
}
