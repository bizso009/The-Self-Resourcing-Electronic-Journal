package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Reviewer_for_submission")
public class ReviewerAssignment extends Model
{
    @ManyToOne
    public User reviewer;
    
    @ManyToOne
    public Submission submission;
    
    public boolean assigned;
    
    public Date dateAssigned;
}
