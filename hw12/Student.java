public class Student {

    private StudentType type;
    private int requiredCredits;
    private int credits;
    private int money;
    private boolean expelled = false;

    public Student(StudentType type, int requiredCredits, int money) {
        this.type = type;
        this.requiredCredits = requiredCredits;
        this.money = money;
        this.credits = 0;
    }

    public StudentType getType() {
        return type;
    }

    public void addCredits(int c) {
        this.credits += c;
    }

    public void pay(int amount) {
        money -= amount;
        if (money < 0) {
            expelled = true;
        }
    }

    public void receive(int amount) {
        money += amount;
    }

    public boolean isExpelled() {
        return expelled;
    }

    public boolean hasDiploma() {
        return !expelled && credits >= requiredCredits;
    }
}
