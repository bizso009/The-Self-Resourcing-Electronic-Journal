package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

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
