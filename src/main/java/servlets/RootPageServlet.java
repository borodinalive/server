package servlets;

import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RootPageServlet extends HttpServlet {

    private AccountService accountService;
    public RootPageServlet(AccountService accountService) {
        this.accountService =  accountService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(200);
        res.getWriter().println("hello!");
    }
}
