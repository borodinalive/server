package accounts;

public interface AccountServerInterface {
    public void addUser();
    public int getUsersCount();
    public int getUsersLimit();
    public void setUsersLimit(int lim);
}
