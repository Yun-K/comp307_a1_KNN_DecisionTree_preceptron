package part3;

import java.util.List;

public class Preceptron {
    /** a list of the image that from the image.data file */
    protected List<ImageP3> imagesDataList;

    /**
     * A constructor. It construct a new instance of Preceptron.
     *
     * @param filePath
     *            the image data to load
     */
    public Preceptron(String filePath) {
        this.imagesDataList = ImageP3.loadFiles(filePath);
    }

    public static void main(String[] args) {
        // if (args.length < 1) {
        // System.err.println(
        // "Please enter one argument, which should be the path of the image.data.");
        // return;
        // }
        // System.out.println("============ Part 3 Yun ZHou, 300442776===============");
        // String filePath = args[0];
        String filePath = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part3/image.data";
        Preceptron preceptron = new Preceptron(filePath);

    }

}
