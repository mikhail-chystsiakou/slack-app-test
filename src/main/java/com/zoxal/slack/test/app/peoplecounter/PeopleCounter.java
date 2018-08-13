package com.zoxal.slack.test.app.peoplecounter;

import com.zoxal.slack.test.app.peoplecounter.processing.SecurityManager;
import com.zoxal.slack.test.app.peoplecounter.server.RequestHandler;
import org.eclipse.jetty.server.Server;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 08/12/2018
 */
public class PeopleCounter {
    private static final int DEFAULT_PORT = 4848;
    private static final String DEFAULT_ADMIN_UID = "UC4HWK6EQ";

    public static void main(String[] args) throws Exception {
        Server server = new Server(parseBindPort(args));
        SecurityManager.setAdminUID(parseAdminId(args));
        server.setHandler(new RequestHandler());

        server.start();
        server.join();
    }

    private static int parseBindPort(String[] args) {
        if (args.length >= 1) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Port argument MUST be an integer value");
                System.exit(1);
            }
        }
        return DEFAULT_PORT;
    }

    private static String parseAdminId(String[] args) {
        if (args.length >= 2) {
            return args[1];
        }
        return DEFAULT_ADMIN_UID;
    }
}
