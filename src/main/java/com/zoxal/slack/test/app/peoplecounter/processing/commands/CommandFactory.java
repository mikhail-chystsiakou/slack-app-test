package com.zoxal.slack.test.app.peoplecounter.processing.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author mich0217
 * @version 08/13/2018
 */
public class CommandFactory {
    private static Map<String, Supplier<Command>> commandMap = new HashMap<>();

    static {
        commandMap.put(RegisterCommand.KEY, RegisterCommand::new);
        commandMap.put(ListCommand.KEY, ListCommand::new);
        commandMap.put(ResetCommand.KEY, ResetCommand::new);
    }

    public Command getCommand(String text) {
        if (commandMap.containsKey(text)) {
            return commandMap.get(text).get();
        }
        return null;
    }
}
