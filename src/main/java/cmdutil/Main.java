package cmdutil;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


import holefill.FillHoleCalc;
import holefill.ImageProcess;
import holefill.Point;

import java.io.IOException;
import java.util.Scanner;

import java.io.PrintWriter;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    public static void main2(String[] args) {

    }

    public static void main(String[] args) throws IOException {


        //TODO change for to foreach
//        Scanner in = new Scanner(System.in);
//        System.out.println(in.next());
        // read arguments & validation check
        try {
//            String path = args[1];
//            int z = Integer.parseInt(args[2]);
//            float e = Float.parseFloat(args[3]);
//            int cType = Integer.parseInt(args[4]);
//            if (cType != 4 && cType != 8) {
//                throw new InvalidPixelConnectivity();
//            }
        // default weighting function - implemented using functional interface
        WeightFunction f = (Point u, Point v) -> (float) (1 / (Math.pow(FillHoleCalc.euclideanDist(u, v), 3) + .00001));

        Point[][] pixelMat = ImageProcess.preprocess("/Users/ofir1080/dev/HoleFillingUtility/images/tests/test6.jpg",
                "/Users/ofir1080/dev/HoleFillingUtility/images/tests/test6_mask.jpg");

        FillHoleCalc.fillHole(pixelMat, f, 8);
        ImageProcess.fillAndSave(pixelMat, "/Users/ofir1080/dev/HoleFillingUtility/images/tests/test6.jpg");

        } catch(NumberFormatException e) {
            System.out.println("Invalid input");
//        } catch (InvalidPixelConnectivity e) {
//            System.out.println("Invalid connectivity type");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: [image path] [mask path] [z] [epsilon] [pixel connectivity: 4/8]");
        }
    }
}

class InvalidPixelConnectivity extends Exception { }
