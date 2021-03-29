package part3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Description: <br/>
 * The image class, for the part 3.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class ImageP3 {

    /** the p1 and the O or X */
    private String firstLine_P1, comment_classLabel;

    /** the width and the height of the image */
    private int width, height;

    /** the pixels, in 2 dimensions,hold the image pixels,either white or black */
    private boolean[][] pixels;

    /**
     * A constructor. It construct a new instance of ImageP3.
     *
     * @param firstLine_P1
     *            the p1, every image got p1 as the start
     * @param comment_classLabel
     *            either O or X, the class label
     * @param width
     *            the width of the image
     * @param height
     *            the height of the image
     * @param pixels
     *            2 dimensions arraay which hold the image pixels,either white or black
     */
    public ImageP3(String firstLine_P1, String comment_classLabel, int width, int height,
            boolean[][] pixels) {
        this.firstLine_P1 = firstLine_P1;
        this.comment_classLabel = comment_classLabel;
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public static List<ImageP3> loadFiles(String filePathName) {
        // precondition check
        // check if the path name does not contains the valid file name
        if (!filePathName.contains("image")) {
            System.err
                    .print("File not found, please check if the PATH to the image data is correct!");
            throw new IllegalArgumentException();
        }

        // the list to return
        List<ImageP3> loadedImage_list = new ArrayList<>();
        // try {
        // // the fileReader objecet for reading
        // File file = new File(filePathName);
        // FileReader fileReader = new FileReader(file);
        // BufferedReader bReader = new BufferedReader(fileReader);
        // String firstLine = bReader.readLine();
        // bReader.close();// close to release resources
        //
        // return loadedInstance_list;
        // } catch (Exception e) {
        // throw new RuntimeException("The file has not beed read successfully", e);
        // }

        boolean[][] newimagePixels = null;
        try {
            Scanner scan = new Scanner(new File(filePathName));

            while (scan.hasNext()) {
                String p1 = scan.next();
                if (p1.equalsIgnoreCase("p1")) {
                    System.err.println(
                            "This is not the P1 PBM file, since the content of the first line is not P1.");
                    throw new Exception();
                }
                String comment_class = scan.next().substring(1);// O or X
                int imageWidth = scan.nextInt();
                int imageHeight = scan.nextInt();
                java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
                newimagePixels = new boolean[imageWidth][imageHeight];

                for (int r = 0; r < imageWidth; r++) {
                    for (int c = 0; c < imageHeight; c++) {
                        newimagePixels[r][c] = (scan.findWithinHorizon(bit, 0).equals("1"));
                    }
                }
                loadedImage_list.add(
                        new ImageP3(p1, comment_class, imageWidth, imageWidth, newimagePixels));

            }

            scan.close();
        } catch (Exception e) {
            System.out.println("Load from file failed");
        }

        return loadedImage_list;
    }

    /**
     * Get the firstLine_P1.
     *
     * @return the firstLine_P1
     */
    public String getFirstLine_P1() {
        return firstLine_P1;
    }

    /**
     * Get the comment_classLabel.
     *
     * @return the comment_classLabel
     */
    public String getComment_classLabel() {
        return comment_classLabel;
    }

    /**
     * Get the width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the pixels.
     *
     * @return the pixels
     */
    public boolean[][] getPixels() {
        return pixels;
    }

    /**
     * Set the firstLine_P1.
     *
     * @param firstLine_P1
     *            the firstLine_P1 to set
     */
    public void setFirstLine_P1(String firstLine_P1) {
        this.firstLine_P1 = firstLine_P1;
    }

    /**
     * Set the comment_classLabel.
     *
     * @param comment_classLabel
     *            the comment_classLabel to set
     */
    public void setComment_classLabel(String comment_classLabel) {
        this.comment_classLabel = comment_classLabel;
    }

    /**
     * Set the width.
     *
     * @param width
     *            the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set the height.
     *
     * @param height
     *            the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Set the pixels.
     *
     * @param pixels
     *            the pixels to set
     */
    public void setPixels(boolean[][] pixels) {
        this.pixels = pixels;
    }

}
