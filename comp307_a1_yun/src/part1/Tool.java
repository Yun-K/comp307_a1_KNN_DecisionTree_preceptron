package part1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: <br/>
 * Tool class, all static methods, for loading file, calcuating the shortest distance which is
 * the Ecliden distance between two wine.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Tool {
    /** feature weight list which store the range */
    private static List<Double> featureWeightList = new ArrayList<Double>();

    /**
     * Description: <br/>
     * Method for calculating the euclidean distance from this wine instance to another.(i.e.
     * the shortest distance between 2 points)
     * <p>
     * The euclidean distance of two object is based upon the all attributes, the number of
     * dimensions is the number of the attribute, in order to get the classify result more
     * accurate, this method will try to add the weight on each of the feature.
     * 
     * @author Yun Zhou
     * @param currentWine
     *            current wine
     * @param anotherWine
     *            the wine to compare
     * @return the shortest distance between two wine instance
     */
    public static double getEclidenDistance_weight(Wine currentWine, Wine anotherWine) {

        double eclidernDistance_weight = 0.0;// the distance between 2 wine objects
        for (int i = 0; i < Wine.FEATURESIZE; i++) {
            double feature_weight = getFeatureWeight(i);
            double diff = currentWine.getFeatures_list().get(i)
                    - anotherWine.getFeatures_list().get(i);

            // from slide 6, lecture 5
            // i think use weight to describe is much more make sense than the range :)
            eclidernDistance_weight += Math.pow(diff, 2) / Math.pow(feature_weight, 2);
        }

        // square root to get the shortest distance
        eclidernDistance_weight = Math.sqrt(eclidernDistance_weight);
        return eclidernDistance_weight;
    }

    /**
     * Description: <br/>
     * This method calculate the weight of each feature, the arg specify which feature.
     * 
     * @author Yun Zhou
     * @param index
     *            the index of the feature
     * @return the weight of the specify feature
     */
    private static double getFeatureWeight(int index) {
        // save the unnecessuary search
        if (!featureWeightList.isEmpty()) {
            return featureWeightList.get(index);
        }

        for (int i = 0; i < Wine.FEATURESIZE; i++) {
            // find the max and min of the feature in the training list
            double feature_max = Double.NEGATIVE_INFINITY;
            double feature_min = Double.POSITIVE_INFINITY;
            for (Wine currentWine : KNearestNeighbour.getWine_trainingList()) {
                if (currentWine.getFeatures_list().get(i) < feature_min) {
                    feature_min = currentWine.getFeatures_list().get(i);
                } else if (currentWine.getFeatures_list().get(i) > feature_max) {
                    feature_max = currentWine.getFeatures_list().get(i);
                }
            }
            // assign the average to be the weight
            double featureWeight = feature_max - feature_min;

            featureWeightList.add(featureWeight);

        }

        return featureWeightList.get(index);

    }

    /**
     * Description: <br/>
     * Load the text file that contains Wine to the Wine instances, and return the list of all
     * Wine instances from file. In this case, either training or test file.
     * 
     * @author Yun Zhou
     * @param pathName
     *            the path to the Wine file
     * @return the list of all Wine instances from file
     */
    public static List<Wine> onLoad(String pathName) {
        // precondition check
        // check if the path name does not contains the valid file name
        if (!(pathName.contains("wine-test") || pathName.contains("wine-training"))) {
            System.err
                    .print("File not found, please check if the PATH to the wine data is correct!");
            // return null;
            throw new IllegalArgumentException();
        }

        // the list to return
        List<Wine> loadedWine_list = new ArrayList<Wine>();

        try {
            // the fileReader objecet for reading
            File file = new File(pathName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fileReader);

            // the first line is the describtion not the data, so read it first
            String wine_line = bReader.readLine();

            // keep reading the txt file until the end of the stream has been reached
            while (wine_line != null) {
                wine_line = bReader.readLine();

                if (wine_line == null) {
                    break;
                }
                // split the line by space
                String[] wine_info = wine_line.split(" ");

                // assign the class label which is the last index
                int classLabel_int = Integer.parseInt(wine_info[wine_info.length - 1]);

                // the attribute list
                List<Double> attributes_list = new ArrayList<Double>();
                // the last element is the class label for this wine instance,
                // so i < length-1
                for (int i = 0; i < wine_info.length - 1; i++) {
                    // add it into the list
                    attributes_list.add(Double.parseDouble(wine_info[i]));
                }

                assert attributes_list.size() == 13;// for debug
                assert classLabel_int == 1 || classLabel_int == 2 || classLabel_int == 3;

                loadedWine_list.add(new Wine(attributes_list, classLabel_int));
                // System.out.println("fukkkkkkkkkkkkkk");
            }

            bReader.close();// close to release resources

            return loadedWine_list;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("The file has not beed read successfully", e);
        }

    }

}
