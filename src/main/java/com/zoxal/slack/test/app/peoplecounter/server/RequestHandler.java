package com.zoxal.slack.test.app.peoplecounter.server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationRequest;
import com.zoxal.slack.test.app.peoplecounter.processing.CommandProcessor;
import com.zoxal.slack.test.app.peoplecounter.data.InMemoryStorage;
import com.zoxal.slack.test.app.peoplecounter.data.Storage;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationResponse;
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
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private CommandProcessor commandProcessor = new CommandProcessor();
    private Storage storage = new InMemoryStorage();

    private static final String USER_NAME_PARAM = "user_name";
    private static final String USER_ID_PARAM = "user_id";
    private static final String COMMAND_TEXT_PARAM = "text";

    public RequestHandler() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        InvocationRequest invocationRequest = new InvocationRequest();
        invocationRequest.setText(request.getParameter(COMMAND_TEXT_PARAM));
        invocationRequest.setUserId(request.getParameter(USER_ID_PARAM));
        invocationRequest.setUserName(request.getParameter(USER_NAME_PARAM));

        logger.debug("Got command {}", invocationRequest);

        InvocationResponse invocationResponse = commandProcessor.process(invocationRequest);

        OutputStream responseOutputStream = response.getOutputStream();
        objectMapper.writeValue(responseOutputStream, invocationResponse);

        baseRequest.setHandled(true);
    }
}
