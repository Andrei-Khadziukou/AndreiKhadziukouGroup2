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

import static mentoring.task4.web.admin.Constants.JSP_PATH;

@WebServlet(description = "Currencies Servlet", urlPatterns = {"/manage-currencies", "/manage-currencies.html"})
public class CurrencyServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    ICurrencyManagerLocal currencyManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Getting currencies...");
        request.setAttribute("currs", currencyManagerLocal.findAll());
        request.getRequestDispatcher(JSP_PATH + "currency.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cur = request.getParameter("curr");
        logger.info("Adding currency [" + cur + "]...");
        currencyManagerLocal.add(cur);
        response.sendRedirect("/admin/manage-currencies");
    }

}
