package hole_filling_utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


enum CONNECTIVITY_TYPE { FOUR, EIGHT }

/**
 *  This class converts an image into a 2d array where each pixel value is a point with ​float a​ in the range ​[0, 1]​,
 *  and hole values which are marked with the value ​-1​
 *
 */
public class ImagePreprocess {

    /**
     * returns a 2d array of bool (mask). mask[i][j] is true iff its pixel tends to black (that is, a hole pixel in main image), false otherwise
     * @param maskPath
     * @return a 2 boolean array indicates 'hole pixels' in main image
     * @throws IOException
     */
    public boolean[][] convertMaskToBinary(String maskPath) throws IOException {

        BufferedImage maskBuffer = ImageIO.read(new File(maskPath));
        int width = maskBuffer.getWidth();
        int height = maskBuffer.getHeight();
        boolean[][] mask = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(maskBuffer.getRGB(j, i));
                mask[i][j] = rgbToGrayscale(c) > .5;
            }
        }
        return mask;
    }

    /**
     * return a 2d array of pixels type Point, where a hole pixel value is -1
     *
     * @param imagePath main image pathname
     * @param mask 2d boolean array represents a binary image
     * @return a 2d array of pixels with values in [0,1]
     */
    public static Point[][] convertToGrayscale(String imagePath, boolean[][] mask) throws IOException {

        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth();
        int height = image.getHeight();
        Point[][] pixels = new Point[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Color iColor = new Color(image.getRGB(j, i));
                float value = -1f;
                if (!mask[i][j]) {
                    // if pixel (j,i) is not a 'hole pixel' convert its color to grayscale
                    value = rgbToGrayscale(iColor);
                }
                // converts sum to a fraction in the closed interval [0,1]
                pixels[i][j] = new Point(i, j, value);
            }
        }
        return pixels;
    }

    private static float rgbToGrayscale(Color c) {

        // if pixel (i, j) is not a hole converts RGB values to grayscale using weights
        // (NOTE that weights sum is 1)
        int red = (int) (c.getRed() * 0.299);
        int green = (int) (c.getGreen() * 0.587);
        int blue = (int) (c.getBlue() * 0.114);

        return (float) (red + green + blue) / 255;
    }

    /**
     * return a set of all points where value is -1 (defined as a hole pixel)
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
     * @param pixels of main image converted to reqquired format
     * @param H set of all hole pixels ( defined with value -1)
     * @return set of all boundary pixels
     */
    public static Set<Point> findBoundary(Point[][] pixels, Set<Point> H, CONNECTIVITY_TYPE connectivity) {

        Set<Point> B = new HashSet();
        for (Point p : H) {
            int x = p.getX();
            int y = p.getY();
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    if (i == j && connectivity == CONNECTIVITY_TYPE.FOUR) {
                        // if FOUR, skip diagonal neighbors
                        continue;
                    }
                    if (isHole(pixels[y + i][x + j])) {
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
