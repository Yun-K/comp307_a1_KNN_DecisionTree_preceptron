package part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: <br/>
 * The class for k Nearest Neighbour, which is part 1 for assignment 1, 2021.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class KNearestNeighbour {
    static {
        wine_trainingList = new ArrayList<Wine>();
        wine_testingList = new ArrayList<Wine>();
    }

    /** the list for store training wine */
    private static List<Wine> wine_trainingList;

    /** the list for store testing wine */
    private static List<Wine> wine_testingList;

    /** the K value which represent the number of neighbours */
    public static int KNeighbours;

    /**
     * Description: <br/>
     * load the file and make it into the list.
     * 
     * @author Yun Zhou
     * @param filePath
     *            the file path
     */
    public void loadFile(String[] filePath) {
        wine_trainingList = Tool.onLoad(filePath[0]);
        wine_testingList = Tool.onLoad(filePath[1]);
    }

    /**
     * Description: <br/>
     * Main method, need to be called after loadFile().
     * 
     * @author Yun Zhou
     */
    public void execute() {
        double correctGuessNumber = 0.0;

        /*
         * compare the test wine to all train wine, in order to find which train wine is closer
         */
        for (Wine testWine : wine_testingList) {
            // find the shortest trainWine in order to sort and classify
            for (Wine trainWine : wine_trainingList) {
                double distance = Tool.getEclidenDistance_weight(trainWine, testWine);
                // set up the distance
                trainWine.setDistanceToTestWine(distance);
            }

            // sort the train wine list by the distance to testWIne
            Collections.sort(wine_trainingList, ((o1, o2) -> {
                if (o1.getDistanceToTestWine() < o2.getDistanceToTestWine()) {
                    // sort it by distance
                    return -1;
                }
                return 1;
            }));

            // assign the class label with the max occurance from k nearest neighbours
            int guessLabel_class = guess_testWIne_class();

            // check whether it's correct
            if (guessLabel_class == testWine.getClassLabel_real()) {
                correctGuessNumber += 1.0;
            }
        }

        double size = wine_testingList.size();
        double accuracy = correctGuessNumber / size * 100.0;

        System.out.println("The number of correct classified instances: " + correctGuessNumber);
        System.out.println("Total test wine instances: " + wine_testingList.size());
        System.out.printf("The Accuracy is: %.2f%%", accuracy);
        // System.out.println(accuracy);
        System.out.println("\n------------------------------------------------\n");

    }

    /**
     * Description: <br/>
     * Main method, entrance of the part 1, KNN. Args should be the path to two wine.txt, the
     * FIRST should be training, the SECOND should be testing.
     * 
     * @author Yun Zhou
     * @param args
     *            the path to two wine.txt
     */
    public static void main(String[] args) {
        // if (args.length < 2) {
        // System.err.println(
        // "Please enter 2 arguments, the first for wine-training and the second for the
        // wine-test.");
        // return;
        // }
        System.out.println("============      Part 1      ===========");
        // KNearestNeighbour knn = new KNearestNeighbour();
        // knn.loadFile(args);
        // for (int i = 1; i < 10; i++) {
        // if (i %2 == 0){// || i == 4 || i == 6 || i == 8) {
        // continue;
        // }
        // KNeighbours = i;
        // System.out.println("-----------------K= " + KNeighbours
        // + "------------------------------");
        // knn.execute();
        // }

        //////////////////////////////////////////////////////////////////////////////////////////
        String[] filePath = new String[2];
        filePath[0] = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part1/wine-training";
        filePath[1] = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part1/wine-test";

        // load the file first
        KNearestNeighbour knn = new KNearestNeighbour();
        knn.loadFile(filePath);
        // loop through the K
        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) {// == 2 || i == 4 || i == 6 || i == 8) {
                continue;
            }
            KNeighbours = i;
            System.out.println(
                    "-----------------K= " + KNeighbours + "------------------------------");
            knn.execute();
        }
    }

    /**
     * Description: <br/>
     * Return the guess class label of this Test Wine instance.
     * 
     * @author Yun Zhou
     * @return the guess class label of this Test Wine instance.
     */
    public int guess_testWIne_class() {
        Map<Integer, Integer> classLabel_occurance_map = new HashMap<Integer, Integer>();

        // add the occurance time to the map
        for (int i = 0; i < KNeighbours; i++) {
            int classLabel = wine_trainingList.get(i).getClassLabel_real();
            if (!classLabel_occurance_map.containsKey(classLabel)) {
                classLabel_occurance_map.put(classLabel, 1);
                continue;
            }
            // not the first time, add 1 to the value which is the occurance
            int add1 = classLabel_occurance_map.get(classLabel) + 1;
            classLabel_occurance_map.put(classLabel, add1);
        }

        // guess which class does this test instance belongs
        int max_occurance = -1;
        int classLabel_gguess = -1;
        for (Integer classLabel : classLabel_occurance_map.keySet()) {
            int occurance = classLabel_occurance_map.get(classLabel);
            if (max_occurance < occurance) {
                max_occurance = occurance;
                classLabel_gguess = classLabel;
            }
        }
        return classLabel_gguess;

    }

    /**
     * Get the wine_trainingList.
     *
     * @return the wine_trainingList
     */
    public static List<Wine> getWine_trainingList() {
        return wine_trainingList;
    }

    /**
     * Get the wine_testingList.
     *
     * @return the wine_testingList
     */
    public static List<Wine> getWine_testingList() {
        return wine_testingList;
    }

}
