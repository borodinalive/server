package servlets;

import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null) {
            res.setStatus(400);
            res.getWriter().println("Ни логин, ни пароль не могут быть пустыми");
            return;
        }

        if (this.accountService.check(login, password)) {
            res.setStatus(200);
            res.getWriter().println("Authorized: " + login);
            return;
        }

        res.setStatus(401);
        res.getWriter().println("Unauthorized");
    }
}
