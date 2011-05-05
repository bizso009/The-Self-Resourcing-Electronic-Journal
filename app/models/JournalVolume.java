package models;

import java.util.Date;
import java.util.List;

public class JournalVolume {
	public int journalVolumeID;
	public Date publishYear;
	public List<JournalNumber> journalNumbers;
	
	public JournalVolume(Date pubDate, List journalNumbers){
		super();
		this.publishYear = pubDate;
		this.journalNumbers = journalNumbers;
	}
}
