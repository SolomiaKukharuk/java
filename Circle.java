import java.util.*;

public class Circle {
    private final double x, y;
    private final double r;
    private static final double EPS = 1e-9;

    public Circle(double x, double y, double r) {
        if (r < 0) throw new IllegalArgumentException("Радіус не може бути від’ємним");
        this.x = x; this.y = y; this.r = r;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getR() { return r; }

    public double area() { return Math.PI * r * r; }
    public double perimeter() { return 2 * Math.PI * r; }

    public boolean intersects(Circle other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double d = Math.sqrt(dx*dx + dy*dy);
        double sumR = this.r + other.r;
        double diffR = Math.abs(this.r - other.r);
        return d + EPS >= diffR && d <= sumR + EPS;
    }

    public static List<int[]> findIntersectingPairs(Circle[] a) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i].intersects(a[j])) res.add(new int[]{i, j});
            }
        }
        return res;
    }

    public static Circle maxAreaCircle(Circle[] a) {
        if (a == null || a.length == 0) throw new IllegalArgumentException("Масив порожній");
        Circle best = a[0];
        double bestArea = best.area();
        for (int i = 1; i < a.length; i++) {
            double ar = a[i].area();
            if (ar > bestArea + EPS) {
                best = a[i];
                bestArea = ar;
            }
        }
        return best;
    }
}
