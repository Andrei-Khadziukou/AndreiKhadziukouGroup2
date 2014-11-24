package mentoring.task4.web.admin;

import mentoring.task4.service.api.ICurrencyManagerLocal;
import mentoring.task4.service.api.IRateManagerLocal;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static mentoring.task4.web.admin.Constants.JSP_PATH;

@WebServlet(description = "Rates Servlet", urlPatterns = {"/manage-rates", "/manage-rates.html"})
public class RateServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    IRateManagerLocal rateManagerLocal;

    @EJB
    ICurrencyManagerLocal currencyManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Getting rates..");
        request.setAttribute("rates", rateManagerLocal.findAll());
        request.setAttribute("currs", currencyManagerLocal.findAll());
        request.getRequestDispatcher(JSP_PATH + "rate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String rate = request.getParameter("rate");
        logger.info("Adding rate [" + from + "-" + to + ":" + rate + "]...");
        rateManagerLocal.add(from, to, rate);
        response.sendRedirect("/admin/manage-rates");
    }

}
