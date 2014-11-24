package mentoring.task4.web.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "Logout Servlet", urlPatterns = {"/logout", "/logout.html"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            javax.management.MBeanServerConnection mbeanServerConnection
                    = java.lang.management.ManagementFactory
                    .getPlatformMBeanServer();
            javax.management.ObjectName mbeanName = new javax.management.ObjectName(
                    "jboss.as:subsystem=security,security-domain="
                            + "jboss-web-policy"
            );
            mbeanServerConnection.invoke(mbeanName, "flushCache", null, null);
        } catch (Exception e) {
            throw new SecurityException(e);
        }
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", new java.util.Date().toString());
        response.setHeader("Connection", "close");
        request.getSession().invalidate();
        request.logout();
        try {
            javax.management.MBeanServerConnection mbeanServerConnection
                    = java.lang.management.ManagementFactory
                    .getPlatformMBeanServer();
            javax.management.ObjectName mbeanName = new javax.management.ObjectName(
                    "jboss.as:subsystem=security,security-domain="
                            + "jboss-web-policy"
            );
            mbeanServerConnection.invoke(mbeanName, "flushCache", null, null);
        } catch (Exception e) {
            throw new SecurityException(e);
        }
        response.sendRedirect("/admin");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.logout();
    }

}
