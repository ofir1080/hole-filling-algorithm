//package unused;
//
//import Point;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.util.HashSet;
//import java.util.Set;
//
////TODO is enum needed?
//
//public class Image {
//
//
//    private final float threshold = .5f; // default threshold to separate hole pixels
//    //TODO: add weighting function
//    public Point[][] pixels;
//    private Set<Point> boundary;
//    private Set<Point> hole;
//    private float[][] mask; // NEEDED?
//    private int height;
//    private int width;
//    private Type connectivity;
//
//
//    public Image(BufferedImage bImage, Type connectivity) {
//
//        this.width = bImage.getWidth();
//        this.height = bImage.getHeight();
//        this.connectivity = connectivity;
//        this.pixels = new Point[height][width];
//        this.mask = new float[height][width]; // NEEDED?
//        this.hole = new HashSet<>();
//        this.boundary = new HashSet<>();
//        createMatrices(bImage);
//        findBoundary();
//    }
//
//
//    private boolean isHole(int y, int x) {
//        System.out.println(y + " " + x);
//        return this.mask[y][x] == -1;
//    }
//
//
//    public Set<Point> getBoundary() {
//        return boundary;
//    }
//
//    public Set<Point> getHole() {
//        return hole;
//    }
//
//    /**
//     * creates matrices pixel & mask
//     * @param bImage
//     */
//    private void createMatrices(BufferedImage bImage) {
//        // converts RGB to a float in the closed interval [0,1]
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                Color c = new Color(bImage.getRGB(j, i));
//
//                // converts RGB values  by weight before converting to grayscale (NOTE that weights converge to 1)
//                int red = (int) (c.getRed() * 0.299);
//                int green = (int) (c.getGreen() * 0.587);
//                int blue = (int) (c.getBlue() * 0.114);
//
//                // converts sum to a fraction in the closed interval [0,1]
//                float frac_value = (float) (red + green + blue) / 255;
//                float value = frac_value > .5 ? frac_value : -1;
//                Point p = new Point(i, j, value);
//                if (frac_value < .5) {
//                    pixels[i][j] = p;
//                }
//
//                // updates mask
//                if (value > threshold) {
//                    mask[i][j] = 0;
//                } else {
//                    // if p is part of the hole, added to the set
//                    mask[i][j] = -1;
//                    this.hole.add(p);
//                }
//            }
//        }
//    }
//
//    private void toGrayscale(BufferedImage bImage) {
//        // converts RGB to a float in the closed interval [0,1]
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                Color c = new Color(bImage.getRGB(j, i));
//
//                // converts RGB values  by weight before converting to grayscale (NOTE that weights converge to 1)
//                int red = (int) (c.getRed() * 0.299);
//                int green = (int) (c.getGreen() * 0.587);
//                int blue = (int) (c.getBlue() * 0.114);
//
//                // converts sum to a fraction in the closed interval [0,1]
//                float frac_value = (float) (red + green + blue) / 255;
//                Point p = new Point(i, j, frac_value);
//                pixels[i][j] = p;
//            }
//        }
//    }
//
//    /**
//     * finds B, the set of all boundary pixels
//     */
//    private void findBoundary() {
//
//        for (Point p : this.hole) {
//            int x = p.getX();
//            int y = p.getY();
//            for (int i = -1; i <= 1; i += 2) {
//                for (int j = -1; j <= 1; j += 2) {
//                    if (i == j && this.connectivity == Type.FOUR) {
//                        // if FOUR, skip diagonal neighbors
//                        continue;
//                    }
//                    if (!isHole(y + i, x + j)) {
//                        this.boundary.add(pixels[y + i][x + j]);
//                    }
//                }
//            }
//        }
//    }
//
//    private void toGrayScale() {
//
//    }
//}