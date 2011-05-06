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

    @OneToMany(mappedBy = "journalNumber", cascade = CascadeType.ALL)
    public List<Article> articles;
}
