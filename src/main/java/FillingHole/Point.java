package FillingHole;


public class Point {

    private int x;
    private int y;
    private float value;
//    private Type pointType;


    public Point(int y, int x, float value) {
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
