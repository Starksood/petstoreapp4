package petstore.inventory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Animal {
    private static int lastId = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");

    protected final int id;
    protected String name;
    protected LocalDate dateDOB;
    protected String description;

    public Animal(String name, String dateDOB) throws Exception {
        this.id = ++Animal.lastId;
        setName(name);
        setDateDOB(dateDOB);
        this.description = "";
    }

    public Animal(int id, String name, String dateDOB) throws Exception {
        this.id = id;
        setName(name);
        setDateDOB(dateDOB);
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.isBlank()) {
            throw new Exception("Invalid! Name can not be empty.");
        }

        this.name = name.trim();
    }

    public String getDateDOB() {
        return dateDOB.format(Animal.formatter);
    }

    public void setDateDOB(String dateDOB) throws Exception {
        try {
            this.dateDOB = LocalDate.parse(dateDOB, Animal.formatter);
        } catch (Exception e) {
            throw new Exception("Invalid date! Must be MM-DD-YYYY");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws Exception {
        this.description = description.trim();
    }

    @Override
    public String toString() {
        return "Animal Id = " + id + ", Name = " + name + ", Date of Birth =" + getDateDOB();
    }
}
