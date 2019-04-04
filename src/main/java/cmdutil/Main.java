package cmdutil;



import java.io.IOException;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) throws IOException {




//        System.out.println("[image path] [z] [epsilon] [pixel connectivity: 4/8]");
//        Scanner in = new Scanner(System.in);

        System.out.println("[image path] [mask path] [z] [epsilon] [pixel connectivity: 4/8]");
        Scanner in = new Scanner(System.in);
        System.out.println(in.next());
//        System.out.println(args[0]);
        // read arguments & validation check
//        try {
//            String path = in.next();
//            int z = Integer.parseInt(in.next());
//            float e = Float.parseFloat(in.next());
//            int cType = Integer.parseInt(in.next());
//            if (cType != 4 && cType != 8) {
//                throw new InvalidPixelConnectivity();
//            }
//        // default weighting function - implemented using functional interface
//        WeightFunction f = (Point u, Point v) -> (float) (1 / (Math.pow(FillHoleCalc.euclideanDist(u, v), 8) + .00001));
//
//        Point[][] pixelMat = ImageProcess.preprocess("/Users/ofir1080/dev/HoleFillingUtility/images/digital_image_processing_HOLE.png",
//                "/Users/ofir1080/dev/HoleFillingUtility/images/digital_image_processing_MASK.png");
//
//        FillHoleCalc.fillHole(pixelMat, f);
//        ImageProcess.fillAndSave(pixelMat, "/Users/ofir1080/dev/HoleFillingUtility/images/HOLE.jpg");
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
