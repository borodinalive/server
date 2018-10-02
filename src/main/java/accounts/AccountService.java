package accounts;

public class AccountService {
    private static AccountService instance = new AccountService();

    private AccountService() {}

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }

        return instance;
    }

    public void save(Account account) {

    }

    public boolean check(String accountLogin) {
        return true;
    }

    public boolean check(String accountLogin, String accountPassword) {
        return true;
    }

    public Account get(String accountLogin) {
        return null;
    }
}
