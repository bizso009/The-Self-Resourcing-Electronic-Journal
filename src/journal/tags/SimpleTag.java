package journal.tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

public class SimpleTag extends TagSupport
{
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException
    {
        try {
            pageContext.getOut().print("Hello.");
         } catch (Exception ex) {
            throw new JspTagException("SimpleTag: " + 
               ex.getMessage());
         }
         return SKIP_BODY;
    }
    
    @Override
    public int doEndTag() throws JspException
    {
        return EVAL_PAGE;
    }
}
