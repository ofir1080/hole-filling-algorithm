package cmdutil;

import holefill.Point;

@FunctionalInterface
public interface WeightFunction {

    float weight(Point p1, Point p2);
}

