package FillingHole;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

//TODO is enum needed?
enum Type { FOUR, EIGHT }

public class Image {


    private final float threshold = .5f;
    //TODO: add weighting function
//    private BufferedImage img; - NEEDED?
    private Point[][] pixels;
    private Set<Point> boundary;
    private Set<Point> hole;
    private float[][] mask; // NEEDED?
    private int height;
    private int width;
    private Type connectivity;



    public Image(BufferedImage image) {

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = new Point[height][width];
        this.mask = new float[height][width];

        // converts RGB to a float in the closed interval [0,1]
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                float value = (float) (red + green + blue) / 255;
                Point p = new Point(i ,j, value);
                pixels[i][j] = p;
                // updates mask
                if (value > .5) {
                    mask[i][j] = 0;
                } else {
                    // if p is part of the hole, added to the set
                    mask[i][j] = -1;
                    hole.add(p);
                }
                if (isBoundaryHole())
            }
        }
    }

    private boolean isHole(int y, int x) {
        return this.mask[y][x] == -1;
    }

    private createMatrices()

    private void createBoundary(Type c) {

        for (Point p : this.hole) {
            int x = p.getX();
            int y = p.getY();
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    if (i == j && c == Type.FOUR ) {
                        // if FOUR, skip diagonal neighbors
                        continue;
                    }
                    if (!isHole(y + i, x + j)) {
                        this.boundary.add(pixels[y + i][x + j]);
                    }
                }
            }
        }
    }

    private
}
