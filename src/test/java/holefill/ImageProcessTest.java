package holefill;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class ImageProcessTest {

    private Point[][] testImage;
    private Set<Point> testHole;


    @Before
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

        Set<Point> correctBoundary = new HashSet<>();
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

        Set<Point> correctBoundary = new HashSet<>();
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
    public void fillAndSave() {
    }
}