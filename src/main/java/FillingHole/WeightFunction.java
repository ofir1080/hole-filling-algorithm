package FillingHole;

@FunctionalInterface
interface WeightFunction {

    float weight(Point p1, Point p2);
}