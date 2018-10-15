package servlets;

import accounts.AccountServerInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageServlet extends HttpServlet {

    public final String SERVLET_URL = "/admin";
    private final AccountServerInterface accountServer;

    public AdminPageServlet(AccountServerInterface accountServer) {
        this.accountServer = accountServer;
        Logger logger = LogManager.getLogger(AdminPageServlet.class.getName());
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        accountServer.addUser();
        res.setStatus(200);
        res.getWriter().println(accountServer.getUsersLimit());
    }
}
