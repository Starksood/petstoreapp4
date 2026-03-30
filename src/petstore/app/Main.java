package petstore.app;

import java.util.ArrayList;

public class Animal {
    private static final String INVENTORY_FILE = "libraryData.txt";
    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');
    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');
    private final List<Item> inventory;
    public Animal() {this.inventory = new ArrayList<>();
    }

    private void displayAPPHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the petstore.app.Animal Palace");
        System.out.println(DOUBLE_DASH_LINE);
    }

    private void deleteItem(){
        System.out.println("Delete Inventory");
        System.out.println(SINGLE_DASH_LINE);

        int id = Input.getInt("Please enter the ID of the item you want to delete");

        for (Item item: inventory){
            System.out.println(id);
            if
        }
    }


}






public class Animal {
    public static void main(String[] args) {
        Animal app = new Animal();
        app.displayAppHeading();

        try {
            app.MainMenu();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Sorry but this program ended with an error. Please Contact Sanyam");
        }

        Input.sc.close();
    }
}