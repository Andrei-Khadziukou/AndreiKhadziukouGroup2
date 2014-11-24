package mentoring.task4.web.admin;

import mentoring.task4.service.api.ICurrencyManagerLocal;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Currencies Servlet", urlPatterns = {"/manage-currencies-del", "/manage-currencies-del.html"})
public class CurrencyDelServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    ICurrencyManagerLocal currencyManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cur = request.getParameter("id");
        logger.info("Deleting currency [" + cur + "]...");
        currencyManagerLocal.remove(cur);
        response.sendRedirect("/admin/manage-currencies");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Auto-generated method stub
    }
}
