import java.util.HashSet;

public class Hole extends Point{

//    private int boudaryType;
    private HashSet<Point> boundery;


    public Hole(int x, int y, int boundaryType, float[][] values) {

        super(x, y, -1);

        // for 4-connected boundary
        boundery.add(new Point(x, y, values[x - 1][y]));
        boundery.add(new Point(x, y, values[x][y - 1]));
        boundery.add(new Point(x, y, values[x + 1][y]));
        boundery.add(new Point(x, y, values[x][y + 1]));

        if (boundaryType == 8) {
            boundery.add(new Point(x, y, values[x - 1][y - 1]));
            boundery.add(new Point(x, y, values[x + 1][y - 1]));
            boundery.add(new Point(x, y, values[x + 1][y + 1]));
            boundery.add(new Point(x, y, values[x - 1][y + 1]));
        }
    }

    public HashSet<Point> getBoundery() {
        return boundery;
    }
}