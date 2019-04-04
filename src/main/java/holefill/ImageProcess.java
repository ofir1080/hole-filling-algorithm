package holefill;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


enum CONNECTIVITY_TYPE { FOUR, EIGHT }

/**
 *  This class converts an image into a 2d array where each pixel value is a point with ​float a​ in the range ​[0, 1]​,
 *  and hole values which are marked with the value ​-1​
 *
 */
public class ImageProcess {

    /**
     * return a 2d array of pixels type Point, where a hole pixel value is -1, otherwise converts its value (color) to fractional grayscale value
     * @param imagePath
     * @param maskPath
     * @return 2d array of pixels in the required format
     * @throws IOException
     */
    public static Point[][] preprocess(String imagePath, String maskPath) throws IOException {

        BufferedImage image = ImageIO.read(new File(imagePath));
        BufferedImage maskBuffer = ImageIO.read(new File(maskPath));
        int width = maskBuffer.getWidth();
        int height = maskBuffer.getHeight();
        Point[][] pixels = new Point[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                float value = -1f;
                Color c = new Color(maskBuffer.getRGB(j, i));
//                System.out.print(maskBuffer.getRGB(j, i));
                if (rgbToGrayscale(c) > .5) {
                    // if pixel (j,i) is not a 'hole pixel' convert its color to grayscale
                    Color pixelColor = new Color(image.getRGB(j, i));
                    value = rgbToGrayscale(pixelColor);
                }
                pixels[i][j] = new Point(i, j, value);
//                System.out.print(value);
            }
//            System.out.println();
        }
        return pixels;
    }

//    /**
//     * return a 2d array of pixels type Point, where a hole pixel value is -1
//     *
//     * @param imagePath main image pathname
//     * @param mask 2d boolean array represents a binary image
//     * @return a 2d array of pixels with values in [0,1]
//     */
//    public static Point[][] convertToGrayscale(String imagePath, boolean[][] mask) throws IOException {
//
//        BufferedImage image = ImageIO.read(new File(imagePath));
//        int width = image.getWidth();
//        int height = image.getHeight();
//        Point[][] pixels = new Point[height][width];
//
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//
//                Color pixelColor = new Color(image.getRGB(j, i));
//                float value = -1f;
//                if (!mask[i][j]) {
//                    // if pixel (j,i) is not a 'hole pixel' convert its color to grayscale
//                    value = rgbToGrayscale(pixelColor);
//                }
//                // converts sum to a fraction in the closed interval [0,1]
//                pixels[i][j] = new Point(i, j, value);
//
//            }
//        }
//
//        return pixels;
//    }

    /**
     * converts an RGB color to grayscale using avg, then divides it by 255 to get a value in interval [0,1]
     * @param c color
     * @return grayscaled normalized value
     */
    private static float rgbToGrayscale(Color c) {

        float avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
        return avg / 255;
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
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (connectivity == CONNECTIVITY_TYPE.FOUR && Math.abs(i) + Math.abs(j) == 2) {
                        // if FOUR, skip diagonal neighbors
                        continue;
                    }
                    if (!isHole(pixels[y + i][x + j])) {
                        B.add(pixels[y + i][x + j]);
//                        System.out.println("(" + (y + j) + "," + (x + i) + ")");
                    }
                }
            }
        }
        return B;
    }

    private static boolean isHole(Point p) {
        return p.getValue() == -1;
    }

    /**
     * creates an image file related to the matrix with a filled hole
     * @param pixels matrix represent final pixel values
     * @param pathname
     * @throws IOException
     */
    public static void fillAndSave(Point[][] pixels, String pathname) throws IOException {

        // extracts PATH+name and format
        Pattern p = Pattern.compile("(.*)\\.(.*)");
        Matcher m = p.matcher(pathname);
        m.find();
        String path = m.group(1);
        String format = m.group(2);

        BufferedImage filledImage = new BufferedImage(pixels[0].length, pixels.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                // iterates all pixels and creates the related color value
                float holePixelColor = pixels[i][j].getValue();
//                System.out.println(holePixelColor);
                Color c = new Color(holePixelColor, holePixelColor, holePixelColor);
                filledImage.setRGB(j, i, c.getRGB());
            }
        }
        File output = new File( "/Users/ofir1080/dev/HoleFillingUtility/images/hole_test_FILLED.jpg" );//path + "_FILLED." + format);
        ImageIO.write(filledImage, format, output);
        System.out.println("Saved image in current directory.");
    }

}
