package com.epam.task02.command;

import java.util.ArrayList;

/**
 * Class creates objects of definite classes, depending on command, got as parameter
 */
public class CommandFactory {

    /**
     * List of available commands
     */
    ArrayList<ShopPerformable> commands = new ArrayList<>();

    /**
     * Constructor initializes list of available commands
     */
    public CommandFactory() throws Exception {
        commands.add(new RentCommand());
        commands.add(new GiveBackCommand());
        commands.add(new SearchCommand());
        commands.add(new InfoCommand());
    }

    /**
     * Method builds object of classes, that implements interface ShopPerformable, depending on command
     * got as argument, and returns it
     *
     * @param command line with command
     */
    public ShopPerformable buildCommand(String command) throws Exception {
        for (ShopPerformable performable : commands) {
            if (performable.getCommandName().equals(command)) {
                return performable;
            }
        }
        throw new Exception("No such command");
    }
}
