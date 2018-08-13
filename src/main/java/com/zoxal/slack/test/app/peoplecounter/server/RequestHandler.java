package com.zoxal.slack.test.app.peoplecounter.server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationRequest;
import javafx.beans.binding.StringBinding;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 08/12/2018
 */
public class RequestHandler extends AbstractHandler {
    private static ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public RequestHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Reader in = new InputStreamReader(request.getInputStream());

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = request.getInputStream().read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }


        String inputMessage = result.toString("UTF-8");
        logger.debug("Got message: \n{}", inputMessage);

        try {
            InvocationRequest command = objectMapper.readValue(inputMessage, InvocationRequest.class);
            logger.debug("Got command '{}' from {}", command.getText(), command.getUserName());
        } catch (IOException e) {
            logger.error("Failed to read data", e);
        }

        baseRequest.setHandled(true);
    }
}
