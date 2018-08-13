package com.zoxal.slack.test.app.peoplecounter.processing.commands;

import com.zoxal.slack.test.app.peoplecounter.data.Storage;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationRequest;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationResponse;
import com.zoxal.slack.test.app.peoplecounter.processing.RepeatedAttemptManager;

/**
 * @author mich0217
 * @version 08/13/2018
 */
public class RegisterCommand implements Command {
    public static final String KEY = "hi";
    private RepeatedAttemptManager repeatedAttemptManager = new RepeatedAttemptManager();

    @Override
    public InvocationResponse process(InvocationRequest invocationRequest) {
        int registrationCounter = Storage.getDefaultStorage().registerUser(
                invocationRequest.getUserId(),
                invocationRequest.getUserName()
        );

        InvocationResponse response = new InvocationResponse(repeatedAttemptManager.userRegistered(
                invocationRequest.getUserName(), registrationCounter)
        );

        if (registrationCounter == RepeatedAttemptManager.BAN_LIMIT) {
            Storage.getDefaultStorage().ban(invocationRequest.getUserId());
        }

        if (registrationCounter == RepeatedAttemptManager.DOUBLE_BAN_LIMIT) {
            Storage.getDefaultStorage().doubleBan(invocationRequest.getUserId());
        }

        return response;
    }
}
