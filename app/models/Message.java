package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Messages")
public class Message extends Model
{
    @ManyToOne
    public Conversation conversation;
    
    @Lob
    public String contents;
    
    @ManyToOne
    public User user;
    
    public Date timeSent;
}
