package holefill;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 *  an image processing utilities for object Image
 *
 */
public class ImageProcess {

    /**
     * return a 2d array of pixels type Point, where a hole pixel value is -1, otherwise converts its value (color) to fractional grayscale value
     *
     * @param imagePath
     * @param maskPath
     * @return 2d array of pixels in the required format
     * @throws IOException
     */
    public static Point[][] preprocess(String imagePath, String maskPath) {

        Point[][] pixels = null;
        float value;
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImage maskBuffer = ImageIO.read(new File(maskPath));
            int width = maskBuffer.getWidth();
            int height = maskBuffer.getHeight();
            pixels = new Point[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {

                    Color c = new Color(maskBuffer.getRGB(j, i));
                    if (rgbToGrayscale(c) > .5) {
                        // if pixel (j,i) is not a 'hole pixel' convert its color to grayscale
                        Color pixelColor = new Color(image.getRGB(j, i));
                        value = rgbToGrayscale(pixelColor);
                    } else {
                        value = -1f;
                    }
                    pixels[i][j] = new Point(i, j, value);
                }
            }
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
            System.exit(1);
        }

        return pixels;
    }

    /**
     * converts an RGB color to grayscale using avg, then divides it by 255 to get a value in interval [0,1]
     *
     * @param c color
     * @return grayscaled normalized value
     */
    private static float rgbToGrayscale(Color c) {

        float avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
        return avg / 255;
    }

    /**
     * return a set of all points where value is -1 (defined as a hole pixel)
     *
     * @param pixels of main image converted to required format
     * @return the hole
     */
    public static Set<Point> findHole(Point[][] pixels) {

        Set<Point> H = new HashSet();
        for (int i = 1; i < pixels.length - 1; i++) {
            for (int j = 1; j < pixels[0].length - 1; j++) {
                if (pixels[i][j].getValue() == -1) {
                    H.add(pixels[i][j]);
                }
            }
        }

        return H;
    }

    /**
     * returns a set of all points (not hole) which are connected to the hole
     *
     * @param pixels of main image converted to reqquired format
     * @param H      set of all hole pixels ( defined with value -1)
     * @return set of all boundary pixels
     */
    public static Set<Point> findBoundary(Point[][] pixels, Set<Point> H, int cType) {

        Set<Point> B = new HashSet<>();
        for (Point p : H) {
            int x = p.getX();
            int y = p.getY();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (cType == 4 && Math.abs(i) + Math.abs(j) == 2) {
                        // if FOUR, skip diagonal neighbors
                        continue;
                    }
                    if (!isHole(pixels[y + i][x + j])) {
                        B.add(pixels[y + i][x + j]);
                    }
                }
            }
        }

        return B;
    }

    private static boolean isHole(Point p) {
        return p.getValue() == -1;
    }
}
