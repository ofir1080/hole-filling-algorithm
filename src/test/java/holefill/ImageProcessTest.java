package holefill;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class ImageProcessTest {

    private Point[][] testImage;
    private Set<Point> testHole;
    Set<Point> correctBoundary;


    @Before
    /**
     * creates a random 100x100 test image
     */
    public void createRandomImage() {
        testImage = new Point[100][100];
        for (int i = 0; i < testImage.length; i++) {
            for (int j = 0; j < testImage[0].length; j++) {

                testImage[i][j] = new Point(i, j, (float) Math.random());
            }
        }
    }

    @Test
    public void preprocess() {


    }


    @Test
    public void findBoundaryDiagonalTest() {

        // creates a diagonal hole from (1,1) to (90,90)
        testHole = new HashSet();
        for (int i = 10; i <= 90; i++) {
            // creates the hole and adds is to the set
            testImage[i][i].setValue(-1);
            testHole.add(testImage[i][i]);
        }

        correctBoundary = new HashSet<>();
        // creates boundary mutually
        correctBoundary.add(testImage[10][9]);
        correctBoundary.add(testImage[90][91]);
        for (int i = 10; i <= 90; i++) {
            correctBoundary.add(testImage[i + 1][i]);
            correctBoundary.add(testImage[i - 1][i]);
        }
        // test 4-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.FOUR),correctBoundary);

        correctBoundary.add(testImage[9][9]);
        correctBoundary.add(testImage[91][91]);
        for (int i = 10; i <= 90; i++) {
            correctBoundary.add(testImage[i + 1][i - 1]);
            correctBoundary.add(testImage[i - 1][i + 1]);
        }
        // test 8-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.EIGHT),correctBoundary);

    }

    @Test
    public void findBoundarySinglePixelTest() {

        testHole = new HashSet();
        testImage[34][55] = new Point(34,55, -1);
        testHole.add(testImage[34][55]);

        correctBoundary = new HashSet<>();
        correctBoundary.add(testImage[33][55]);
        correctBoundary.add(testImage[35][55]);
        correctBoundary.add(testImage[34][54]);
        correctBoundary.add(testImage[34][56]);
        // test 4-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.FOUR), correctBoundary);

        correctBoundary.add(testImage[33][54]);
        correctBoundary.add(testImage[35][56]);
        correctBoundary.add(testImage[35][54]);
        correctBoundary.add(testImage[33][56]);
        // test 8-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.EIGHT), correctBoundary);

    }

    @Test
    public void findBoundaryLineTest() {

        // test for horizontal 1-pixel height hole
        testHole = new HashSet();
        for (int i = 6; i <= 68; i++) {
            testImage[39][i].setValue(-1);
            testHole.add(testImage[39][i]);
        }

        correctBoundary = new HashSet<>();
        for (int i = 6; i <= 68; i++) {
            correctBoundary.add(testImage[40][i]);
            correctBoundary.add(testImage[38][i]);
        }
        correctBoundary.add(testImage[39][69]);
        correctBoundary.add(testImage[39][5]);

        // test 4-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.FOUR), correctBoundary);

        correctBoundary.add(testImage[38][5]);
        correctBoundary.add(testImage[40][5]);
        correctBoundary.add(testImage[38][69]);
        correctBoundary.add(testImage[40][69]);
        // test 8-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.EIGHT), correctBoundary);

    }

    @Test
    public void findBoundaryPyramidTest() {

        testHole = new HashSet();
        correctBoundary = new HashSet<>();

        testImage[50][50].setValue(-1);
        testHole.add(testImage[50][50]);

        testImage[51][50].setValue(-1);
        testHole.add(testImage[51][50]);

        testImage[51][51].setValue(-1);
        testHole.add(testImage[51][51]);

        testImage[51][49].setValue(-1);
        testHole.add(testImage[51][49]);

        correctBoundary = new HashSet<>();

        correctBoundary.add(testImage[52][51]);
        correctBoundary.add(testImage[52][50]);
        correctBoundary.add(testImage[52][49]);

        correctBoundary.add(testImage[51][52]);
        correctBoundary.add(testImage[51][48]);

        correctBoundary.add(testImage[50][49]);
        correctBoundary.add(testImage[50][51]);

        correctBoundary.add(testImage[49][50]);
        // test 4-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.FOUR), correctBoundary);

    }

    @Test
    public void findBoundaryRandomHoleTestExtended() {

        testHole = new HashSet();
        correctBoundary = new HashSet<>();

        correctBoundary.add(testImage[39][40]);
        int k = 1;
        for (int i = 40; i <= 50; i++) {
            for (int j = 0; j < k; j++) {
                testImage[i][40 + j].setValue(-1);
                testHole.add(testImage[i][40 + j]);
                testImage[i][40 - j].setValue(-1);
                testHole.add(testImage[i][40 - j]);
            }
            correctBoundary.add(testImage[i][40 + k]);
            correctBoundary.add(testImage[i][40 - k]);
            k++;
        }

        for (int i = 0; i <= 10; i++) {
            correctBoundary.add(testImage[51][40 - i]);
            correctBoundary.add(testImage[51][40 + i]);
        }

        // test 4-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.FOUR), correctBoundary);

//        correctBoundary.add(testImage[39][39]);
//        correctBoundary.add(testImage[39][41]);
        k = 1;
        for (int i = 39; i < 50; i++) {
            correctBoundary.add(testImage[i][40 + k]);
            correctBoundary.add(testImage[i][40 - k]);
            k++;
        }
        correctBoundary.add(testImage[51][40 - 11]);
        correctBoundary.add(testImage[51][40 + 11]);

        // test 8-connectivity
        assertEquals(ImageProcess.findBoundary(testImage, testHole, CONNECTIVITY_TYPE.EIGHT), correctBoundary);

    }

}