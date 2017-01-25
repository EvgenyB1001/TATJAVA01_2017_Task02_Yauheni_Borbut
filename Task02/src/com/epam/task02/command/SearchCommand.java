package com.epam.task02.command;

import com.epam.task02.shop.Shop;
import com.epam.task02.shop.equipment.SportEquipment;

import java.util.ArrayList;

/**
 * Class performs actions to search available equipment in the shop
 */
public class SearchCommand implements ShopPerformable {

    private String clientName;
    /**
     * Line with text of command
     */
    private final String COMMAND_NAME = "search";

    /**
     * list of parameters
     */
    private ArrayList<String> params;

    /**
     * Method searches available equipment according to input parameters
     */
    @Override
    public void execute() throws Exception {
        if (params == null || params.size() != 2) {
            throw new Exception("Invalid parameters, command can't be performed");
        }

        SportEquipment equipment = Shop.getInstance().searchAvailableEquipment(params.get(0), params.get(1));
        System.out.println("Equipment found: " + equipment.getCategory() + " "
                + equipment.getTitle() + " | count: "
                + Shop.getInstance().getEquipmentCount(equipment.getCategory(), equipment.getTitle())
                + " | price: " + equipment.getPrice());
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
