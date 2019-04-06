package holefill;

import cmdutil.WeightFunction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class represents an Image as a 2d array where each pixel value is a point with ​float a​ in the range ​[0, 1]​,
 *  and hole values which are marked with the value ​-1​
 */
public class Image {

    protected Point[][] pixelMat;
    protected Set<Point> hole;
    protected Set<Point> boundary;
    protected WeightFunction weightFunc; // default weighting function - implemented using functional interface
    protected int cnctType;


    public Image(String imagePath, String maskPath, int z, float e, int cnctType) {

        this.pixelMat = ImageProcess.preprocess(imagePath, maskPath);
        this.hole = ImageProcess.findHole(pixelMat);
        this.cnctType = cnctType;
        this.boundary = ImageProcess.findBoundary(this.pixelMat, this.hole, cnctType);
        // default weight function
        this.weightFunc = (Point u, Point v) -> (float) (1 / (Math.pow(FillHoleCalc.euclideanDist(u, v), z) + e));
        this.cnctType = cnctType;
    }

    /**
     * creates an image file related to the matrix with a filled hole
     * @param pathname
     * @throws IOException (never thrown because file is created before writing)
     */
    public void save(String pathname) throws IOException {

        // extracts PATH+name and format
        Pattern p = Pattern.compile("(.*)\\.(.*)");
        Matcher m = p.matcher(pathname);
        m.find();
        String path = m.group(1);
        String format = m.group(2);

        BufferedImage filledImage = new BufferedImage(this.pixelMat[0].length, this.pixelMat.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < this.pixelMat.length; i++) {
            for (int j = 0; j < this.pixelMat[0].length; j++) {
                // iterates all pixels and creates the related color value
                float holePixelColor = this.pixelMat[i][j].getValue();
                Color c = new Color(holePixelColor, holePixelColor, holePixelColor);
                filledImage.setRGB(j, i, c.getRGB());
            }
        }
        File output = new File( path + "_FILLED." + format);
        ImageIO.write(filledImage, format, output);
        System.out.println("Saved image in source directory.");
    }

    // for non-default weight function
    public void setWeightFunc(WeightFunction weightFunc) {
        this.weightFunc = weightFunc;
    }
}
