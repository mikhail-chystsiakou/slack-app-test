package com.zoxal.slack.test.app.peoplecounter.processing.commands;

import com.zoxal.slack.test.app.peoplecounter.messages.InvocationRequest;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationResponse;

/**
 * Allowed commands
 *
 * @author mich0217
 * @version 08/13/2018
 */
public interface Command {
    InvocationResponse process(InvocationRequest invocationRequest);
}
