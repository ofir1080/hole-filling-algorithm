package command_line_utility;

//import unused.Image;

import hole_filling_utils.ImageProcess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) throws IOException {


//        File input = new File("grayscale.jpg");
//        BufferedImage b = ImageIO.read(input);
//        Image image = new Image(b, Type.FOUR);
//        System.out.println(b.getRGB(1,1));
//        for (int i = 0; i < image.pixels.length; i++) {
//            for (int j = 0; j < image.pixels[0].length; j++) {
//                System.out.print(image.pixels[i][j].getValue());
//            }
//            System.out.println();
//        }

        BufferedImage image;
        int width;
        int height;
        File input = new File("/Users/ofir1080/dev/HoleFillingUtility/digital_image_processing.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
        BufferedImage filledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

         for(int i=0; i<height; i++) {

            for(int j=0; j<width; j++) {

               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               float val = (float) (c.getBlue() + c.getGreen() + c.getRed()) / (3 * 255);
               Color newColor = new Color(val, val, val);

               filledImage.setRGB(j,i,newColor.getRGB());
            }
         }

         File ouptut = new File("test2.jpg");
         ImageIO.write(filledImage, "jpg", ouptut);


//        System.out.println("[image path] [z] [epsilon] [pixel connectivity: 4/8]");
//        Scanner in = new Scanner(System.in);
//        System.out.println(in.next());

//        ImageProcess.fillAndSave("/Users/ofir1080/dev/HoleFillingUtility/digital_image_processing.jpg");

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
////            Image image = new Image(ImageIO.read(new File(path)));
//            // default weighting function - implemented using functional interface
//            WeightFunction f = (Point u, Point v) -> (float) (1 / (Math.pow(FillHoleMath.euclideanDist(u , v), z)) + e);
//
//        } catch(NumberFormatException e) {
//            System.out.println("Invalid input");
////        } catch (IOException e) {
////            System.out.println("Image does not exist");
//        } catch (InvalidPixelConnectivity e) {
//            System.out.println("Invalid connectivity type");
//
//        }
    }
}

class InvalidPixelConnectivity extends Exception { }
