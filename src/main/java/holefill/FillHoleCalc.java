package holefill;

import cmdutil.WeightFunction;
import java.util.Set;


public class FillHoleCalc {

    public static void fillHole(Image image) {

        for (Point h : image.hole) {
            h.setValue(calcColor(h, image.boundary, image.weightFunc));
        }
    }

    public static float calcColor(Point h, Set<Point> B, WeightFunction w) {

        float numerator = 0;
        float denominator = 0;

        for (Point b : B) {
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