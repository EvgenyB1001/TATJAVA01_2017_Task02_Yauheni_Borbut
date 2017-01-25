package com.epam.task02.shop.rent;

import com.epam.task02.client.Client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class contains information about items in rent
 */
public class RentUnit {

    /**
     * List of items in rent
     */
    private ArrayList<String[]> units = new ArrayList<>();

    private static RentUnit instance;

    private RentUnit() {
    }

    /**
     * Implementation of singleton
     */
    public static RentUnit getInstance() {
        if (instance == null) {
            instance = new RentUnit();
        }

        return instance;
    }

    /**
     * Method gets parameters of equipment and client, validates them and adds another equipment
     * and client's name to the rent list.
     *
     * @param equipmentName name of equipment
     * @param count         count of items
     * @param client        object of client
     */
    public void addRentEquipment(Client client, String equipmentName, Integer count) throws Exception {
        if (client == null || equipmentName == null || count.compareTo(0) <= 0) {
            throw new Exception("Invalid argument to add rent item");
        }
        // Searches, does current equipment already exist in rent list. If it is, just adds count to it
        for (String[] record : units) {
            if (record[0].equals(client.getName()) && record[1].equals(equipmentName)) {
                Integer current = Integer.parseInt(record[2]);
                current = current + count;
                record[2] = current.toString();
                return;
            }
        }

        units.add(new String[]{client.getName(), equipmentName, count.toString()});
    }

    /**
     * Method removes record of rent item according to parameters, got as arguments
     *
     * @param client        object of client
     * @param equipmentName name of equipment
     * @param count         count of items
     */
    public void removeRentEquipment(Client client, String equipmentName, Integer count) throws Exception {
        if (client == null || equipmentName == null || count.compareTo(0) <= 0) {
            throw new Exception("Invalid argument to remove rent item");
        }

        Iterator it = units.iterator();
        while (it.hasNext()) {
            String[] record = (String[]) it.next();
            if (record[0].equals(client.getName()) && record[1].equals(equipmentName)) {
                Integer current = Integer.parseInt(record[2]);
                if (count.compareTo(current) > 0) {
                    throw new Exception("Error! Count of items to give back more than count of rented items");
                }
                if (count.compareTo(current) < 0) {
                    current = current - count;
                    record[2] = current.toString();
                } else if (count.compareTo(current) == 0) {
                    it.remove();
                }
            }
        }
    }

    public ArrayList<String[]> getRentList() {
        return units;
    }
}
