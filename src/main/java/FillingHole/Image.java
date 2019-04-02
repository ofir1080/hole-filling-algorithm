package FillingHole;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;


public class Image {


    //TODO: add weighting function
//    private BufferedImage img; - NEEDED?
    private Point[][] values;
    private HashSet<Point> boundary;
    private HashSet<Point> hole;
    private float[][] mask; // NEEDED?
    private int height;
    private int width;



    public Image(BufferedImage image) {

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.values = new Point[height][width];
        this.mask = new float[height][width];

        // converts RGB to a float in the closed interval [0,1]
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                float value = (float) (red + green + blue) / 255;
                values[i][j] = new Point(i, j, value);
                mask[i][j] = value > .5 ? 0 : -1;
            }
        }

    }

    private boolean isHole(int x, int y) {
        return this.values[x][y].getValue() == -1;
    }


    private HashSet<Point> findBoundary(Point[][] values, float[][] mask, Type t) {
        return null;

    }
}
