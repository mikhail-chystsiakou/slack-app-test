package com.zoxal.slack.test.app.peoplecounter.processing.commands;

import com.zoxal.slack.test.app.peoplecounter.data.Storage;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationRequest;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationResponse;
import com.zoxal.slack.test.app.peoplecounter.processing.SecurityManager;

/**
 * @author mich0217
 * @version 08/13/2018
 */
public class ResetCommand implements Command {
    public static final String KEY = "reset";
    private SecurityManager securityManager = new SecurityManager();

    @Override
    public InvocationResponse process(InvocationRequest invocationRequest) {
        if (!securityManager.isResetAllowed(invocationRequest.getUserId())) {
            return new InvocationResponse("Are you Mike? You are not Mike. Good bye.");
        }
        Storage.getDefaultStorage().reset();
        return new InvocationResponse("User list reset");
    }
}
