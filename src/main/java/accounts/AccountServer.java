package accounts;

public class AccountServer implements AccountServerInterface {
    private int usersCount;
    private int usersLimit;

    public AccountServer(int usersLimit) {
        this.usersLimit = usersLimit;
        this.usersCount = 0;
    }

    @Override
    public void addUser() {
        this.usersCount++;
    }

    @Override
    public int getUsersCount() {
        return this.usersCount;
    }

    @Override
    public int getUsersLimit() {
        return this.usersLimit;
    }

    @Override
    public void setUsersLimit(int lim) {
        this.usersLimit = lim;
    }
}
