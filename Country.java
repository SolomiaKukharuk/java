public class Country {
    private final String name;
    private final double area;
    private final String continent;

    public Country(String name, double area, String continent) {
        this.name = name;
        this.area = area;
        this.continent = continent;
    }

    public String getName() { return name; }
    public double getArea() { return area; }
    public String getContinent() { return continent; }

    @Override
    public String toString() {
        return name + " (" + continent + "), площа = " + area;
    }
}

