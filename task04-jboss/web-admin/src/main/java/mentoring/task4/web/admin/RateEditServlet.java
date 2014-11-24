package mentoring.task4.web.admin;

import mentoring.task4.service.api.IRateManagerLocal;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Rate Servlet", urlPatterns = {"/manage-rates-edit", "/manage-rates-edit.html"})
public class RateEditServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    IRateManagerLocal rateManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        logger.info("Deleting rate [" + id + "]...");
        rateManagerLocal.remove(id);
        response.sendRedirect("/admin/manage-rates");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        logger.info("Updating rate [" + id + "]...");
        rateManagerLocal.update(id, request.getParameter("rate"));
        response.sendRedirect("/admin/manage-rates");
    }

}
