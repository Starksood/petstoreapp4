package petstore.inventory;

public class Fish extends Animal{
    private int finCount;
    private WaterType water;

    private Fish(String name, String dateOfBirth, int finCount, WaterType water) throws Exception{
        super(name, dateOfBirth);
        setFinCount(finCount);
        setWater(water);
    }

    public Fish(int id, String name, String dateOfBirth, int finCount, WaterType water) throws Exception{
        super(id, name, dateOfBirth);
        setFinCount(finCount);
        setWater(water);
    }

    public int getFinCount() {
        return finCount;
    }
    public void setFinCount(int finCount) {
        this.finCount = finCount;
    }
    public WaterType getWater() {
        return water;
    }
    public void setWater(WaterType water) {
        this.water = water;
    }
    @Override
    public void displayAnimal() {
        super.displayAnimal();
        System.out.printf(" %2d %-10s\n", finCount, water);
    }

}
