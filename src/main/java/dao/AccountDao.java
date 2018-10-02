package dao;

import accounts.Account;

import java.sql.SQLException;

public interface AccountDao {
    Account get(String login) throws SQLException;

    void add(Account account) throws SQLException;
}
