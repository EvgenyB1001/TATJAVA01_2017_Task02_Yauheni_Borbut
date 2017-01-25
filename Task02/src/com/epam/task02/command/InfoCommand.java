package com.epam.task02.command;

import com.epam.task02.shop.Shop;

import java.util.ArrayList;

/**
 * Class performs action to output information about available and rented equipment
 */
public class InfoCommand implements ShopPerformable {

    /**
     * Line with text of command
     */
    private final String COMMAND_NAME = "info";

    /**
     * Calls method of class Shop to output information
     */
    @Override
    public void execute() {
        Shop.getInstance().showInformation();
    }

    /**
     * Method sets parameters to initialize command
     *
     * @param clientName name of client
     * @param params     parameters to initialize command
     */
    @Override
    public void setParams(String clientName, ArrayList<String> params) throws Exception {
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
