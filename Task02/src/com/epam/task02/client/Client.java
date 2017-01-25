package com.epam.task02.client;

/**
 * Class contains data of current client
 */
public class Client {

    private long idCounter = 0;

    /**
     * ID of client
     */
    private long id = 0;

    /**
     * Name of client
     */
    private String name;

    /**
     * Constructor initializes fields of class and validates them
     *
     * @param name name of client
     */
    public Client(String name) throws Exception {
        this.id = idCounter++;
        if (name != null) {
            this.name = name;
        } else {
            throw new Exception("Invalid arguments to initialize client");
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
