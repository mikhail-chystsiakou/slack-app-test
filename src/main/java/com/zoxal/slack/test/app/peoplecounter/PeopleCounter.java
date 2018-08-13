package com.zoxal.slack.test.app.peoplecounter;

import com.zoxal.slack.test.app.peoplecounter.server.RequestHandler;
import org.eclipse.jetty.server.Server;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 08/12/2018
 */
public class PeopleCounter {
    private static final int DEFAULT_PORT = 54321;

    public static void main(String[] args) throws Exception {
        Server server = new Server(parseBindPort(args));
        server.setHandler(new RequestHandler());

        server.start();
        server.join();
    }

    private static int parseBindPort(String[] args) {
        if (args.length >= 2) {
            try {
                return Integer.valueOf(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Port argument MUST be an integer value");
                System.exit(1);
            }
        }
        return DEFAULT_PORT;
    }
}
