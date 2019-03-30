
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Main {

    public static void main(String[] args) throws IOException {

//        BufferedImage image = ImageIO.read(new File("/Users/ofir1080/dev/HoleFillingUtility/digital_image_processing.jpg"));
////
////        int width = image.getWidth();
////        int height = image.getHeight();
////
////        for (int i = 0; i < height; i++) {
////
////            for (int j = 0; j < width; j++) {
////
////                Color c = new Color(image.getRGB(j, i));
////                int red = (int) (c.getRed() * 0.299);
////                int green = (int) (c.getGreen() * 0.587);
////                int blue = (int) (c.getBlue() * 0.114);
////                Color newColor = new Color(red + green + blue,
////                        red + green + blue,red + green + blue);
////
////                image.setRGB(j, i, newColor.getRGB());
////            }
////        }
////
////        File ouptut = new File("grayscaled.jpg");
////        ImageIO.write(image, "jpg", ouptut);
////
////
////        //TODO: take reuired args

        Image img = new Image("/Users/ofir1080/dev/HoleFillingUtility/scshot.png");


    }
}
