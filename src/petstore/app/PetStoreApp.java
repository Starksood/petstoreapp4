
package library.app;

import library.inventory.Animal;
import library.inventory.Fish;
import library.inventory.Genre;
import library.inventory.Bird;
import library.inventory.Category;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Sanyam
 *  @since March 29th, 2026
 *  @version 1.0 beta
 *  @see <a href="{PUT_YOUR_URL_HERE}">GitHub Repository</a>
 *
 */
public class LibraryApp {

    private static final String INVENTORY_FILE = "LibraryData.txt";

    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    private final List<Animal> inventory;

    public LibraryApp(){
        this.inventory = new ArrayList<>();
    } // end of constructor

    private void displayAppHeading() {

        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Library App");
        System.out.println(DOUBLE_DASH_LINE);

    } // end of displayAppHeading method

    private void deleteAnimal(){
        System.out.println("Delete Inventory");
        System.out.println(SINGLE_DASH_LINE);

        int id = Input.getInt("Please enter the inventory id: ");

        for (Animal Animal : inventory){
            System.out.println(id);
            if (Animal.getId() == id){
                inventory.remove(Animal);
                System.out.println("Successful Delete: " + Animal);
                Input.getLine("Press enter to continue...");
                return;
            }
        }

        System.out.println("ERROR: Inventory ID:" + id + " NOT found!");

    } // end of deleteAnimal method

    private Fish addFish(String title, String dateReceived, String description) throws Exception {

        Fish Fish;
        int userInput;
        String author;
        Genre genre = null;

        author = Input.getString("Author: ");

        try {
            userInput = Input.getIntRange("Genre 1=Fiction, 2=Children, 3=Poetry: ", 1, 3);
            genre = Genre.values()[userInput - 1];
        } catch (Exception e){
            throw new Exception("Invalid data! Fish Genre = " + genre);
        }

        Fish = new Fish(title, dateReceived, author, genre);
        Fish.setDescription(description);

        return Fish;
    } // end of addFish method

    private Bird addBird(String title, String dateReceived, String description) throws Exception {

        Bird Bird;
        String publisher;
        Category category = null;

        publisher = Input.getString("Publisher: ");

        try {
            int userInput = Input.getIntRange("Category 1=Magazine, 2=Journal, 3=Newspaper: ", 1, 3);
            category = Category.values()[userInput - 1];
        } catch (Exception e){
            throw new Exception("Invalid data! Bird Category = " + category);
        }

        Bird = new Bird(title, dateReceived, publisher, category);
        Bird.setDescription(description);

        return Bird;
    } // end of addBird method

    private void addAnimal() throws Exception {

        System.out.println("Add Inventory");
        System.out.println(SINGLE_DASH_LINE);

        System.out.println("Please enter the following inventory information:");
        String title = Input.getString("Title: ");
        String dateReceived = Input.getDate("Date Received (MM-DD-YYYY): ");
        String description = Input.getLine("Description or press enter to continue: ");

        int inventoryType = Input.getIntRange("Type 1=Fish, 2=Bird: ", 1, 2);

        switch(inventoryType){
            case 1:
                Fish b = addFish(title, dateReceived, description);
                inventory.add(b);
                System.out.println("Successful Add: " + b);
                Input.getLine("Press enter to continue...");
                break;
            case 2:
                Bird p = addBird(title, dateReceived, description);
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

    private void displayInventory(){
        System.out.println("Fish Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Title           Date Rec'd Author          Genre");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (Animal Animal : inventory) {
            if (Animal instanceof Fish){
                Animal.displayAnimal();
            }
        }
        System.out.println();

        System.out.println("Bird Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Title           Date Rec'd Publisher       Category");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (Animal Animal : inventory) {
            if (Animal instanceof Bird){
                Animal.displayAnimal();
            }
        }
        System.out.println();

        Input.getLine("Press enter to continue...");
    } // end of displayInventory

    public void saveInventory(){
        System.out.println("Saving data! Please wait...");

        /*
        Try-With-Resources: shortcut way to declare and initialize in one step
        when you use this way of opening the file as part of the try statement
        Java will automatically close the file so there is no need to write a close statement
        NOTE: Java doesn't automatically close the file if the file is opened inside the block
        */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LibraryApp.INVENTORY_FILE))) {

            for(Animal Animal : inventory){

                // The file will be piped delimited so each field is separated by a |
                if (Animal instanceof Fish)
                    bw.write("Fish|");
                else if (Animal instanceof Bird)
                    bw.write("Bird|");

                bw.write(Animal.getId() + "|" + Animal.getTitle() + "|" + Animal.getDateReceived() + "|" + Animal.getDescription() + "|");

                if (Animal instanceof Fish)
                    bw.write(((Fish) Animal).getAuthor() + "|" + ((Fish) Animal).getGenre() + "\n");
                else if (Animal instanceof Bird)
                    bw.write(((Bird) Animal).getPublisher() + "|" + ((Bird) Animal).getCategory() + "\n");
            }

            bw.flush();
            // No explicit close needed - automatically handled when using Try-With-Resources

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(inventory.size() + " Inventory records successfully written to " + LibraryApp.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");

    }

    public void loadInventory(){
        System.out.println("Loading data! Please wait...");

        inventory.clear(); //empty the ArrayList so we can load the data from the file

        /*
        Try-With-Resources: shortcut way to declare and initialize in one step
        when you use this way of opening the file as part of the try statement
        Java will automatically close the file so there is no need to write a close statement
        NOTE: Java doesn't automatically close the file if the file is opened inside the block
        */
        try (BufferedReader br = new BufferedReader(new FileReader(LibraryApp.INVENTORY_FILE))) {

            String inLine;

            while ((inLine = br.readLine()) != null) {  // exclude newline

                String[] data = inLine.split("[|]"); // [|] is a regex for splitting by the pipe character

                //0=Animal 1=id, 2=title, 3=date, 4=description, 5=author/publisher, 6=genre/category
                switch(data[0]){
                    case "Fish":
                        Fish b = new Fish(Integer.parseInt(data[1]), data[2], data[3], data[5], Genre.valueOf(data[6]));
                        b.setDescription(data[4]);
                        inventory.add(b);
                        break;
                    case "Bird":
                        Bird p = new Bird(Integer.parseInt(data[1]), data[2], data[3], data[5], Category.valueOf(data[6]));
                        p.setDescription(data[4]);
                        inventory.add(p);
                        break;
                    default:
                        throw new Exception("Invalid inventory type: " + data[0]);
                } // end of switch

            } // end of while loop

            // No explicit close needed - automatically handled when using Try-With-Resources

            Animal.setLastId(inventory.get(inventory.size() - 1).getId());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } // end of try-catch

        System.out.println(inventory.size() + " Inventory records successfully loaded from " + LibraryApp.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");
    } // end of loadInventory method

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
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        Input.getLine("Press enter to continue...");
                    }
                    break;
                case 2:
                    try {
                        this.deleteAnimal();
                    } catch (Exception e){
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

    public static void main(String[] args) {

        LibraryApp app = new LibraryApp();

        app.displayAppHeading();

        try {
            app.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Sorry but this program ended with an error. Please contact Princess Debbie!");
        }

        Input.sc.close();

    } // end of main method

} // end of LibraryApp class