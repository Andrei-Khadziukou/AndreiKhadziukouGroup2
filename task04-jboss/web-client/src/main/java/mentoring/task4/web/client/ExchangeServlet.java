package mentoring.task4.web.client;

import mentoring.task4.domain.Account;
import mentoring.task4.service.api.IAccountManagerLocal;
import mentoring.task4.service.api.IRateManagerLocal;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static mentoring.task4.web.client.Constants.JSP_PATH;


@WebServlet(urlPatterns = {"/exchange", "/exchange.html"})
public class ExchangeServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    IAccountManagerLocal accountManagerLocal;
    @EJB
    IRateManagerLocal rateManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = String.valueOf(request.getUserPrincipal());
        logger.info("Staring exchange for user [" + login + "]...");
        Account account = accountManagerLocal.findByUser(login);
        request.setAttribute("acc", account);
        request.setAttribute("user", login);
        String from = request.getParameter("from");
        request.setAttribute("from", from);
        request.setAttribute("maxsumm", accountManagerLocal.findDeposit(account, from).getAmount());
        request.setAttribute("currs", rateManagerLocal.getAvailCurrencies(from));
        request.setAttribute("rates", rateManagerLocal.getAvailRates(from));
        request.getRequestDispatcher(JSP_PATH + "exchange.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = String.valueOf(request.getUserPrincipal());
        logger.info("Submitting exchange for user [" + login + "]...");
        Account account = accountManagerLocal.findByUser(login);
        String from = String.valueOf(request.getParameter("from"));
        String to = String.valueOf(request.getParameter("to"));
        BigDecimal summ = new BigDecimal(String.valueOf(request.getParameter("summ")));
        accountManagerLocal.doExchange(account, from, to, summ);
        response.sendRedirect("/client/account");
    }

}
