package holefill;

import cmdutil.WeightFunction;
import java.util.Set;


public class FillHoleCalc {

    public static void fillHole(Point[][] pixels, WeightFunction w) {

        Set<Point> H = ImageProcess.findHole(pixels);
        Set<Point> B = ImageProcess.findBoundary(pixels, H, CONNECTIVITY_TYPE.EIGHT);
        for (Point h : H) {
            h.setValue(calcColor(h, B, w));
        }
    }

    private static float calcColor(Point h, Set<Point> B, WeightFunction w) {

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