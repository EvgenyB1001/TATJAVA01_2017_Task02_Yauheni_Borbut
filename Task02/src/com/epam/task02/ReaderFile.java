package com.epam.task02;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class reads data from file
 */
public class ReaderFile {

    /**
     * Method gets file path, reads current file and return list of parameters
     *
     * @param filePath path to current file
     * @return list of parameters
     */
    public ArrayList<String[]> readFromTxt(String filePath) throws Exception {
        if (filePath == null) {
            throw new Exception("Path to file is not initialized");
        }

        Scanner scanner = new Scanner(new File(filePath));
        ArrayList<String[]> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] array = line.split(",");
            list.add(array);
        }

        return list;
    }
}
