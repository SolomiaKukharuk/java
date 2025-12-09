public class TeachHumanitarian implements Action {

    @Override
    public void apply(Student s) {
        if (s.getType() == StudentType.HUMANITARIAN ||
                s.getType() == StudentType.MIXED) {
            s.addCredits(3);
        }
        // Якщо студент не того профілю – нічого не робимо.
    }
}
