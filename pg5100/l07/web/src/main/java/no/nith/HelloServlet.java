package no.nith;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Simen Bekkhus
 */
@WebServlet(name = "HelloServlet", urlPatterns = "/HelloServ")
public class HelloServlet extends HttpServlet {
    @EJB(beanName = "HelloBeanEJB")
    public HelloBean ejb;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String hei = ejb.Hello();

        request.setAttribute("msg", hei);
        request.getRequestDispatcher("svar.jsp").forward(request, response);
    }
}
