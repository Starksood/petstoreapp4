package petstore.inventory;

/**
 * Represents a Bird in the pet store.
 * Inherits from the Animal class and adds specific bird-related properties.
 * 
 * @author Sanyam Sood
 * @since March 29th, 2026
 */
public class Bird extends Animal {
    private int wingSpan;
    private NestType nest;

    /**
     * Constructs a new Bird with name, date of birth, wingspan, and nest type.
     * 
     * @param name        The name of the bird.
     * @param dateOfBirth The date of birth in MM-DD-YYYY format.
     * @param wingSpan    The wingspan of the bird.
     * @param nestType    The type of nest the bird uses.
     * @throws Exception If initialization fails.
     */
    public Bird(String name, String dateOfBirth, int wingSpan, NestType nestType) throws Exception {
        super(name, dateOfBirth);
        setWingSpan(wingSpan);
        setNestType(nestType);
    }

    /**
     * Constructs a new Bird with a specific ID, name, date of birth, wingspan, and
     * nest type.
     * This constructor is typically used when loading existing data from a file.
     * 
     * @param id          The unique identifier for the bird.
     * @param name        The name of the bird.
     * @param dateOfBirth The date of birth in MM-DD-YYYY format.
     * @param wingSpan    The wingspan of the bird.
     * @param nestType    The type of nest the bird uses.
     * @throws Exception If initialization fails.
     */
    public Bird(int id, String name, String dateOfBirth, int wingSpan, NestType nestType) throws Exception {
        super(id, name, dateOfBirth);
        setWingSpan(wingSpan);
        setNestType(nestType);
    }

    /**
     * Sets the nest type.
     * 
     * @param nest The nest type.
     */
    public void setNestType(NestType nest) {
        this.nest = nest;
    }

    /**
     * Gets the wingspan.
     * 
     * @return The wingspan.
     */
    public int getWingSpan() {
        return wingSpan;
    }

    /**
     * Sets the wingspan.
     * 
     * @param wingSpan The wingspan.
     */
    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }

    /**
     * Gets the nest type.
     * 
     * @return The nest type.
     */
    public NestType getNestType() {
        return nest;
    }

    /**
     * Displays the bird's information to the console.
     */
    @Override
    public void displayAnimal() {
        super.displayAnimal();
        System.out.printf(" %-15d %-10s\n", wingSpan, nest);
    }

}
