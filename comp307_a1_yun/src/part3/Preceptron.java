package part3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: <br/>
 * The preceptron class that do the preceptron algorthim.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Preceptron {
    /** a list of the image that from the image.data file */
    protected List<ImageP3> imagesDataList;

    /**
     * the list of features which random generated
     */
    protected List<Feature> randomFeatures_list;
    // protected List<Map<Feature, Double>> features_weight_list;

    /**
     * each feature has the corresponding weight, which is one-to-one mapping. (Hint:the size
     * of randomFeatures_list is the same as the weight.length, one to one mapping).
     */
    private double[] weight;

    /**
     * A constructor. It construct a new instance of Preceptron.
     *
     * @param filePath
     *            the image data to load
     */
    public Preceptron(String filePath) {
        this.imagesDataList = ImageP3.loadFiles(filePath);
    }

    /**
     * Description: <br/>
     * Execute the application.
     * 
     * @author Yun Zhou
     */
    protected void execute() {
        this.randomFeatures_list = generateFeature();// initialize the feature list and
                                                     // initialize the weight
        executeAlgorithm();// execute the algorthim
    }

    /**
     * Description: <br/>
     * Algorithm for learning the weights of the precptron, this is from the handout as well as
     * the lecture slide 7.
     * 
     * @author Yun Zhou
     */
    protected void executeAlgorithm() {
        int correctClassifiedImages_num = 0;
        int runTimes = 0;
        int limitTime = 111;
        // loop until all images are guessed correctly or
        // it stops converging which means
        // it does not have any progress for presenting all examples after runing 100 times
        while (!(correctClassifiedImages_num >= imagesDataList.size() || runTimes >= limitTime)) {
            runTimes++;
            // get the correct classified image number for this iteration
            correctClassifiedImages_num = getCorrectClassifiedNumber();
        }

        printOutPerformanceResults(correctClassifiedImages_num, runTimes, limitTime);

    }

    /**
     * Description: <br/>
     * Print out the performance result that the assignment handout need us to print.
     * 
     * @author Yun Zhou
     * @param correctClassifiedImages_num
     * @param runTimes
     * @param limitTime
     */
    public void printOutPerformanceResults(int correctClassifiedImages_num, int runTimes,
            int limitTime) {
        System.out.println("================================================================");
        System.out.println(
                "--------------Random Features that the programe created:--------------------");
        for (Feature feature : randomFeatures_list) {
            feature.printFeatures();
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println(
                "The final set of weights the perceptron algorthim learnd:\n Final Weight: ");
        System.out.println(Arrays.toString(weight));
        System.out.println("---------------------------------------------------------------------");

        System.out.println("---------------------------------------------------------------------");
        System.out.println("The limit times: " + limitTime);
        System.out.println(
                "The algorthim iterate " + runTimes + " out of " + limitTime + " times in total ");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("The total size of the image is: " + imagesDataList.size());
        System.out
                .println("It finally got " + correctClassifiedImages_num + " out of "
                         + imagesDataList.size() + " correct prediction.");
        System.out.println("The accuracy is " + correctClassifiedImages_num + "/"
                           + imagesDataList.size() + ", which is:\n\t\t");
        double accuracy = 100.0 * ((double) correctClassifiedImages_num)
                / ((double) imagesDataList.size());
        System.out.printf("\t%.2f%%", accuracy);
        System.out
                .println("\n---------------------------------------------------------------------");

    }

    /**
     * Description: <br/>
     * Loop through the imagedataList to get the correct classified result. It will upgrade the
     * performance by updating the weight in each iteraction.
     * 
     * @author Yun Zhou
     * @return the number of correct classified number
     */
    public int getCorrectClassifiedNumber() {
        int correctClassifiedNumber = 0;
        // loop through the imagelist
        for (int imageIndex = 0; imageIndex < imagesDataList.size(); imageIndex++) {
            ImageP3 currentImage = imagesDataList.get(imageIndex);// assign the image object
            /*
             * first, do the maths which need to use the corresponing weight in order to
             * calculate the feature value
             */
            double predictFeatureValues = 0.0;
            for (int featureIndex = 0; featureIndex < randomFeatures_list
                    .size(); featureIndex++) {
                double featureValue = randomFeatures_list.get(featureIndex).getIntegerValues()
                        .get(featureIndex);
                double feature_weight = weight[featureIndex];
                predictFeatureValues += (featureValue * feature_weight);
            }

            /*
             * then, based on the weighted feature values, guess which class does this image
             * object belongs
             */
            String predict_output_OorX = "";
            if (predictFeatureValues > 0) {
                predict_output_OorX = "X";
            } else {
                predict_output_OorX = "O";
            }

            /*
             * compare it with the true result, to see the situation.
             * 
             * Based on each different situation, in order to get the correct true result in
             * the next iteration and image object, the weight will then be corresponding
             * updated
             */
            // get the correct real classlabel for comparing
            String realclass_OorX = currentImage.getComment_classLabel();
            // the preceptron prediction is correct, do nothing
            if (predict_output_OorX.equalsIgnoreCase(realclass_OorX)) {
                correctClassifiedNumber++;// correct, add 1
                continue;
            }
            // BELOW MEANS THE PREDICT IS WRONG, so the WEIGHT will be UPDATED
            // Else if -ve example and wrong:
            // (i.e. weights on active features are too high)
            // Subtract feature vector from weight vector
            if (realclass_OorX.equalsIgnoreCase("O")) {
                // assert randomFeatures_list.size() == weight.length;

                // Subtract feature vector from weight vector
                for (int weightIndex = 0; weightIndex < weight.length; weightIndex++) {
                    // assert imageIndex == weightIndex;
                    // double featureVal =
                    // randomFeatures_list.get(weightIndex).getValue(image);
                    double featureVal = randomFeatures_list.get(weightIndex).getIntegerValues()
                            .get(imageIndex);

                    double updateWeight = weight[weightIndex] - featureVal;
                    weight[weightIndex] = updateWeight;
                }

            }
            // Else if +ve example and wrong:
            // (i.e. weights on active features are too low)
            // Add feature vector to weight vector
            else if (realclass_OorX.equalsIgnoreCase("X")) {
                // Add feature vector to weight vector
                for (int weightIndex = 0; weightIndex < weight.length; weightIndex++) {
                    // assert imageIndex == weightIndex;
                    // double featureVal =
                    // randomFeatures_list.get(weightIndex).getValue(image);
                    double featureVal = randomFeatures_list.get(weightIndex).getIntegerValues()
                            .get(imageIndex);

                    double updateWeight = weight[weightIndex] + featureVal;
                    weight[weightIndex] = updateWeight;
                }
            }
        }

        return correctClassifiedNumber;

    }

    /**
     * Description: <br/>
     * Random generate the features.
     * 
     * @author Yun Zhou
     * @return the list of random features
     */
    public List<Feature> generateFeature() {
        List<Feature> tempFeaturesList = new ArrayList<Feature>();
        // feature 0 is always the dummy feature
        tempFeaturesList.add(new Feature(true));
        // random generate 50 featrues
        for (int i = 0; i < 50; i++) {
            tempFeaturesList.add(new Feature());
        }

        // set up the feature values associate with each image object
        for (int i = 0; i < tempFeaturesList.size(); i++) {
            Feature feature = tempFeaturesList.get(i);
            for (ImageP3 image : imagesDataList) {
                List<Integer> featureValues = feature.getIntegerValues();
                if (i == 0) {
                    // first feature is the dummy
                    // add the value 1
                    featureValues.add(1);
                } else {
                    featureValues.add(feature.getValue(image));
                }
                feature.setIntegerValues(featureValues);
            }
        }
        // initialize the weight
        weight = new double[tempFeaturesList.size()];
        for (int i = 0; i < weight.length; i++) {
            weight[i] = 0.0;// initialize each of them to 0
        }

        return tempFeaturesList;
    }

}
