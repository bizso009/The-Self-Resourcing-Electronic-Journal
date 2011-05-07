import java.io.File;
import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
import org.junit.Test;
=======
import misc.CommonUtil;
import models.Article;
import models.User;
import models.UserRole;
>>>>>>> 1b69be0a72867cfcc5590cbe05f17738393e545e

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
    
    @Test
    public void testSubmitArticle() {
    	
    	File file = new File("test/sample.pdf");
    	Map<String,File> files = new HashMap<String,File>();
    	files.put("articlePdf", file);
    	
    	Map<String,String> paras = new HashMap<String,String>();
    	paras.put("firstName", "testtest");
    	paras.put("lastName", "testtest");
    	paras.put("email", "bizso09@gmail.com");
    	paras.put("affiliation", "test");
    	paras.put("summary", "test");
    	paras.put("title", "test");
    	paras.put("keywords", "test,test,test");
    	paras.put("author", "1");
    	paras.put("authNumber", "1");

    	POST("/SubmitArticle/submit",paras,files);
    	
    	
    	
    }
    @Test
    public void testSendEmail(){
   		
//			GET("/SubmitArticle/sendEmail?username=232424&pass=test&email=bitvaizs@gmail.com");
    }
     

    
}