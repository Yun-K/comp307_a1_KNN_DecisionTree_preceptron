package part2;

public class Part2Main extends DecisionTree {

    public static void main(String[] args) {

        // if (args.length < 2) {
        // System.err.println(
        // "Please enter 2 arguments, the first for the path of hepatitis-training and the
        // second "
        // + "for the hepatitis-test.");
        // return;
        // }
        // System.out.println("============ Part 2 Yun ZHou, 300442776===============");
        // String trainfilePath = args[0];
        // String testfilePath = args[1];

        String trainfilePath = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part2/hepatitis-training";

        String testfilePath = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part2/hepatitis-test";

        DecisionTree dTree = new DecisionTree();
        dTree.loadFiles(trainfilePath, testfilePath);
        TreeNode tree = dTree.buildTree(train_instances, Tool2.categoryNames);
        tree.drawTree("");
        printAccuracyResult(tree);
        printBaseLineClassifier();

        // /* below for the report q2, 10 fold cross validation */
        // double totalAccuracy_decisionTree = 0.0;
        // double times = 0.0;
        // for (int i = 0; i < 10; i++) {
        // String trainPath_10fold =
        // "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part2/hepatitis-training-run-"
        // + String.valueOf(i);
        // String testfilePath_10fold =
        // "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part2/hepatitis-test-run-"
        // + String.valueOf(i);
        //
        // System.out.println("-----------------------------------------");
        // System.out.println("-----------------------------------------");
        // System.out
        // .println("The training set: \n\t hepatitis-training-run-" + String.valueOf(i));
        // System.out.println("The test set: \n\t hepatitis-test-run-" + String.valueOf(i));
        //
        // DecisionTree dtree_10fold = new DecisionTree();
        // dtree_10fold.loadFiles(trainPath_10fold, testfilePath_10fold);
        // TreeNode tree_1 = dtree_10fold.buildTree(train_instances, Tool2.categoryNames);
        // totalAccuracy_decisionTree += printAccuracyResult(tree_1);
        // times++;
        // // tree_1.drawTree("");
        // }
        // double average_accuracy_decisionTree = totalAccuracy_decisionTree / times;
        //
        // System.out.println("-----------------------------------------");
        // System.out.println("It runs " + times + " times");
        // System.out.printf("\nThe average accuracy for the decision tree is %.2f%%",
        // average_accuracy_decisionTree);
        // System.out.println("\n-----------------------------------------");
    }

}
