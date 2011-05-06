package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Submission extends Model
{
<<<<<<< HEAD
    public boolean prioratized;
    
    
    @OneToMany(mappedBy="submission",cascade = CascadeType.ALL)
    public List<Article> articles;
=======
    public boolean                  prioratized;
>>>>>>> 481fd032befbd7779e0ffecff3e62f055ff1e80f

    @ManyToOne
    public User                     author;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    public List<Article>            articles;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    public List<ReviewerAssignment> reviewerAssignments;

    public static Submission newSubmission()
    {
        Submission s = new Submission();
        s.save();
        return s;
    }
    
    public static Submission getSubmission(Long submissionID){
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