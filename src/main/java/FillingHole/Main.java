package FillingHole;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage  image;
        int width;
        int height;

        File input = new File("grayscale.jpg");
        image = ImageIO.read(input);
        width = image.getWidth();
        height = image.getHeight();

        for(int i=0; i<height; i++) {

            for(int j=0; j<width; j++) {

                Color c = new Color(image.getRGB(j, i));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);
                int value = (red+green+blue / 3) > 128 ? 255 : 0;
//                int value = red + green + blue;
                Color newColor = new Color(value, value, value);

                image.setRGB(j,i,newColor.getRGB());
            }
        }

        File ouptut = new File("binary.jpg");
        ImageIO.write(image, "jpg", ouptut);



//        System.out.println("[image path] [z] [epsilon] [pixel connectivity: 4/8]");
//        Scanner in = new Scanner(System.in);
//
//        // read arguments & validation check
//        try {
//            String path = in.next();
//            int z = Integer.parseInt(in.next());
//            float e = Float.parseFloat(in.next());
//            int cType = Integer.parseInt(in.next());
//            if (cType != 4 && cType != 8) {
//                throw new InvalidPixelConnectivity();
//            }
//            Image image = new Image(ImageIO.read(new File(path)));
//            // default weighting function - implemented using functional interface
//            WeightFunction f = (Point u, Point v) -> (float) (1 / (Math.pow(FillHoleMath.euclideanDist(u , v), z)) + e);
//
//        } catch(NumberFormatException e) {
//            System.out.println("Invalid input");
//        } catch (IOException e) {
//            System.out.println("Image does not exist");
//        } catch (InvalidPixelConnectivity e) {
//            System.out.println("Invalid connectivity type");
//
//        }
    }
}

class InvalidPixelConnectivity extends Exception { }
