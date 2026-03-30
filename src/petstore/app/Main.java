package petstore.app;


public class Animal {
    public static void main(String[] args) {
        try {

            Animal a1 = new Animal("Frank", "01-01-2026");
            System.out.println(a1);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}