package misc;
public class CommonUtil
{
    public static String sanitizeForXML(String data)
    {
        return data.trim().replaceAll("&", "&amp;").replaceAll("\r", "\n").replaceAll("\n\n", "\n")
            .replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"","&quot;").trim();
    }
}
