public class ObtainScholarship implements Action {

    private int amount;

    public ObtainScholarship(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Student s) {
        s.receive(amount);
    }
}
