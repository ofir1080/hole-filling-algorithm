package holefill;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class FillHoleCalcTest {

    private Point[][] testImage;

    @Before
    public void createImage() {

        float[][] testImage = {
                {0.72f, 0.86f, 0.02f, 0.84f, 0.70f, 0.06f, 0.72f, 0.37f, 0.34f, 0.74f},
                {0.53f, 0.11f, 0.04f, 0.10f, 0.41f, 0.93f, 0.47f, 0.44f, 0.80f, 0.51f},
                {0.72f, 0.96f, 0.70f, 0.23f, 0.91f, 0.35f, 0.37f, 0.60f, 0.59f, 0.65f},
                {0.84f, 0.37f, 0.84f, -1f, -1f, -1f, -1f, 0.74f, 0.76f, 0.14f},
                {0.94f, 0.68f, -1f, -1f, -1f, 0.88f, 0.37f, 0.32f, 0.30f, 0.64f},
                {0.09f, 0.43f, 0.30f, -1f, -1f, 0.56f, 0.99f, 0.22f, 0.55f, 0.72f},
                {0.47f, 0.47f, 0.76f, 0.50f, 0.39f, 0.07f, 0.44f, 0.06f, 0.65f, 0.45f},
                {0.51f, 0.85f, 0.72f, 0.63f, 0.04f, 0.92f, 0.31f, 0.76f, 0.52f, 0.54f},
                {0.22f, 0.95f, 0.20f, 0.70f, 0.08f, 0.74f, 0.54f, 0.37f, 0.09f, 0.54f},
                {0.74f, 0.12f, 0.42f, 0.71f, 0.50f, 0.62f, 0.00f, 0.07f, 0.88f, 0.71f}
        };
//        {
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0 },
//            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
//            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
//            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
//            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
//            {0, 0, 1, 0, 0, 1, 1, 0, 0, 0 },
//            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//        }
//
//        for (int i = 0; i < testImage.length; i++) {
//            System.out.print("{");
//            for (int j = 0; j < testImage[0].length; j++) {
//                System.out.print(0 + ", ");
//            }
//            System.out.println("},");
//        }


//        testImage = new Point[20][20];
//        for (int i = 0; i < testImage.length; i++) {
//            System.out.print("{");
//            for (int j = 0; j < testImage[0].length; j++) {
//                System.out.print((float) ((int) (Math.random() * 100)) / 100 + "f,");
//            }
//            System.out.println("},");
//        }

    }

    @Test
    public void fillHoleMonoBoundary() {

        testImage = new Point[100][100];
        float boundaryColor = (float) Math.random();
        for (int i = 0; i < testImage.length; i++) {
            for (int j = 0; j < testImage[0].length; j++) {
            }
        }
    }
}