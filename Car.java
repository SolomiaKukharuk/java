import java.util.*;
import java.time.Year;
public class Car {
    private String brand;
    private String model;
    private int year;
    private String color;

    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getColor() { return color; }

    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ", " + color + ")";
    }

    public static Car[] filterByBrand(Car[] cars, String brand) {
        return Arrays.stream(cars)
                .filter(c -> c.getBrand().equalsIgnoreCase(brand))
                .sorted(Comparator.comparingInt(Car::getYear))
                .toArray(Car[]::new);
    }

    public static Car[] filterByModelAndAge(Car[] cars, String model, int n) {
        int currentYear = Year.now().getValue();
        return Arrays.stream(cars)
                .filter(c -> c.getModel().equalsIgnoreCase(model))
                .filter(c -> currentYear - c.getYear() > n)
                .toArray(Car[]::new);
    }
}
