package part2;

import java.util.ArrayList;
import java.util.List;

public class DecisionTree {

    /** a list of training instances from the txt file */
    static List<Instance> train_instances = new ArrayList<Instance>();

    /** a list of testing instances reading from the txt file */
    static List<Instance> test_instances = new ArrayList<Instance>();

    /**
     * Description: <br/>
     * To build a decision tree.
     * 
     * @author Yun Zhou
     * @param allInstances_current
     * @param attribute_current
     * @return
     */
    public TreeNode buildTree(List<Instance> allInstances_current,
            List<String> attribute_current) {
        // remove the Class as it contains dead or live, not boolean
        if (attribute_current.contains("Class")) {
            attribute_current.remove("Class");
        }
        assert attribute_current.contains("Class") == false;

        // no instance means it can not do the decision any more, it reaches the leaf
        if (allInstances_current == null || allInstances_current.isEmpty()) {
            // return a leaf node that contains the name and
            // probability of the most probable class across the whole training set
            // first arg is the name, second is th probability
            return new TreeNode(getMostPossible_liveOrDie(train_instances),
                    calculateProbability_majorityClass(train_instances));
        }
        /*
         * if instances are pure (i.e. all belong to the same class)
         * 
         * then: return a leaf node that contains the name of the class and probability 1
         */
        else if (Tool2.calculateImpurity_entropy(allInstances_current) == 0.0) {
            // first arg is the name, second is the probability which is 1
            return new TreeNode(getMostPossible_liveOrDie(allInstances_current), 1.0);

        } else if (attribute_current.isEmpty()) {
            // return a leaf node that contains the name and probability of the majority class
            // of instances (chosen randomly if classes are equal)
            // first arg is the name, second is the probability
            return new TreeNode(getMostPossible_liveOrDie(allInstances_current),
                    calculateProbability_majorityClass(allInstances_current));
        }
        /*
         * DO THE RECURSION:
         * 
         * find the best attribute,the best attribute should be with the lowest impurity , so
         * that the depth of the tree can be the lowest
         */
        String bestAttribute_string = "";// store the best attribute

        // store the left and right child instances, it's based on the best attribute
        List<Instance> best_trueInstances_list = new ArrayList<Instance>();
        List<Instance> best_falseInstances_list = new ArrayList<Instance>();
        for (String attributeString : attribute_current) {

            // separte the Instances to two lists
            List<Instance> true_instance_list_temp = new ArrayList<Instance>();
            List<Instance> false_instance_list_temp = new ArrayList<Instance>();

            // do need to -1 because for the Instance.attributes, we don't have live/dead
            // do not need to -1 anymore since at the top of the method, the Class is removed
            int index = Tool2.categoryNames.indexOf(attributeString);// - 1;
            for (Instance instance : allInstances_current) {
                if (instance.getAttributeValue(index)) {
                    true_instance_list_temp.add(instance);
                } else {
                    false_instance_list_temp.add(instance);
                }
            }

            // compute the impurity
            double bestImpurity = Double.POSITIVE_INFINITY;
            double trueList_impurity = 0.0,
                    falseList_impurity = 0.0;
            if (!true_instance_list_temp.isEmpty()) {
                trueList_impurity = Tool2.calculateImpurity_entropy(true_instance_list_temp);
            }
            if (!false_instance_list_temp.isEmpty()) {
                falseList_impurity = Tool2.calculateImpurity_entropy(false_instance_list_temp);
            }
            // get the weighted average impurity, the maths is from slide 16, lec6
            double weighted_true = trueList_impurity
                    * ((double) true_instance_list_temp.size()
                            / (double) allInstances_current.size()),

                    weighted_false = falseList_impurity
                            * ((double) false_instance_list_temp.size()
                                    / (double) allInstances_current.size());
            double weighted_average_impurity = weighted_true + weighted_false;

            if (weighted_average_impurity < bestImpurity) {
                bestImpurity = weighted_average_impurity;
                bestAttribute_string = attributeString;
                best_trueInstances_list = true_instance_list_temp;
                best_falseInstances_list = false_instance_list_temp;
            }
        }
        // build subtrees using the remaining attributes:
        List<String> remaining = new ArrayList<String>(attribute_current);
        remaining.remove(bestAttribute_string);

        // do the recursion:
        TreeNode leftTreeNode = buildTree(best_trueInstances_list, remaining);
        TreeNode righTreeNode = buildTree(best_falseInstances_list, remaining);

        return new TreeNode(bestAttribute_string, leftTreeNode, righTreeNode,
                calculateProbability_majorityClass(allInstances_current));

    }

    public static double printAccuracyResult(TreeNode tree) {
        System.out
                .println(
                        "\n-----------Decision Tree Accuracy result------------------------------");
        double correctGuessNumber = 0.0;
        double totalTestFileSize = test_instances.size() * 1.0;
        for (Instance test_instance : test_instances) {
            String guess_result = guess_liveOrDie(tree, test_instance);
            if (guess_result.equals(test_instance.getLiveOrDead())) {
                correctGuessNumber++;
            }
        }
        System.out.println("-----------------------------------------");
        System.out.println("Total number of correct guessed instances: " + correctGuessNumber);
        System.out.println("Total test instances: " + totalTestFileSize);
        System.out
                .println("The accuracy is: " + correctGuessNumber + " out of " + totalTestFileSize);
        double accuracy = (correctGuessNumber / totalTestFileSize) * 100;
        System.out.printf("Accuracy: %.2f%%", accuracy);
        System.out.println("\n-----------------------------------------");
        return accuracy;
    }

    public static double printBaseLineClassifier() {
        System.out
                .println(
                        "\n-----------BaseLine classifier Accuracy result------------------------------");
        double correctGuessNumber = 0.0;
        double totalTestFileSize = test_instances.size() * 1.0;
        for (Instance test_instance : test_instances) {
            // always predicts the most frequent class in the training set)
            String guess_result = getMostPossible_liveOrDie(train_instances);
            if (guess_result.equals(test_instance.getLiveOrDead())) {
                correctGuessNumber++;
            }
        }
        System.out.println("-----------------------------------------");
        System.out.println("Total number of correct guessed instances: " + correctGuessNumber);
        System.out.println("Total test instances: " + totalTestFileSize);
        System.out
                .println("The accuracy is: " + correctGuessNumber + " out of " + totalTestFileSize);
        double accuracy = (correctGuessNumber / totalTestFileSize) * 100;
        System.out.printf("Accuracy: %.2f%%", accuracy);
        System.out.println("\n-----------------------------------------");
        return accuracy;

    }

    /**
     * Description: <br/>
     * Guess the instance argument is live or die by using the provided decison tree.
     * 
     * @author Yun Zhou
     * @param tree
     *            the provided tree
     * @param instanceToGuess
     *            instance to guess
     * @return live or die
     */
    public static String guess_liveOrDie(TreeNode tree, Instance instanceToGuess) {
        if (tree.isLeafTreeNode()) {
            return tree.getLiveOrDead();
        }
        // do the recursion to find the leafNode

        // the index need to -1 due to the attribute Class need to be ignored
        int index = Tool2.categoryNames.indexOf(tree.getAttributeValueString()) - 1;
        if (instanceToGuess.getAttributeValue(index) == true) {
            return guess_liveOrDie(tree.getLeftChildNode(), instanceToGuess);
        } else {
            return guess_liveOrDie(tree.getRightChildNode(), instanceToGuess);
        }

    }

    /**
     * Description: <br/>
     * Load files first.
     * 
     * @author Yun Zhou
     * @param trainfilePath
     * @param testfilePath
     */
    public void loadFiles(String trainfilePath, String testfilePath) {
        train_instances = Tool2.onLoad(trainfilePath);
        test_instances = Tool2.onLoad(testfilePath);
    }

    /**
     * Description: <br/>
     * FOr calculating the most possible result which is die or live from the given instances.
     * 
     * @author Yun Zhou
     * @param instances
     *            instance list to check
     * @return live or die
     */
    protected static String getMostPossible_liveOrDie(List<Instance> instances) {
        int liveCases = 0;
        int deadCases = 0;
        for (int i = 0; i < instances.size(); i++) {
            if (instances.get(i).getLiveOrDead().equalsIgnoreCase("Live")) {
                liveCases++;
                continue;
            }
            deadCases++;
        }
        if (liveCases > deadCases) {
            return "live";
        } else if (liveCases < deadCases) {
            return "die";
        }
        // follow the handout, which random return
        double ran = Math.random();
        if (ran > .5) {
            return "live";
        }
        return "die";

    }

    /**
     * Description: <br/>
     * Calculate the probability of the majority class from the instance list.
     * 
     * @author Yun Zhou
     * @param instances
     *            the list of instances
     * @return the probabiliy of the majority class
     */
    public static double calculateProbability_majorityClass(List<Instance> instances) {
        double liveCases = 0.0;
        double deadCases = 0.0;
        for (Instance instance : instances) {
            if (instance.getLiveOrDead().equalsIgnoreCase("live")) {
                liveCases = liveCases + 1.0;
            }
            if (instance.getLiveOrDead().equalsIgnoreCase("die")) {
                deadCases = deadCases + 1.0;
            }
        }
        double size = instances.size() * 1.0;
        if (liveCases > deadCases) {
            return liveCases / size;
        }
        return deadCases / size;
    }
}
