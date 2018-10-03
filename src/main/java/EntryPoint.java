import accounts.AccountService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.RootPageServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class EntryPoint {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server server = new Server(port);

        ServletContextHandler servletContextHandler = new ServletContextHandler( ServletContextHandler.SESSIONS );

        AccountService accountService = AccountService.getInstance();

        RootPageServlet rootPageServlet = new RootPageServlet(accountService);
        SignInServlet signInServlet = new SignInServlet(accountService);
        SignUpServlet signUpServlet = new SignUpServlet(accountService);

        servletContextHandler.addServlet(new ServletHolder(rootPageServlet),"/");
        servletContextHandler.addServlet(new ServletHolder(signInServlet),"/signin");
        servletContextHandler.addServlet(new ServletHolder(signUpServlet),"/signup");

        server.setHandler(servletContextHandler);

        server.start();
        System.out.println("Creating server at port: " + port);
        System.out.println("Server started");
        server.join();
    }
}
