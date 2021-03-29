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

        int i_th = 0;// the number of the testWIne, used for printing results
        /*
         * compare the test wine to all train wine, in order to find which train wine is closer
         */
        for (Wine testWine : wine_testingList) {
            i_th++;
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

            boolean isCorrect = false;
            // check whether it's correct
            if (guessLabel_class == testWine.getClassLabel_real()) {
                correctGuessNumber += 1.0;
                isCorrect = true;
            }
            // System.out.println("-----------------------------------------------");
            // System.out.println("For the " + i_th + "th test wine:");
            // System.out.println("The real class label is " + testWine.getClassLabel_real());
            // System.out.println("The guess class label by my knn is " + guessLabel_class);
            // System.out.println("which is:\t" + isCorrect);

        }
        System.out.println(
                "-----------------K= " + KNeighbours + "------------------------------");
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
