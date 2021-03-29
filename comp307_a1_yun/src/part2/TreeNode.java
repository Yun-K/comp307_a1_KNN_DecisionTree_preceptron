package part2;

import java.util.List;

public class TreeNode {

    /** for child node use */
    TreeNode leftChildNode = null, rightChildNode = null;

    private List<Instance> instancesList;

    private List<String> attributes;

    /** the attribute string that this node have */
    private String attributeValueString;

    private String liveOrDead;

    private double probability = Double.NaN;

    private boolean isLeafTreeNode = false;

    /**
     * A constructor. It construct a new instance of TreeNode.
     *
     * @param bestAttribute_string
     *            the attribute string that this node have
     * @param leftTreeNode
     *            the leftChild node
     * @param righTreeNode
     *            the rightChild node
     * @param probability
     */
    public TreeNode(String bestAttribute_string, TreeNode leftTreeNode, TreeNode righTreeNode,
            double probability) {
        this.attributeValueString = bestAttribute_string;
        this.leftChildNode = leftTreeNode;
        this.rightChildNode = righTreeNode;
        // System.out.println(probability);
        // assert !Double.isNaN(probability);
        // this.probability = probability;

    }

    /**
     * A constructor. It construct a new instance of TreeNode.
     *
     * @param mostPossible_liveOrDie
     * @param calculateProbability_majorityClass
     */
    public TreeNode(String mostPossible_liveOrDie, double calculateProbability_majorityClass) {
        this.liveOrDead = mostPossible_liveOrDie;
        this.probability = calculateProbability_majorityClass;
        // assert !Double.isNaN(calculateProbability_majorityClass);
        // assert !Double.isNaN(probability);
        this.isLeafTreeNode = true;
    }

    /**
     * Description: <br/>
     * Do a depth-first traversal of the tree in order to draw the tree.
     * 
     * @author Yun Zhou
     * @param indentString
     *            the tab
     */
    public void drawTree(String indentString) {
        TreeNode currentNode = this;// assign the value who can this method
        if (currentNode.isLeafTreeNode) {
            // error checking
            if (currentNode.getProbability() == 0.0) {
                System.out.printf("%sUnknown%n", indentString, attributeValueString);
            } else {
                System.out.printf("%sClass %s, prob=%.2f%n", indentString, liveOrDead,
                        currentNode.getProbability());
            }
        }
        // if it is non-leaf node
        else {
            System.out.printf("%s%s = True:%n", indentString, attributeValueString);
            currentNode.getLeftChildNode().drawTree(indentString + "\t");
            System.out.printf("%s%s = False:%n", indentString, attributeValueString);
            currentNode.getRightChildNode().drawTree(indentString + "\t");

        }

    }

    /**
     * Get the leftChildNode.
     *
     * @return the leftChildNode
     */
    public TreeNode getLeftChildNode() {
        return leftChildNode;
    }

    /**
     * Get the rightChildNode.
     *
     * @return the rightChildNode
     */
    public TreeNode getRightChildNode() {
        return rightChildNode;
    }

    /**
     * Get the instancesList.
     *
     * @return the instancesList
     */
    public List<Instance> getInstancesList() {
        return instancesList;
    }

    /**
     * Get the attributes.
     *
     * @return the attributes
     */
    public List<String> getAttributes() {
        return attributes;
    }

    /**
     * Get the attributeValueString.
     *
     * @return the attributeValueString
     */
    public String getAttributeValueString() {
        return attributeValueString;
    }

    /**
     * Get the liveOrDead.
     *
     * @return the liveOrDead
     */
    public String getLiveOrDead() {
        return liveOrDead;
    }

    /**
     * Get the probability.
     *
     * @return the probability
     */
    public double getProbability() {
        // if (Double.isNaN(probability)) {
        // probability = DecisionTree.calculateProbability_majorityClass(instancesList);
        //
        // }
        return probability;
    }

    /**
     * Get the isLeafTreeNode.
     *
     * @return the isLeafTreeNode
     */
    public boolean isLeafTreeNode() {
        return isLeafTreeNode;
    }

}
