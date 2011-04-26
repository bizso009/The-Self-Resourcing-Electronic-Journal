package journal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * BaseServlet that provides some useful methods used by its descendants.
 */
@WebServlet("/BaseServlet.do")
public abstract class BaseServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public BaseServlet()
    {
        super();
    }

    protected void forward(String jspPath, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            getServletConfig().getServletContext().getRequestDispatcher(jspPath).forward(request, response);
        }
        catch (ServletException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
