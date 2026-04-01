package petstore.app;

import petstore.inventory.Animal;


public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Animal a1 = new Animal(" Potato ", "01-01-2026");
            a1.setDescription("Animal Description");
            System.out.println(a1);
            System.out.println(a1.getDescription());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}