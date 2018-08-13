package com.zoxal.slack.test.app.peoplecounter.processing.commands;

import com.zoxal.slack.test.app.peoplecounter.data.Storage;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationRequest;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationResponse;

import java.util.Collection;

/**
 * @author mich0217
 * @version 08/13/2018
 */
public class ListCommand implements Command {
    public static final String KEY = "list";

    @Override
    public InvocationResponse process(InvocationRequest invocationRequest) {
        Collection<String> registeredUsers = Storage.getDefaultStorage().getRegisteredUsers();
        return new InvocationResponse(String.join(", ", registeredUsers));
    }
}
