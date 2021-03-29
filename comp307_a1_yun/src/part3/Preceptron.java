package part3;

import java.util.List;

public class Preceptron {

    public Preceptron(String filePath) {
        loadFiles(filePath);
    }

    /** a list of image */
    // List<Image> imageDataList;

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

    public void loadFiles(String filePathName) {

    }

}
