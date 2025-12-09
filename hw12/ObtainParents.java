public class ObtainParents implements Action {

    private int amount;

    public ObtainParents(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(Student s) {
        s.receive(amount);
    }
}
