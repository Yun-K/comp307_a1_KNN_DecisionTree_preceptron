package comp307_a1_yun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static int KNeighbours;

    public KNearestNeighbour() {

    }

    /**
     * Description: <br/>
     * load the file and make it into the list.
     * 
     * @author Yun Zhou
     * @param filePath
     *            the file path
     */
    private void loadFile(String[] filePath) {
        wine_trainingList = Tool.onLoad(filePath[0]);
        wine_testingList = Tool.onLoad(filePath[1]);
    }

    void execute() {
        // double correctGuessNumber = 0.0;

        for (Wine testWine : wine_testingList) {
            // find the shortest trainWine in order to sort and classify
            for (Wine trainWine : wine_trainingList) {
                double distance = Tool.getEclidenDistance_weight(trainWine, testWine);
                // set up the distance
                trainWine.setDistanceToTestWine(distance);
            }

            // clone the training list for the purpose of compare, in order to find K
            // numbers of nearest neighbours
            // List<Wine> cloned_trainingWines_forCompare = new ArrayList<Wine>(
            // getWine_trainingList());
            Collections.sort(wine_trainingList, ((o1, o2) -> {
                if (o1.getDistanceToTestWine() < o2.getDistanceToTestWine()) {
                    // sort it by distance
                    return -1;
                }
                return 1;
            }));

            //
            // /*
            // * guess the class label based on the K number of the neighbour
            // */
            // // add it into the map
            // Map<Integer, Integer> classLabel_occurance_map = new HashMap<Integer,
            // Integer>();
            // for (int i = 0; i < KNeighbours; i++) {
            // // int classLabel =
            // // cloned_trainingWines_forCompare.get(i).getClassLabel_real();
            // int classLabel = wine_trainingList.get(i).getClassLabel_real();
            // // add the occurance time to the map
            // if (classLabel_occurance_map.containsKey(classLabel)) {
            // classLabel_occurance_map.put(classLabel,
            // classLabel_occurance_map.get(classLabel) + 1);
            // } else {
            //
            // classLabel_occurance_map.put(classLabel, 1);
            // }
            // }
            //
            // // guess which class does this test instance belongs
            // int max_occurance = -1;
            // for (int key : classLabel_occurance_map.keySet()) {
            // int occurance = classLabel_occurance_map.get(key);
            // if (occurance > max_occurance) {
            // max_occurance = occurance;
            // }
            // }
            // // int max_occurance = votingMap();
            // // check whether it's correct
            // if (max_occurance == testWine.getClassLabel_real()) {
            // correctGuessNumber += 1;
            // }
            // }

            int lineLabel = votingMap();
            testWine.setClassLabel_guess(lineLabel);
        }

        double accuracy = accuracyCalc();
        // print
        // System.out.println("Correct classified instances: " + correctGuessNumber);
        System.out.println("Total test wine instances: " + wine_testingList.size());
        // double accuracy = correctGuessNumber / ((double) wine_testingList.size());
        accuracy *= 100;
        System.out.printf("Accuracy: %.2f%%", accuracy);
        System.out.println("\n------------------------------------------------\n\n");

    }

    public static double accuracyCalc() {
        int accuracyCounter = 0;

        for (Wine test : wine_testingList) {
            if (test.getClassLabel_guess() == test.getClassLabel_real()) {
                accuracyCounter++;
            }
        }
        System.out.println("Correct classified instances: " + accuracyCounter);
        // System.out.println(accuracyCounter);// println
        return accuracyCounter / (double) wine_testingList.size();
    }

    public static void main(String[] args) {
        // if (args.length < 2) {
        // System.err.println(
        // "Please enter 2 arguments, the first for wine-training and the second for the
        // wine-test.");
        // return;
        // }
        // KNearestNeighbour knn = new KNearestNeighbour(args);
        //////////////////////////////////////////////////////////////////////////////////////////
        String[] filePath = new String[2];
        filePath[0] = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part1/wine-training";
        filePath[1] = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part1/wine-test";

        // load the file first

        KNearestNeighbour knn = new KNearestNeighbour();
        knn.loadFile(filePath);

        for (int i = 1; i < 10; i++) {
            KNeighbours = i;

            System.out.println("-----------------K= " + KNeighbours
                               + "------------------------------");
            knn.execute();

        }

    }

    public static int votingMap() {
        Map<Integer, Integer> voted = new HashMap<>();
        for (int i = 0; i < KNeighbours; i++) {
            int key = wine_trainingList.get(i).getClassLabel_real();
            if (voted.containsKey(key)) {
                voted.put(key, voted.get(key) + 1);
            } else {
                voted.put(key, 1);
            }
        }

        int numVotes = 0;
        int labelVotes = 0;

        for (int vote : voted.keySet()) {
            if (voted.get(vote) > numVotes) {
                numVotes = voted.get(vote);
                labelVotes = vote;
            }
        }

        return labelVotes;
    }

    /**
     * Get the wine_trainingList.
     *
     * @return the wine_trainingList
     */
    public static final List<Wine> getWine_trainingList() {
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
