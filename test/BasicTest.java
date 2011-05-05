import models.Article;
import models.AuthorDetail;

import org.junit.*;
import java.util.*;
import play.test.*;

public class BasicTest extends UnitTest {

    @Test
    public void testCreateArticle() {
        //Fixtures.loadModels("data.yml");
        Article article = new Article();
        article.title = "test";
        article.summary="test";
        article.datePublished=new Date();;
        article.dateSubmitted=new Date();
//        article.pdfFileLink
        article.journalNumberID = 1;
        article.submissionID = 1;
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
