package com.epam.task02.command;

import com.epam.task02.shop.Shop;

import java.util.ArrayList;

/**
 * Class performs action to rent some equipment
 */
public class RentCommand implements ShopPerformable {

    /**
     * Line with client's name
     */
    private String clientName;

    /**
     * Line with text of command
     */
    private final String COMMAND_NAME = "rent";

    /**
     * list of parameters
     */
    private ArrayList<String> params;

    /**
     * Method performs action to rent equipment
     */
    @Override
    public void execute() throws Exception {
        if (params == null || params.size() != 3) {
            throw new Exception("Invalid parameters, command can't be performed");
        }

        Shop.getInstance().takeRent(Shop.getInstance().searchClient(clientName),
                params.get(0), params.get(1), Integer.parseInt(params.get(2)));
    }

    /**
     * Method sets parameters to initialize command
     *
     * @param clientName name of client
     * @param params     parameters to initialize command
     */
    @Override
    public void setParams(String clientName, ArrayList<String> params) throws Exception {
        if (clientName == null || params == null) {
            throw new Exception("Parameter to set command are not initialized");
        }

        this.clientName = clientName;
        this.params = params;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
