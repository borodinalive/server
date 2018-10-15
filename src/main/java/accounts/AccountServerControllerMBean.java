package accounts;

public interface AccountServerControllerMBean {
    public int getUsers();
    public int getUsersLimit();
    public void setUsersLimit(int lim);
}
