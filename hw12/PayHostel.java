public class PayHostel implements Action {

    private int amount;

    public PayHostel(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Student s) {
        s.pay(amount);
    }
}

