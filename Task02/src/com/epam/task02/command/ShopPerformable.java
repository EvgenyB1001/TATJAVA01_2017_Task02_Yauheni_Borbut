package com.epam.task02.command;


import java.util.ArrayList;

/**
 * Interface, that provide actions for all command, that shop can perform
 */
public interface ShopPerformable {

    /**
     * Method performs action according to class, tah implements this method
     */
    void execute() throws Exception;

    /**
     * Method sets parameters to execute command
     */
    void setParams(String clientName, ArrayList<String> params) throws Exception;

    /**
     * Method returns text of name of command
     */
    String getCommandName();
}
