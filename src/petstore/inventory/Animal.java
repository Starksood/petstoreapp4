package petstore.inventory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Animal in the pet store inventory.
 * Provides basic properties like name, date of birth, and unique ID.
 * 
 * @author Sanyam
 * @since March 29th, 2026
 */
public class Animal {
    private static int lastId = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");

    /** The unique identifier for the animal. */
    protected final int id;
    /** The name of the animal. */
    protected String name;
    /** The date of birth of the animal. */
    protected LocalDate dateDOB;
    /** A description of the animal. */
    protected String description;

    /**
     * Constructs a new Animal with a name and date of birth.
     * Automatically assigns a unique ID by incrementing the last used ID.
     * 
     * @param name    The name of the animal.
     * @param dateDOB The date of birth in MM-DD-YYYY format.
     * @throws Exception If the name is invalid or the date format is incorrect.
     */
    public Animal(String name, String dateDOB) throws Exception {
        this.id = ++Animal.lastId;
        setName(name);
        setDateDOB(dateDOB);
        this.description = "";
    }

    /**
     * Constructs a new Animal with a specific ID, name, and date of birth.
     * This constructor is typically used when loading existing data from a file.
     * 
     * @param id      The unique ID for the animal.
     * @param name    The name of the animal.
     * @param dateDOB The date of birth in MM-DD-YYYY format.
     * @throws Exception If the name is invalid or the date format is incorrect.
     */
    public Animal(int id, String name, String dateDOB) throws Exception {
        this.id = id;
        setName(name);
        setDateDOB(dateDOB);
        this.description = "";
    }

    /**
     * Gets the name of the animal.
     * 
     * @return The animal's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the animal.
     * 
     * @param name The new name of the animal.
     * @throws Exception If the name is null or blank.
     */
    public void setName(String name) throws Exception {
        if (name == null || name.isBlank()) {
            throw new Exception("Invalid! Name can not be empty.");
        }

        this.name = name.trim();
    }

    /**
     * Gets the date of birth as a formatted string.
     * 
     * @return The formatted date of birth (MM-DD-YYYY).
     */
    public String getDateDOB() {
        return dateDOB.format(Animal.formatter);
    }

    /**
     * Sets the date of birth from a string.
     * 
     * @param dateDOB The date of birth to set (MM-DD-YYYY).
     * @throws Exception If the date format is invalid.
     */
    public void setDateDOB(String dateDOB) throws Exception {
        try {
            this.dateDOB = LocalDate.parse(dateDOB, Animal.formatter);
        } catch (Exception e) {
            throw new Exception("Invalid date! Must be MM-DD-YYYY");
        }
    }

    /**
     * Gets the unique ID of the animal.
     * 
     * @return The animal's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the last used ID for the animal ID generator.
     * This ensures the next created animal will have a unique ID after loading from
     * a file.
     * 
     * @param lastId The last used ID.
     */
    public static void setLastId(int lastId) {
        Animal.lastId = lastId;
    }

    /**
     * Gets the description of the animal.
     * 
     * @return The animal's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the animal.
     * 
     * @param description The new description.
     * @throws Exception If description processing fails.
     */
    public void setDescription(String description) throws Exception {
        this.description = description.trim();
    }

    /**
     * Displays a summary of the animal's info to the console.
     */
    public void displayAnimal() {
        System.out.printf("%-3d %-15s %-10s", id, name, getDateDOB());
    }

    @Override
    public String toString() {
        return "Animal Id = " + id + ", Name = " + name + ", Date of Birth =" + getDateDOB();
    }
}
