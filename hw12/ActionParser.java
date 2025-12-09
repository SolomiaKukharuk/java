public class ActionParser {

    public static Action parse(String line) {

        String[] tokens = line.split(" ");

        switch (tokens[0]) {

            case "teach":
                if (tokens[1].equals("humanitarian")) {
                    return new TeachHumanitarian();
                } else if (tokens[1].equals("natural")) {
                    int credits = Integer.parseInt(tokens[2]);
                    return new TeachNatural(credits);
                }
                break;

            case "pay":
                if (tokens[1].equals("hostel")) {
                    return new PayHostel(Integer.parseInt(tokens[2]));
                }
                if (tokens[1].equals("food")) {
                    return new PayFood(Integer.parseInt(tokens[2]));
                }
                break;

            case "obtain":
                if (tokens[1].equals("scholarship")) {
                    return new ObtainScholarship(Integer.parseInt(tokens[2]));
                }
                if (tokens[1].equals("parents")) {
                    return new ObtainParents(Integer.parseInt(tokens[2]));
                }
                break;
        }

        return s -> {}; // NO-OP якщо помилка
    }
}

