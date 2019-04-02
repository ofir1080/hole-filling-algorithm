package hole_filling_utils;

@FunctionalInterface
public interface WeightFunction {

    float weight(Point p1, Point p2);
}

