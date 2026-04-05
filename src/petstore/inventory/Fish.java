package petstore.inventory;

/**
 * Represents a Fish in the pet store.
 * Inherits from the Animal class and adds specific fish-related properties.
 * 
 * @author Sanyam Sood
 * @since March 29th, 2026
 */
public class Fish extends Animal {
    private int finCount;
    private WaterType water;

    /**
     * Constructs a new Fish with name, date of birth, fin count, and water type.
     * 
     * @param name        The name of the fish.
     * @param dateOfBirth The date of birth in MM-DD-YYYY format.
     * @param finCount    The number of fins the fish has.
     * @param waterType   The type of water (e.g., SALT, FRESH) the fish lives in.
     * @throws Exception If initialization fails.
     */
    public Fish(String name, String dateOfBirth, int finCount, WaterType waterType) throws Exception {
        super(name, dateOfBirth);
        setFinCount(finCount);
        setWaterType(waterType);
    }

    /**
     * Constructs a new Fish with a specific ID, name, date of birth, fin count, and
     * water type.
     * This constructor is typically used when loading existing data from a file.
     * 
     * @param id          The unique identifier for the fish.
     * @param name        The name of the fish.
     * @param dateOfBirth The date of birth in MM-DD-YYYY format.
     * @param finCount    The number of fins the fish has.
     * @param waterType   The type of water the fish lives in.
     * @throws Exception If initialization fails.
     */
    public Fish(int id, String name, String dateOfBirth, int finCount, WaterType waterType) throws Exception {
        super(id, name, dateOfBirth);
        setFinCount(finCount);
        setWaterType(waterType);
    }

    /**
     * Gets the number of fins.
     * 
     * @return The fin count.
     */
    public int getFinCount() {
        return finCount;
    }

    /**
     * Sets the number of fins.
     * 
     * @param finCount The number of fins.
     */
    public void setFinCount(int finCount) {
        this.finCount = finCount;
    }

    /**
     * Gets the water type.
     * 
     * @return The water type.
     */
    public WaterType getWaterType() {
        return water;
    }

    /**
     * Sets the water type.
     * 
     * @param water The water type.
     */
    public void setWaterType(WaterType water) {
        this.water = water;
    }

    /**
     * Displays the fish's information to the console.
     */
    @Override
    public void displayAnimal() {
        super.displayAnimal();
        System.out.printf(" %-15d %-10s\n", finCount, water);
    }

}
