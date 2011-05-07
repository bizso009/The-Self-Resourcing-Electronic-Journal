import models.Article;
import models.Keyword;
import models.Submission;
import models.User;

import org.junit.*;
import java.util.*;
import play.test.*;

public class BasicTest extends UnitTest {

	public void testGetFile() {
		Article.findById(startPlayBeforeTests);
	}

	@Test
	public void testCreateArticle() {
		// Fixtures.loadModels("data.yml");

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
		article.summary = "test";
		article.datePublished = new Date();
		;
		article.dateSubmitted = new Date();
		// article.pdfFileLink
		// article.journalNumber = null;
		Submission s = new Submission();
		s.save();
		article.submission = s;
		article.keywords = keywords;
		article.save();
		assertTrue(Article.count() > 1);

		/*User ad1 = new User();
		ad1.firstName = "test";
//		ad1.article = article;
		ad1.save();

		User ad2 = new User();
		ad2.firstName = "test";
//		ad2.article = article;
		ad2.save();*/

		assertTrue(User.count() > 1);

	}

	@Test
	public void testRegisterUser() {
		User u = User.find("byEmail", "bizso09@gmail.com").first();
		if (u != null)
			u.delete();

		/*User.registerUser("bizso09@gmail.com", "test", "test", "test",
				"test");
		User user = User.find("byEmail", "bizso09@gmail.com").first();
		assertNotNull(user);*/
	}

}
