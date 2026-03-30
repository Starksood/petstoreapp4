package petstore.app;

import petstore.inventory.Animal;


public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Animal a1 = new Animal("Frank", "01-01-2026");
            System.out.println(a1);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}