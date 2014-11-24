package mentoring.task4.web.admin;

import mentoring.task4.service.api.IAccountManagerLocal;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Currencies Servlet", urlPatterns = {"/manage-accounts-edit", "/manage-accounts-edit.html"})
public class AccountEditServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    IAccountManagerLocal accountManagerLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        logger.info("Deleting account [" + id + "]...");
        accountManagerLocal.remove(id);
        response.sendRedirect("/admin/manage-accounts");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        logger.info("Submitting edition account [" + id + "]...");
        String passp = request.getParameter("passp");
        String userId = request.getParameter("user");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        if (id == null || id.trim().isEmpty()) {
            accountManagerLocal.add(passp, userId, name, phone, address);
        } else {
            accountManagerLocal.update(id, passp, userId, name, phone, address);
        }
        response.sendRedirect("/admin/manage-accounts");
    }

}
