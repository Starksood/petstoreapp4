package petstore.inventory;

public class Bird extends Animal{
    private int wingSpan;
    private NestType nest;

    public Bird(String name, String dateOfBirth, int wingSpan, NestType nest) throws Exception{
        super(name, dateOfBirth);
        setWingSpan(wingSpan);
        setNest(nest);
    }


    public Bird(int id, String name, String dateOfBirth, int wingSpan, NestType nest) throws Exception{
        super(id, name, dateOfBirth);
        setWingSpan(wingSpan);
        setNest(nest);
    }

    private void setNest(NestType nest) {
        this.nest = nest;
    }

    public int getWingSpan() {
        return wingSpan;
    }
    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }
    public NestType getNest() {
        return nest;
    }
    public void setWater(WaterType water) {
        this.nest = nest;
    }
    @Override
    public void displayAnimal() {
        super.displayAnimal();
        System.out.printf(" %2d %-10s\n", wingSpan, nest);
    }

}
