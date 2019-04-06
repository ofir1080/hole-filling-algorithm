*Answers to questions are in answers.pdf*

# Hole Filling Algorithm

This library fills holes in images. Steps:
* Convert the source image to a grayscale format by taking the mean value of (r,g,b) values.
* Normalises color values from range [0-255] to [0.0-1.0]
* Assign -1 value to every pixel where defined as hole in the mask
* Find the the boundary of the hole (for either 8 or 4 connectivity)
* Applies the algorithm for every pixel in the hole
* Save the filled image in source image directory

NOTE: Mask image is the same height and width of the source image. A pixel is defined as 'hole pixel' iff its color value in the mask satisfies *value > 0.5*

## Usage

```
./gradlew run --args='[image path] [mask path] [z] [epsilon] [pixel connectivity: 4/8]'
```
### Recommend:
* Set z in range 2 <= z <= 25
* Set e < 1e-4

### Example
```
./gradlew run --args='./images/tests/test1.jpg ./images/tests/test1_mask.jpg 2 0.00001 8'
```
source and mask  
![test](https://i.ibb.co/pPwg9Kp/test1.jpg)
![test](https://i.ibb.co/9vbXFft/test1-mask.jpg)  
output  
![test](https://i.ibb.co/f4H3tcL/test1-FILLED.jpg)

## Unit tests

Finding the boundary of the hole was a quite a challenge for me compare to other actions. I tried to implement is as simple, efficient and readable as possible.
I have built quite a few unit tests for this function using jUnit. To run all tests:

```
./gradlew test --tests holefill.ImageProcessTest
```

## Overall tests

I have included some test images in /images/tests. To run:

```
./gradlew run --args='./images/tests/test1.jpg ./images/tests/test1_mask.jpg 2 0.00001 8'
```

```
 ./gradlew run --args='./images/tests/test2.jpg ./images/tests/test2_mask.jpg 2 0.00001 4'
```

```
./gradlew run --args='./images/tests/test3.jpg ./images/tests/test3_mask.jpg 27 0.00001 8'
```

```
./gradlew run --args='./images/tests/test4.jpg ./images/tests/test4_mask.jpg 2 0.00001 4'
```

```
./gradlew run --args='./images/tests/test5.jpg ./images/tests/test5_mask.jpg 24 0.00001 8'
```

```
./gradlew run --args='./images/tests/test6.jpg ./images/tests/test6_mask.jpg 3 0.00001 8'
```

## Built With

* [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) - Dependency Management and Build Tool

## Dependencies

* [JUnit 4](https://junit.org/junit4/) - testing framework for the Java

## Author
Ofir Abramovich
