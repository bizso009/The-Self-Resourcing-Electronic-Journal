import models.Article;

import org.junit.*;
import java.util.*;
import play.test.*;

public class BasicTest extends UnitTest {

    @Test
    public void testCreateArticle() {
        //Fixtures.loadModels("data.yml");
        Article article = new Article();
        article.title = "test";
        article.journalNumberID = 1;
        article.submissionID = 1;
        article.save();
        
        assertTrue(Article.count() > 1);
    }

}
