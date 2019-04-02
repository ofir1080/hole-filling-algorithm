package FillingHole;

//TODO is enum needed?
enum Type { FOREGROUND, BOUNDARY, HOLE }

public class Point {

    private int x;
    private int y;
    private float value;
//    private Type pointType;


    public Point(int x, int y, float value) {
        this.x = x;
        this.y = y;
        this.value = value;
//        this.pointType = t;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getValue() {
        return value;
    }
}
