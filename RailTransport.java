import java.util.*;


class RailTransport {
    private String type;

    public RailTransport(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}


class Carriage {
    private String name;
    private int passengerCount;
    private int baggageCapacity;
    private int comfortLevel;

    public Carriage(String name, int passengerCount, int baggageCapacity, int comfortLevel) {
        this.name = name;
        this.passengerCount = passengerCount;
        this.baggageCapacity = baggageCapacity;
        this.comfortLevel = comfortLevel;
    }

    public String getName() { return name; }
    public int getPassengerCount() { return passengerCount; }
    public int getBaggageCapacity() { return baggageCapacity; }
    public int getComfortLevel() { return comfortLevel; }

    @Override
    public String toString() {
        return String.format("%s (пасажири=%d, багаж=%d, комфорт=%d)",
                name, passengerCount, baggageCapacity, comfortLevel);
    }
}

class PassengerTrain extends RailTransport {
    private List<Carriage> carriages;

    public PassengerTrain(String type) {
        super(type);
        this.carriages = new ArrayList<>();
    }

    public void addCarriage(Carriage c) {
        carriages.add(c);
    }

    public int totalPassengers() {
        return carriages.stream().mapToInt(Carriage::getPassengerCount).sum();
    }

    public int totalBaggage() {
        return carriages.stream().mapToInt(Carriage::getBaggageCapacity).sum();
    }

    public void sortByComfort() {
        carriages.sort(Comparator.comparingInt(Carriage::getComfortLevel).reversed());
    }

    public List<Carriage> findByPassengerRange(int min, int max) {
        List<Carriage> res = new ArrayList<>();
        for (Carriage c : carriages) {
            if (c.getPassengerCount() >= min && c.getPassengerCount() <= max)
                res.add(c);
        }
        return res;
    }

    public void showTrainInfo() {
        System.out.println("\n=== Потяг: " + getType() + " ===");
        for (Carriage c : carriages) System.out.println(c);
        System.out.printf("→ Загальна кількість пасажирів: %d\n", totalPassengers());
        System.out.printf("→ Загальний багаж: %d кг\n", totalBaggage());
    }
}
