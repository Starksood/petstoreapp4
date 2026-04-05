
package petstore.app;

import petstore.inventory.Animal;
import petstore.inventory.Fish;
import petstore.inventory.WaterType;
import petstore.inventory.Bird;
import petstore.inventory.NestType;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * The main application class for the PetStore App.
 * Manages an inventory of animals, providing functionalities to add, delete,
 * display, save, and load records.
 * 
 * @author Sanyam
 * @since March 29th, 2026
 * @version 1.1 (Debugging Complete)
 */
public class PetStoreApp {

    private static final String INVENTORY_FILE = "PetStoreData.txt";

    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    private final List<Animal> inventory;

    /**
     * Constructs a new PetStoreApp and initializes the inventory list.
     */
    public PetStoreApp() {
        this.inventory = new ArrayList<>();
    } // end of constructor

    /**
     * Displays the application heading.
     */
    private void displayAppHeading() {

        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the PetStore App");
        System.out.println(DOUBLE_DASH_LINE);

    } // end of displayAppHeading method

    /**
     * Deletes an animal from the inventory based on its ID.
     */
    private void deleteAnimal() {
        System.out.println("Delete Inventory");
        System.out.println(SINGLE_DASH_LINE);

        int id = Input.getInt("Please enter the inventory id: ");

        for (Animal Animal : inventory) {
            System.out.println(id);
            if (Animal.getId() == id) {
                inventory.remove(Animal);
                System.out.println("Successful Delete: " + Animal);
                Input.getLine("Press enter to continue...");
                return;
            }
        }

        System.out.println("ERROR: Inventory ID:" + id + " NOT found!");

    } // end of deleteAnimal method

    /**
     * Prompts the user for fish-specific information and creates a new Fish object.
     * 
     * @param name        Name of the fish.
     * @param dateDOB     Date of birth.
     * @param description A brief description.
     * @return A new Fish object.
     * @throws Exception If input validation fails.
     */
    private Fish addFish(String name, String dateDOB, String description) throws Exception {

        Fish Fish;
        int userInput;
        int finCount;
        WaterType WaterType = null;

        finCount = Input.getInt("finCount: ");

        try {
            userInput = Input.getIntRange("WaterType 1=Salt, 2=Fresh, 3=Both: ", 1, 3);
            WaterType = WaterType.values()[userInput - 1];
        } catch (Exception e) {
            throw new Exception("Invalid data! Fish WaterType = " + WaterType);
        }

        Fish = new Fish(name, dateDOB, finCount, WaterType);
        Fish.setDescription(description);

        return Fish;
    } // end of addFish method

    /**
     * Prompts the user for bird-specific information and creates a new Bird object.
     * 
     * @param Name        Name of the bird.
     * @param dateDOB     Date of birth.
     * @param description A brief description.
     * @return A new Bird object.
     * @throws Exception If input validation fails.
     */
    private Bird addBird(String Name, String dateDOB, String description) throws Exception {

        Bird Bird;
        int wingSpan;
        NestType NestType = null;

        wingSpan = Input.getInt("Wing Span: ");

        try {
            int userInput = Input.getIntRange("NestType 1=Burrow, 2=Cup, 3=Dome: ", 1, 3);
            NestType = NestType.values()[userInput - 1];
        } catch (Exception e) {
            throw new Exception("Invalid data! Bird NestType = " + NestType);
        }

        Bird = new Bird(Name, dateDOB, wingSpan, NestType);
        Bird.setDescription(description);

        return Bird;
    } // end of addBird method

    /**
     * Orchestrates the process of adding a new animal to the inventory.
     * 
     * @throws Exception If object creation or input fails.
     */
    private void addAnimal() throws Exception {

        System.out.println("Add Inventory");
        System.out.println(SINGLE_DASH_LINE);

        System.out.println("Please enter the following inventory information:");
        String Name = Input.getString("Name: ");
        String dateDOB = Input.getDate("Date DOB (MM-DD-YYYY): ");
        String description = Input.getLine("Description or press enter to continue: ");

        int inventoryType = Input.getIntRange("Type 1=Fish, 2=Bird: ", 1, 2);

        switch (inventoryType) {
            case 1:
                Fish b = addFish(Name, dateDOB, description);
                inventory.add(b);
                System.out.println("Successful Add: " + b);
                Input.getLine("Press enter to continue...");
                break;
            case 2:
                Bird p = addBird(Name, dateDOB, description);
                inventory.add(p);
                System.out.println("Successful Add: " + p);
                Input.getLine("Press enter to continue...");
                break;
            case 3:
                break;
            default:
                throw new Exception("Invalid Input! Inventory Type = " + inventoryType);
        } // end of switch

    } // end of addAnimal method

    /**
     * Displays all animals in the inventory, grouped by type.
     */
    private void displayInventory() {
        System.out.println("Fish Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Name           Date Rec'd finCount          WaterType");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (Animal Animal : inventory) {
            if (Animal instanceof Fish) {
                Animal.displayAnimal();
            }
        }
        System.out.println();

        System.out.println("Bird Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Name           Date Rec'd WingSpan      NestType");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (Animal Animal : inventory) {
            if (Animal instanceof Bird) {
                Animal.displayAnimal();
            }
        }
        System.out.println();

        Input.getLine("Press enter to continue...");
    } // end of displayInventory

    /**
     * Saves the current inventory to a pipe-delimited text file.
     */
    public void saveInventory() {
        System.out.println("Saving data! Please wait...");

        /*
         * Try-With-Resources: shortcut way to declare and initialize in one step
         * when you use this way of opening the file as part of the try statement
         * Java will automatically close the file so there is no need to write a close
         * statement
         * NOTE: Java doesn't automatically close the file if the file is opened inside
         * the block
         */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PetStoreApp.INVENTORY_FILE))) {

            for (Animal Animal : inventory) {

                // The file will be piped delimited so each field is separated by a |
                if (Animal instanceof Fish)
                    bw.write("Fish|");
                else if (Animal instanceof Bird)
                    bw.write("Bird|");

                bw.write(Animal.getId() + "|" + Animal.getName() + "|" + Animal.getDateDOB() + "|"
                        + Animal.getDescription() + "|");

                if (Animal instanceof Fish)
                    bw.write(((Fish) Animal).getFinCount() + "|" + ((Fish) Animal).getWaterType() + "\n");
                else if (Animal instanceof Bird)
                    bw.write(((Bird) Animal).getWingSpan() + "|" + ((Bird) Animal).getNestType() + "\n");
            }

            bw.flush();
            // No explicit close needed - automatically handled when using
            // Try-With-Resources

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out
                .println(inventory.size() + " Inventory records successfully written to " + PetStoreApp.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");

    }

    /**
     * Loads the inventory from a pipe-delimited text file.
     * Restores the ID counter based on the last record loaded.
     */
    public void loadInventory() {
        System.out.println("Loading data! Please wait...");

        inventory.clear(); // empty the ArrayList so we can load the data from the file

        /*
         * Try-With-Resources: shortcut way to declare and initialize in one step
         * when you use this way of opening the file as part of the try statement
         * Java will automatically close the file so there is no need to write a close
         * statement
         * NOTE: Java doesn't automatically close the file if the file is opened inside
         * the block
         */
        try (BufferedReader br = new BufferedReader(new FileReader(PetStoreApp.INVENTORY_FILE))) {

            String inLine;

            while ((inLine = br.readLine()) != null) { // exclude newline

                String[] data = inLine.split("[|]"); // [|] is a regex for splitting by the pipe character

                // 0=Animal 1=id, 2=Name, 3=date, 4=description, 5=finCount/SALT,
                // 6=WaterType/NestType
                switch (data[0]) {
                    case "Fish":
                        Fish b = new Fish(Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[5]),
                                WaterType.valueOf(data[6]));
                        b.setDescription(data[4]);
                        inventory.add(b);
                        break;
                    case "Bird":
                        Bird p = new Bird(Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[5]),
                                NestType.valueOf(data[6]));
                        p.setDescription(data[4]);
                        inventory.add(p);
                        break;
                    default:
                        throw new Exception("Invalid inventory type: " + data[0]);
                } // end of switch

            } // end of while loop

            // No explicit close needed - automatically handled when using
            // Try-With-Resources

            Animal.setLastId(inventory.get(inventory.size() - 1).getId());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } // end of try-catch

        System.out
                .println(
                        inventory.size() + " Inventory records successfully loaded from " + PetStoreApp.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");
    } // end of loadInventory method

    /**
     * The main menu loop of the application.
     * 
     * @throws Exception If an unhandled error occurs during execution.
     */
    private void mainMenu() throws Exception {

        boolean keepRunning = true;

        while (keepRunning) {

            System.out.println(SINGLE_DASH_LINE);
            System.out.println("Main Menu");
            System.out.println(SINGLE_DASH_LINE);

            System.out.println("0 = End Program");
            System.out.println("1 = Add Animal");
            System.out.println("2 = Delete Animal");
            System.out.println("3 = Display Inventory");
            System.out.println("4 = Save Inventory");
            System.out.println("5 = Load Inventory");

            System.out.println(SINGLE_DASH_LINE);
            int userInput = Input.getIntRange("Menu Choice: ", 0, 5);
            System.out.println(SINGLE_DASH_LINE);

            switch (userInput) {
                case 0:
                    keepRunning = false;
                    break;
                case 1:
                    try {
                        this.addAnimal();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Input.getLine("Press enter to continue...");
                    }
                    break;
                case 2:
                    try {
                        this.deleteAnimal();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Input.getLine("Press enter to continue...");
                    }
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    saveInventory();
                    break;
                case 5:
                    loadInventory();
                    break;
                default:
                    throw new Exception("Invalid menu choice: " + userInput);

            } // end of switch
        } // end of while loop
    } // end of mainMenu

    /**
     * Entry point of the application.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        PetStoreApp app = new PetStoreApp();

        app.displayAppHeading();

        try {
            app.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Sorry but this program ended with an error. Please contact Princess Debbie!");
        }

        Input.sc.close();

    } // end of main method

} // end of PetStoreApp class