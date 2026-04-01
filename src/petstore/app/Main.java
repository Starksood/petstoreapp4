package petstore.app;

import petstore.inventory.Animal;
import petstore.inventory.Fish;
import petstore.inventory.WaterType;


public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Animal a1 = new Animal(" Potato ", "01-01-2026");
            a1.setDescription("Animal Description");
            a1.displayAnimal();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            Fish f1 = new Fish(" Fries ", "01-01-2026", 2, WaterType.SALT);
            f1.displayAnimal();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}