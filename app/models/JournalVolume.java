package models;

import java.util.*;
import javax.persistence.*;

import jobs.NotifyJournalSubscriptions;
import play.db.jpa.*;

@Entity
@Table(name = "JournalVolumes")
public class JournalVolume extends Model
{
    public String              title;
    public Date                publishYear;

    public void setPublishYear(Date date){
    	this.publishYear = date;
    	new NotifyJournalSubscriptions(this).now();
    }
    @OneToMany(mappedBy = "volume")
    public List<JournalNumber> numbers;
}
