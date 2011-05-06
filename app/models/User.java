package models;

<<<<<<< HEAD
import java.util.List;
=======
import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import controllers.Secure.Security;
import java.util.*;
>>>>>>> 3f61d287d86cb816570ef17cb576a03d003664c6

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
@Table(name = "Users")
<<<<<<< HEAD
public class User extends Person {

	public String password;

	public boolean subscribed;

	// /**
	// * @return the firstName
	// */
	// public String getFirstName() {
	// return this.personDetail.firstName;
	// }
	// /**
	// * @return the lastName
	// */
	// public String getLastName() {
	// return this.personDetail.lastName;
	// }
	// /**
	// * @return the affiliation
	// */
	// public String getAffiliation() {
	// return this.personDetail.affiliation;
	// }
	// /**
	// * @param firstName the firstName to set
	// */
	// public void setFirstName(String firstName) {
	// this.personDetail.firstName = firstName;
	// }
	// /**
	// * @param lastName the lastName to set
	// */
	// public void setLastName(String lastName) {
	// this.personDetail.lastName = lastName;
	// }
	// /**
	// * @param affiliation the affiliation to set
	// */
	// public void setAffiliation(String affiliation) {
	// this.personDetail.affiliation = affiliation;
	// }
	//
	// @Required
	// @OneToOne
	// public Person personDetail;
	//
	@ManyToOne
	public UserRole role;

	@OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
	public List<ReviewerAssignment> assignments;

	public User(String email, String password, String firstName,
			String lastName, String affiliation) {
		super(firstName, lastName, email, affiliation);
		this.password = password;
		// this.personDetail = new Person();
		// setEmail(email);
		// setPassword(password);
		// setFirstName(firstName);
		// setLastName(lastName);
		// setAffiliation(affiliation);
		// this.personDetail.save();

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
		return Crypto.decryptAES(this.password);
	}

	public void setPassword(String password) {
		this.password = Crypto.encryptAES(password);
	}

	// public String getEmail(){
	// return personDetail.email;
	// }
	// public void setEmail(String email){
	// this.personDetail.email = email;
	// }

	@Override
	public void _delete() {
		super._delete();
		// TODO check for last editor
	}
}
=======
public class User extends Model
{
   
    public String passwordHash;
    public boolean subscribed;

    @ManyToOne
    public PersonDetail personDetail;
    
    @ManyToOne
    public UserRole role;
    
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    public List<ReviewerAssignment> assignments;
}
>>>>>>> 3f61d287d86cb816570ef17cb576a03d003664c6
