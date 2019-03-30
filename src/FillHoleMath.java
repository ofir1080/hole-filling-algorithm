

public class FillHoleMath {

    //TODO: set z and epsilon to final
    static float z = 2;
    static float epsilon = 0.001f;


    // TODO: try to deliver (weight) function
    public static float calcColor(Hole h) {

        float numerator = 0;
        float denominator = 0;

        for (Point p : h.getBoundery()) {
            float weight = calcWeight(h, p);
            numerator += weight * p.getValue();
            denominator += weight;
        }

        return numerator / denominator;
    }

    private static float calcWeight(Point h, Point p) {
        return (float) (1 / (Math.pow(euclideanDist(h , p), z)) + epsilon);
    }

    private static float euclideanDist(Point p1, Point p2) {

        float xVal = p1.getX() - p2.getX();
        float yVal = p1.getY() - p2.getY();
        return (float) (Math.sqrt(xVal * xVal + yVal * yVal));
    }

}
