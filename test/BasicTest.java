import models.Article;
import models.AuthorDetail;
import models.Keyword;

import org.junit.*;
import java.util.*;
import play.test.*;

public class BasicTest extends UnitTest {

    @Test
    public void testCreateArticle() {
        //Fixtures.loadModels("data.yml");
       
//    	 Keyword key1 = new Keyword();
//         key1.word = "test";
//         key1.save();
//         Keyword key2 = new Keyword();
//         key2.word = "test";
//         key2.save();
//         
//         List<Keyword> keywords = new ArrayList<Keyword>();
//         keywords.add(key1);
//         keywords.add(key2);
//         
         
    	
    	Article article = new Article();
        article.title = "test";
        article.summary="test";
        article.datePublished=new Date();;
        article.dateSubmitted=new Date();
//        article.pdfFileLink
        article.journalNumberID = 1;
        article.submissionID = 1;
//        article.keywords = keywords;
        article.save();
        assertTrue(Article.count() > 1);
        
        AuthorDetail ad1 = new AuthorDetail();
        ad1.firstName = "test";
        ad1.article = article;
        ad1.save();
        
        AuthorDetail ad2 = new AuthorDetail();
        ad2.firstName = "test";
        ad2.article = article;
        ad2.save();
        
       

        
        assertTrue(AuthorDetail.count() > 1);

    }

}
