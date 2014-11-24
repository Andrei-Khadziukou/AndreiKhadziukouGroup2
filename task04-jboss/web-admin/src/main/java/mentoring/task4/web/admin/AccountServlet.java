package mentoring.task4.web.admin;

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

import static mentoring.task4.web.admin.Constants.JSP_PATH;

@WebServlet(description = "Currencies Servlet", urlPatterns = {"/manage-accounts", "/manage-accounts.html"})
public class AccountServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    IAccountManagerLocal accountManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Getting accounts..");
        request.setAttribute("accs", accountManagerLocal.findAll());
        request.getRequestDispatcher(JSP_PATH + "accounts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        logger.info("Editing account [" + id + "]...");
        Account account;
        if (id == null || id.trim().isEmpty()) {
            account = new Account();
        } else {
            account = accountManagerLocal.get(id);
        }
        request.setAttribute("acc", account);
        request.getRequestDispatcher(JSP_PATH + "editaccount.jsp").forward(request, response);
    }

}
