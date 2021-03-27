package main;

import part1.KNearestNeighbour;

public class Part1Main {

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
            KNearestNeighbour.KNeighbours = i;
            System.out.println(
                    "-----------------K= " + i + "------------------------------");
            knn.execute();
        }
    }

}
