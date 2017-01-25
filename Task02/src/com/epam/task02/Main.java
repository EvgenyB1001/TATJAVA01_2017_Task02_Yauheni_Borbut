package com.epam.task02;

import com.epam.task02.shop.ShopInitializer;
import com.epam.task02.shop.ShopPerformer;

/**
 * Class has entry point to the program
 */
public class Main {

    /**
     * Line with name of file, that contains data to initialize
     */
    public static final String FILE_NAME = "\\data.txt";

    /**
     * Method is entry point to program
     */
    public static void main(String[] args) {
        try {
            new ShopInitializer().initialize(System.getProperty("user.dir") + FILE_NAME);
            new ShopPerformer().performSession();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
