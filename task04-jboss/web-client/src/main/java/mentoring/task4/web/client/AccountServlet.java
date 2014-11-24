package mentoring.task4.web.client;

import mentoring.task4.domain.Account;
import mentoring.task4.service.api.IAccountManagerLocal;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static mentoring.task4.web.client.Constants.JSP_PATH;


@WebServlet(urlPatterns = {"/account", "/account.html"})
public class AccountServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    IAccountManagerLocal accountManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = String.valueOf(request.getUserPrincipal());
        logger.info("Getting account for user [" + login + "]...");
        Account account = accountManagerLocal.findByUser(login);
        request.setAttribute("acc", account);
        request.setAttribute("user", login);
        request.getRequestDispatcher(JSP_PATH + "account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //stub
    }

}
