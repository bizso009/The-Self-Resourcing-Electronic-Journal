package models;

import java.util.Date;
import java.util.List;

public class JournalNumber {
	public int journalNumberID;
	public int journalVolumeID;
	public Date publishDate;
	public List<Article> articles;
	
	public JournalNumber(int journalVolumeID, Date publishDate){
		this.journalVolumeID = journalVolumeID;
		this.publishDate = publishDate;
	}
}
