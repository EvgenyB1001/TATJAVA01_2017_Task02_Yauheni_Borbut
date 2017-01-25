package com.epam.task02.shop;

import com.epam.task02.command.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class provides actions to interact shop with customer
 */
public class ShopPerformer {

    /**
     * Constant lines with text
     */
    private final String WELCOME_TEXT = "Hello! Welcome to our shop!";
    private final String TYPE_NAME_TEXT = "Please, type in your name: ";
    private final String CHOOSE_COMMAND_TEXT = "Type 'rent' and parameters of equipment to rent some item, separated by ',' or ' '\n" +
            "type 'giveBack' and parameters of equipment to return item, separated by ',' or ' '\n" +
            "type 'info' to show information about items:\ntype 'search' and parameters of equipment " +
            "to search item, separated by ',' or ' '\ntype 'exit' to exit:";
    private final String EXIT_TEXT = "exit";
    private final String ASK_ANOTHER_OPERATION_TEXT = "Do you want another operation? (Type 'y' to another operation):";


    /**
     * Method provides actions to interact with user, asking him to log in
     * and to choose definite action to perform
     */
    public void performSession() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(WELCOME_TEXT);
        // Flag, that tells system to continue or to close session
        boolean isDone = false;
        do {
            try {
                System.out.println(TYPE_NAME_TEXT);
                String name = scanner.nextLine();
                if (name.equals("")) {
                    throw new Exception("Invalid name");
                }

                Shop.getInstance().addClient(name);
                System.out.println(CHOOSE_COMMAND_TEXT);
                String command = scanner.nextLine();
                if (command.equals(EXIT_TEXT)) {
                    return;
                }

                performCommand(name, command);
                System.out.println(ASK_ANOTHER_OPERATION_TEXT);
                if (!scanner.nextLine().equals("y")) {
                    isDone = true;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (!isDone);
        scanner.close();
    }

    /**
     * Method performs definite action, depending on command, got as argument
     *
     * @param clientName name of user
     * @param command    command to perform
     */
    private void performCommand(String clientName, String command) throws Exception {
        if (clientName == null || command == null) {
            throw new Exception("Some of objects are not initialized. ShopPerformable can not be performed");
        }

        ArrayList<String> parameters = parseAttributes(command);
        CommandFactory factory = new CommandFactory();
        ShopPerformable currentCommand = factory.buildCommand(parameters.get(0));
        parameters.remove(0);
        currentCommand.setParams(clientName, parameters);
        currentCommand.execute();
    }

    /**
     * Method gets line of data, parses it and return list of attributes
     *
     * @param line line to parse
     * @return list of parsed attributes
     */
    private ArrayList<String> parseAttributes(String line) throws Exception {
        if (line == null || line.equals("")) {
            throw new Exception("Invalid input arguments");
        }

        line = line.replace(",", " ");
        String[] parts = line.split(" ");
        ArrayList<String> attributes = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].equals("")) {
                attributes.add(parts[i]);
            }
        }

        return attributes;
    }
}
