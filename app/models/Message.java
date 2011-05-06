package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

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
