package holefill;


public class Point {

    private int x;
    private int y;
    private float value;


    public Point(int y, int x, float value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
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
