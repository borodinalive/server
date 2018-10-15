import accounts.AccountServer;
import accounts.AccountServerController;
import accounts.AccountServerControllerMBean;
import accounts.AccountServerInterface;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AdminPageServlet;
import servlets.RootPageServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class EntryPoint {
    public static void main(String[] args) throws Exception {
        Logger logger = LogManager.getLogger(EntryPoint.class.getName());

        int port = 8080;
        Server server = new Server(port);

        AccountServerInterface accountServer = new AccountServer(10);

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mbs.registerMBean(serverStatistics, name);

        ServletContextHandler servletContextHandler = new ServletContextHandler( ServletContextHandler.SESSIONS );

        RootPageServlet rootPageServlet = new RootPageServlet();
        AdminPageServlet adminPageServlet =  new AdminPageServlet(accountServer);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("public_html");

        servletContextHandler.addServlet(new ServletHolder(rootPageServlet),"/");
        servletContextHandler.addServlet(new ServletHolder(adminPageServlet),adminPageServlet.SERVLET_URL);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, servletContextHandler});

        server.setHandler(handlerList);

        server.start();
        logger.info("Creating server at port: " + port);
        System.out.println("Server started");
        server.join();
    }
}
