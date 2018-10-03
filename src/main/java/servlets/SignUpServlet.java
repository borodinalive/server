package servlets;

import accounts.Account;
import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(200);
        res.getWriter().println("signup");
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

        try {
            if (this.accountService.check(login)) {
                res.setStatus(409);
                res.getWriter().println("Пользователь с логином " + login + " уже существует.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Account account = new Account(login, password);

        try {
            this.accountService.save(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        res.setStatus(200);
    }
}
