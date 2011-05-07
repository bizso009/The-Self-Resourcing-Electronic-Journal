package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;

@Entity
@Table(name = "JournalNumbers")
public class JournalNumber extends Model
{
    @ManyToOne
    public JournalVolume volume;
    public Date          publishDate;

    @OneToMany(mappedBy = "journalNumber")
    public List<Article> articles;
    
    public static List<JournalNumber> getJournalNumbeByVolume(int volume_id)
    {
    	return JournalNumber.find("volume_id =?", volume_id).fetch();
    }
}
