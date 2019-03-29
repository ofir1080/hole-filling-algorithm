import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Image {


    //TODO: add boundary type
    //TODO: add weighting function

    private float[][] values;
    private int height;
    private int width;

    public Image(String pathname) throws IOException {

        BufferedImage image = ImageIO.read(new File(pathname));
        this.width = image.getWidth();
        this.height = image.getHeight();

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                //TODO: convert RGB to a floting point in [0,1]

//                Color c = new Color(image.getRGB(j, i));
//                int red = (int) (c.getRed() * 0.299);
//                int green = (int) (c.getGreen() * 0.587);
//                int blue = (int) (c.getBlue() * 0.114);
//                Color newColor = new Color(red + green + blue,
//                        red + green + blue, red + green + blue);
//
//                image.setRGB(j, i, newColor.getRGB());

            }
        }

        this.values = values;
        this.height = height;
        this.width = width;
    }

    //TODO
    private void findHole() {

    }

    private boolean isPixelHole(int x, int y) {
        return this.values[x][y] == -1;
    }

    //TODO
    private void fillHole() {

    }

}
