public class PayFood implements Action {

    private int amount;

    public PayFood(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Student s) {
        s.pay(amount);
    }
}

