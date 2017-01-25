package com.epam.task02.shop.equipment;


/**
 * Class contains data of current sport equipment
 */
public class SportEquipment {

    /**
     * Counter to generate id numbers
     */
    private static long idCounter = 0;

    /**
     * Title of equipment
     */
    private String title;

    /**
     * Price of one specimen of this equipment
     */
    private int price;

    /**
     * Category of equipment
     */
    private String category;

    /**
     * Id of current specimen
     */
    private long id;

    /**
     * Constructor initialize fields of class and validates them
     *
     * @param category category of equipment
     * @param title    title of equipment
     * @param price    price of current specimen
     */
    public SportEquipment(String category, String title, int price) throws Exception {
        this.id = idCounter++;
        if (category != null && title != null) {
            this.category = category;
            this.title = title;
        } else {
            throw new Exception("Invalid title or category");
        }
        if (price > 0) {
            this.price = price;
        } else {
            throw new Exception("Price can't have negative value");
        }

    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

}
