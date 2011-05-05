package misc;

import java.util.UUID;

public class CommonUtil
{
    public static String sanitizeForHTML(String data)
    {
        return data.trim().replaceAll("&", "&amp;").replaceAll("\r", "\n").replaceAll("\n\n", "\n")
            .replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"","&quot;").replaceAll("\n","<br/>").trim();
    }
    
    public static String randomString(){
		return UUID.randomUUID().toString().split("-")[0];

    }
}
