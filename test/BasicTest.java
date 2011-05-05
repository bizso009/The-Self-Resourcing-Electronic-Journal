import models.Article;
import models.PersonDetail;
import models.Keyword;

import org.junit.*;
import java.util.*;
import play.test.*;

public class BasicTest extends UnitTest {

	
<<<<<<< HEAD
	
=======
	public void testGetFile(){
		Article.findById(startPlayBeforeTests);
	}
>>>>>>> 66adabad87b4039bda916ef36506249c21cfae32
    @Test
    public void testCreateArticle() {
        //Fixtures.loadModels("data.yml");
       
    	 Keyword key1 = new Keyword();
         key1.word = "test";
         key1.save();
         Keyword key2 = new Keyword();
         key2.word = "test";
         key2.save();
         
         List<Keyword> keywords = new ArrayList<Keyword>();
         keywords.add(key1);
         keywords.add(key2);
         
         
    	
    	Article article = new Article();
        article.title = "test";
        article.summary="test";
        article.datePublished=new Date();;
        article.dateSubmitted=new Date();
//        article.pdfFileLink
        article.journalNumberID = 1;
        article.submissionID = 1;
        article.keywords = keywords;
        article.save();
        assertTrue(Article.count() > 1);
        
        PersonDetail ad1 = new PersonDetail();
        ad1.firstName = "test";
        ad1.article = article;
        ad1.save();
        
        PersonDetail ad2 = new PersonDetail();
        ad2.firstName = "test";
        ad2.article = article;
        ad2.save();
        
       

        
        assertTrue(PersonDetail.count() > 1);

    }

}
