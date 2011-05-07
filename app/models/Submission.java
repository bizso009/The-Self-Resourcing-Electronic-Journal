package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Submission extends Model {
	public boolean prioratized;

	@OneToMany(mappedBy = "submission")
	public List<Article> articles;

	@ManyToOne
	public User author;

	@OneToMany(mappedBy = "submission")
	public List<ReviewerAssignment> reviewerAssignments;

	public static Submission newSubmission() {
		Submission s = new Submission();
		s.save();
		return s;
	}

	public static Submission getSubmission(Long submissionID) {
		if (submissionID == null) {
			return Submission.newSubmission();
		} else {
			Submission s = Submission.findById(submissionID);
			if (s == null) {
				return Submission.newSubmission();
			} else {
				return s;
			}
		}
	}
}