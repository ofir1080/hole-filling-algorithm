import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;


public class Image {


    //TODO: add boundary type
    //TODO: add weighting function

//    private BufferedImage img;
    private Point[][] values;
    private HashSet<Point> boundary;
    private HashSet<Point> hole;
    private int height;
    private int width;

    private boolean[][] mask;


    public Image(BufferedImage image) {

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.values = new Point[height][width];
        this.mask = new boolean[height][width];

        // converts RGB to a float in the closed interval [0,1]
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                // sets black pixel to 0 to prevent division by 0.
                float value = (float) (red + green + blue) / 255;
                mask[i][j] = isPixelHole(i, j);
                System.out.print(mask[i][j] + " ");
            }
            System.out.println();
        }

    }

    //TODO
    private void findHole() {

    }

    private boolean isPixelHole(int x, int y) {
        return this.values[x][y].getValue() == -1;
    }

    //TODO
    private void fillHole() {

    }

}
