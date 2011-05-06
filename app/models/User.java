package models;

import java.util.List;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import controllers.Secure.Security;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
public class User extends Person {

	public String password;

	public boolean subscribed;

	@ManyToOne
	public UserRole role;

	@OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
	public List<ReviewerAssignment> assignments;
	
	@OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
	public List<Review> reviews;

	public User(String email, String password, String firstName,
			String lastName, String affiliation) {
		super(firstName, lastName, email, affiliation);
		setPassword(password);
	}

	public static User registerUser(String email, String password,
			String firstName, String lastName, String affiliation) {
		User user = new User(email, password, firstName, lastName, affiliation);
		user.role = UserRole.findByRole(UserRole.READER);
		user.save();

		try {
			user.sendConfirmationEmail();
		} catch (EmailException e) {
			Logger.error(e, "email exception");
			e.printStackTrace();
		}
		return user;
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
		//return Crypto.decryptAES(this.password);
	    return this.password;
	}

	public void setPassword(String password) {
		this.password = password;//Crypto.encryptAES(password);
	}
	@Override
	public void _delete() {
		UserRole editor = UserRole.findByRole(UserRole.EDITOR);
		if (role.equals(editor) && editor.users.size() == 1) {
			throw new IllegalStateException("Cannot delete last editor in database");
		}
		super._delete();
	}
}
