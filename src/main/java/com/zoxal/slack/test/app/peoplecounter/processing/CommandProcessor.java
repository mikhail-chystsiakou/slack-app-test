package com.zoxal.slack.test.app.peoplecounter.processing;

import com.zoxal.slack.test.app.peoplecounter.messages.InvocationRequest;
import com.zoxal.slack.test.app.peoplecounter.messages.InvocationResponse;
import com.zoxal.slack.test.app.peoplecounter.processing.commands.Command;
import com.zoxal.slack.test.app.peoplecounter.processing.commands.CommandFactory;

/**
 * @author mich0217
 * @version 08/13/2018
 */
public class CommandProcessor {
    private CommandFactory commandFactory = new CommandFactory();

    public InvocationResponse process(InvocationRequest request) {
        Command command = commandFactory.getCommand(request.getText());
        if (command == null) {
            return new InvocationResponse("Command '" + request.getText() + "' is not supported, "
                    + "please contact your administrator");
        }

        return command.process(request);
    }
}
