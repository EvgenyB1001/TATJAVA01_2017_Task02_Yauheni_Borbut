package com.epam.task02.shop;

import com.epam.task02.ReaderFile;
import com.epam.task02.shop.equipment.SportEquipment;

import java.util.ArrayList;

/**
 * Class provides actions to initialize shop's data
 */
public class ShopInitializer {

    /**
     * Method gets current object of shop and path to file with data, and initializes shop with such data
     *
     * @param filePath path to file with data
     */
    public void initialize(String filePath) throws Exception {
        ArrayList<String[]> arguments = new ReaderFile().readFromTxt(filePath);
        for (String[] curArguments : arguments) {
            // Number of parameters to initialize available items must be equal 4 (category, title, price, count)
            if (curArguments.length == 4) {
                Shop.getInstance().setEquipment(new SportEquipment(curArguments[0],
                        curArguments[1], Integer.parseInt(curArguments[2])), Integer.parseInt(curArguments[3]));
            }
        }
    }
}
