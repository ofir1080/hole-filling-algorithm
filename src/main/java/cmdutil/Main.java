package cmdutil;

import holefill.FillHoleCalc;
import holefill.Image;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        try {
            // parsing arguments
            int cType = Integer.parseInt(args[4]);
            float e = Float.parseFloat(args[3]);
            int z = Integer.parseInt(args[2]);
            String mask = args[1];
            String path = args[0];

            if (cType != 4 && cType != 8) {
                throw new InvalidPixelConnectivity();
            }

            Image image = new Image(path, mask, z, e, cType); // creates an image object
            FillHoleCalc.fillHole(image); // fills the hole
            image.save(path); // creates and saves filled image images

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing arguments\nUsage: [image path] [mask path] [z] [epsilon] [pixel connectivity: 4/8]");
            System.exit(1);
        } catch(NumberFormatException e) {
            System.out.println(e.fillInStackTrace());
            System.exit(1);
        } catch (InvalidPixelConnectivity e) {
            System.out.println("Invalid connectivity type");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
            System.exit(1);
        }
    }
}

class InvalidPixelConnectivity extends Exception { }
