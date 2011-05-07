package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;

@Entity
@Table(name = "JournalVolumes")
public class JournalVolume extends Model
{
    public String              title;
    public Date                publishYear;

    @OneToMany(mappedBy = "volume")
    public List<JournalNumber> numbers;
}
