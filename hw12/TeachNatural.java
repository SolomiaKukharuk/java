public class TeachNatural implements Action {

    private int credits;

    public TeachNatural(int credits) {
        this.credits = credits;
    }

    @Override
    public void apply(Student s) {
        if (s.getType() == StudentType.NATURAL ||
                s.getType() == StudentType.MIXED) {
            s.addCredits(credits);
        }
    }
}
