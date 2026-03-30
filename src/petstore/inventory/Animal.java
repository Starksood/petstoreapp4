package library.app;

import library.inventory.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Animal {
    private static int lastId = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");

    protected final int id;
    protected String name;
    protected LocalDate dateDOB;
    protected String description;

    public Animal(String name, String dateReceived) throws Exception {
        this.id = ++Animal.lastId;
        setName(title);
        setDateDOB(dateDOB);
    }

    public Animal(int id, String title, String dateReceived) throws Exception {
        this.id = id;
        setName(name);
        setDateDOB(dateDOB);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        name = name.trim();

        if (name.isBlank()){
            throw new Exception("Invalid! Title can not be empty.");
        }

        this.name = name;
    }

    public String getDateDOB() {
        return dateDOB.format(Item.formatter);
    }

    public void setDateDOB(String dateDOB) throws Exception {
        try {
            this.dateDOB = LocalDate.parse(dateDOB, Item.formatter);
        } catch (Exception e){
            throw new Exception("Invalid date! Must be MM-DD-YYYY");
        }
    }

}