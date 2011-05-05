import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;

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
    	paras.put("email", "test");
    	paras.put("affiliation", "test");
    	paras.put("summary", "test");
    	paras.put("title", "test");
    	paras.put("keywords", "test,test,test");
    	
    	POST("/SubmitArticle/submit",paras,files);
    	
    	
    	
    }
     

    
}