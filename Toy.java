import java.io.Serializable;

public class Toy implements Serializable {
    private String name;
    private double price;
    private int ageMin;
    private int ageMax;

    public Toy(String name, double price, int ageMin, int ageMax) {
        this.name = name;
        this.price = price;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
    }

    public boolean isSuitableForAge(int age) {
        return age >= ageMin && age <= ageMax;
    }

    @Override
    public String toString() {
        return name + " (" + price + " грн, вік " + ageMin + "-" + ageMax + ")";
    }
}

