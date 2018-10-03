package accounts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;

public class AccountService {
    private static AccountService instance = null;
    private final SessionFactory sessionFactory;
    private Session session;

    private AccountService() throws Exception {
        Configuration configuration = getH2Configuration();
        sessionFactory = createSessionFactory(configuration);

        try {
            session = sessionFactory.openSession();
        } catch(Exception e) {
            throw new Exception("... something go wrong:\n" + e.getMessage());
        }
    }

    public static AccountService getInstance() throws Exception {
        if (instance == null) {
            instance = new AccountService();
        }

        return instance;
    }

    public void save(Account account) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.save(account);
        transaction.commit();
    }

    public boolean check(String accountLogin) throws SQLException {
        Account account = session.get(Account.class, accountLogin);
        return account != null;
    }

    public boolean check(String accountLogin, String accountPassword) throws SQLException {
        Account account = session.get(Account.class, accountLogin);
        return account.getPassword().equals(accountPassword);
    }

    public Account get(String accountLogin) throws SQLException {
        Account account = session.get(Account.class, accountLogin);
        return account;
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
        configuration.setProperty("hibernate.connection.username", "sa");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(Account.class);

        return configuration;
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
