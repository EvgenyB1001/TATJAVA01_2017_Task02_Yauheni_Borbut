package com.epam.task02.shop;

import com.epam.task02.client.Client;
import com.epam.task02.shop.equipment.SportEquipment;
import com.epam.task02.shop.rent.RentUnit;

import java.util.HashMap;
import java.util.Map;

/**
 * Class provides actions, that shop can perform, including adding equipments and clients, taking rent
 */
public class Shop {

    /**
     * List of items available in shop
     */
    private HashMap<SportEquipment, Integer> goods = new HashMap<>();

    /**
     * List of existing clients
     */
    private HashMap<Client, Integer> clients = new HashMap<>();

    private static Shop instance;

    private Shop() {
    }

    /**
     * Implementation of singleton
     */
    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    /**
     * Method validates arguments and sets another equipment to list of available items
     *
     * @param equipment object of equipment
     * @param count     count of item
     */
    public void setEquipment(SportEquipment equipment, Integer count) throws Exception {
        if (equipment == null || count.compareTo(0) <= 0) {
            throw new Exception("Invalid arguments of goods");
        }
        // Searches, does new equipment already exist in list and if it is, adds count
        for (Map.Entry<SportEquipment, Integer> regEquip : goods.entrySet()) {
            if (regEquip.getKey().getId() == equipment.getId()) {
                regEquip.setValue(regEquip.getValue() + count);
                return;
            }
        }

        goods.put(equipment, count);
    }

    /**
     * Method validates arguments and sets another client to list of clients
     *
     * @param clientName name of current client
     */
    public void addClient(String clientName) throws Exception {
        if (clientName == null) {
            throw new Exception("Client not initialized");
        }

        // Searches, does the new client already exist
        for (Map.Entry<Client, Integer> regClient : clients.entrySet()) {
            if (regClient.getKey().getName().equals(clientName)) {
                System.out.println("Welcome, " + clientName + "!\n");
                return;
            }
        }

        clients.put(new Client(clientName), 0);
        System.out.println("New client is registered\n");
    }

    /**
     * Method gets parameters as arguments, validates them and set new record of rent item
     *
     * @param category category of rent item
     * @param title    title of rent item
     * @param client   object of client
     * @param count    count of items to rent
     */
    public void takeRent(Client client, String category, String title, Integer count) throws Exception {
        if (category == null || count.compareTo(0) <= 0 || client == null || title == null) {
            throw new Exception("Invalid arguments to take rent");
        }

        SportEquipment equipment = searchAvailableEquipment(category, title);
        for (Map.Entry<Client, Integer> entry : clients.entrySet()) {
            if (entry.getKey().getId() == client.getId()) {
                Integer current = entry.getValue() + count;
                if (current.compareTo(3) > 0) {
                    throw new Exception("One client isn't allowed to take a rent more than 3 items");
                }

                if (goods.get(equipment).compareTo(count) < 0) {
                    throw new Exception("Insufficient items to rent");
                }

                RentUnit.getInstance().addRentEquipment(client,
                        equipment.getCategory() + " " + equipment.getTitle(), count);
                goods.put(equipment, goods.get(equipment) - count);
                clients.put(entry.getKey(), current);
            }
        }
    }

    /**
     * Method searches available equipment according to arguments and returns it
     *
     * @param category category of item
     * @param title    title of item
     * @return object of item
     */
    public SportEquipment searchAvailableEquipment(String category, String title) throws Exception {
        if (category == null || title == null) {
            throw new Exception("Arguments are not initialized");
        }

        for (Map.Entry<SportEquipment, Integer> regEquip : goods.entrySet()) {
            if (regEquip.getKey().getCategory().equals(category) &&
                    regEquip.getKey().getTitle().equals(title) && regEquip.getValue() != 0) {
                return regEquip.getKey();
            }
        }

        throw new Exception("Shop doesn't provide such item");
    }

    /**
     * Method searches available equipment according to arguments and returns its count
     *
     * @param category category of item
     * @param title    title of item
     * @return current count of available equipment
     */
    public Integer getEquipmentCount(String category, String title) throws Exception {
        if (category == null || title == null) {
            throw new Exception("Arguments are not initialized");
        }

        for (Map.Entry<SportEquipment, Integer> regEquip : goods.entrySet()) {
            if (regEquip.getKey().getCategory().equals(category) &&
                    regEquip.getKey().getTitle().equals(title)) {
                return regEquip.getValue();
            }
        }

        throw new Exception("Shop doesn't provide such item");
    }

    /**
     * Method searches definite client according to arguments and returns it
     *
     * @param name name of client
     * @return definite client
     */
    public Client searchClient(String name) throws Exception {
        if (name == null) {
            throw new Exception("Arguments are not initialized");
        }

        for (Map.Entry<Client, Integer> regClient : clients.entrySet()) {
            if (regClient.getKey().getName().equals(name)) {
                return regClient.getKey();
            }
        }

        throw new Exception("There is no such client");
    }

    /**
     * Method outputs information about available and rented items
     */
    public void showInformation() {
        System.out.println("Available goods:\n");
        for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
            System.out.println(entry.getKey().getCategory() + " " + entry.getKey().getTitle()
                    + " | count: " + entry.getValue());
        }

        System.out.println();
        System.out.println("Items in rent:\n");
        for (String[] record : RentUnit.getInstance().getRentList()) {
            System.out.println(record[1] + " | count: " + record[2] + " | took by: " + record[0]);
        }
    }

    /**
     * Method remove record of rented item according to parameters got as arguments
     *
     * @param category category of rent item
     * @param title    title of rent item
     * @param client   object of client
     * @param count    count of items to rent
     */
    public void returnFromRent(Client client, String category, String title, Integer count) throws Exception {
        if (client == null || category == null || title == null) {
            throw new Exception("Arguments client name or category, or title is not initialized");
        }
        if (count.compareTo(0) <= 0) {
            throw new Exception("Invalid count of items");
        }

        SportEquipment equipment = searchAvailableEquipment(category, title);
        for (Map.Entry<Client, Integer> entry : clients.entrySet()) {
            if (entry.getKey().getId() == client.getId()) {
                Integer current = entry.getValue() - count;
                if (current.compareTo(0) < 0) {
                    throw new Exception("Number of items to give back must be equal or less than number of rented items");
                }

                RentUnit.getInstance().removeRentEquipment(client,
                        equipment.getCategory() + " " + equipment.getTitle(), count);
                goods.put(equipment, goods.get(equipment) + count);
                clients.put(entry.getKey(), current);
            }
        }
    }
}