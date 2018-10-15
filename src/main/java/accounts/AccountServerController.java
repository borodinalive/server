package accounts;

public class AccountServerController implements AccountServerControllerMBean {
    private final AccountServerInterface accountServer;

    public AccountServerController(AccountServerInterface accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsers() {
        return accountServer.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int lim) {
        accountServer.setUsersLimit(lim);
    }
}
