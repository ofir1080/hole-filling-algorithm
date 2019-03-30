
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;


public class Main {

    public static void main(String[] args) { //throws IOException {

//        BufferedImage image = ImageIO.read(new File("/Users/ofir1080/dev/HoleFillingUtility/digital_image_processing.jpg"));
//
//        int width = image.getWidth();
//        int height = image.getHeight();
//
//        for (int i = 0; i < height; i++) {
//
//            for (int j = 0; j < width; j++) {
//
//                Color c = new Color(image.getRGB(j, i));
//                int red = (int) (c.getRed() * 0.299);
//                int green = (int) (c.getGreen() * 0.587);
//                int blue = (int) (c.getBlue() * 0.114);
//                float value = (float) (red + green + blue) / 255;
//                Color newColor = new Color(value, value, value);
//                image.setRGB(j, i, newColor.getRGB());
//            }
//        }
//
//        File ouptut = new File("coinBIN.png");
//        ImageIO.write(image, "png", ouptut);


        System.out.println("[image path] [z] [epsilon] [pixel connectivity: 4/8]");
        Scanner in = new Scanner(System.in);

        // read arguments & validation check
        try {
            String path = in.next();
            int z = Integer.parseInt(in.next());
            float e = Float.parseFloat(in.next());
            int cType = Integer.parseInt(in.next());
            if (cType != 4 && cType != 8) {
                throw new InvalidPixelConnectivity();
            }
            Image image = new Image(ImageIO.read(new File(path)));


        } catch(NumberFormatException e) {
            System.out.println("Invalid input");
        } catch (IOException e) {
            System.out.println("Image does not exist");
        } catch (InvalidPixelConnectivity e) {
            System.out.println("Invalid connectivity type");

        }
    }
}

class InvalidPixelConnectivity extends Exception { }
