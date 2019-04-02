package hole_filling_utils;

//import unused.Image;

import java.util.Set;

public class FillHoleMath {

    //TODO: set z and epsilon to final
    static float z = 2;
    static float epsilon = 0.001f;


    public static void fillHole(Set<Point> H, Set<Point> B) {

        // shall be called outside
        WeightFunction w = (Point u, Point v) -> (float) (1 / (Math.pow(euclideanDist(u , v), z)) + epsilon);

        for (Point h : H) {
            h.setValue(calcColor(h, B, w));
        }
    }

    private static float calcColor(Point h, Set<Point> B, WeightFunction w) {

        float numerator = 0;
        float denominator = 0;

        for (Point b :B) {
            float weight = w.weight(h, b);
            numerator += weight * b.getValue();
            denominator += weight;
        }

        return numerator / denominator;
    }

    public static float euclideanDist(Point p1, Point p2) {

        float xVal = p1.getX() - p2.getX();
        float yVal = p1.getY() - p2.getY();
        return (float) (Math.sqrt(xVal * xVal + yVal * yVal));
    }
}