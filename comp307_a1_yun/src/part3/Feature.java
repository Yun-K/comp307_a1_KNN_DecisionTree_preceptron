package part3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: <br/>
 * The feature class, part 3 need to generate 50 random feature.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Feature {
    /** the row and col of the feature */
    private int[] row, col;

    /** the sgn store the boolean values */
    private boolean[] sgn;

    /** first feature is the dummy. */
    private boolean isDummy = false;

    /** A list of feature values */
    private List<Integer> integerValues;

    /**
     * A constructor. It construct a new instance of Feature.
     *
     */
    public Feature() {
        setUpRandomFeature();
        this.setIntegerValues(new ArrayList<Integer>());
    }

    /**
     * A constructor. It construct a new instance of Feature. Set the dummy as true since it's
     * the first.
     * 
     * @param isDummy
     */
    public Feature(boolean isDummy) {
        this.isDummy = isDummy;
        this.setIntegerValues(new ArrayList<Integer>());
    }

    /**
     * Description: <br/>
     * set up the random feature.
     * 
     * @author Yun Zhou
     */
    public void setUpRandomFeature() {
        row = new int[3];
        col = new int[3];
        sgn = new boolean[3];

        for (int i = 0; i < col.length; i++) {
            // define the range
            int min = 0;
            int max = 9;
            int range = max - min + 1;
            // generate random numbers within 0 to 9(including)
            int randomNumber = (int) (Math.random() * range) + min;
            col[i] = randomNumber;
        }
        for (int i = 0; i < row.length; i++) {
            // define the range
            int min = 0;
            int max = 9;
            int range = max - min + 1;
            // generate random numbers within 0 to 9(including)
            int randomNumber = (int) (Math.random() * range) + min;
            row[i] = randomNumber;
        }

        for (int i = 0; i < sgn.length; i++) {
            double rand = Math.random();
            boolean value = false;
            if (rand < 0.5) {
                value = true;
            }
            sgn[i] = value;
        }

    }

    /**
     * Description: <br/>
     * From the assignment handout.
     * 
     * An algorthim which can compute the value of the feature for a given image (represented
     * as a 2D Boolean array) .
     * 
     * @author Yun Zhou
     * @param image
     *            the image to be workd on
     * @return the value of the feature for a given image
     */
    public int getValue(ImageP3 image) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            if (image.getPixels()[row[i]][col[i]] == sgn[i]) {
                sum++;
            }
        }
        return (sum >= 2) ? 1 : 0;// ternary operator
    }

    /**
     * Description: <br/>
     * Return the feature strings, which print the row, col and sgn.
     * 
     * @author Yun Zhou
     * @return the feature strings, which print the row, col and sgn.
     */
    public String printFeatures() {
        String feat = "Features{ rows = ";
        feat += Arrays.toString(row) + "  cols = ";
        feat += Arrays.toString(col) + "  sgn = ";
        feat += Arrays.toString(sgn) + " }";
        System.out.println(feat);
        return feat;
    }

    /**
     * Get the row.
     *
     * @return the row
     */
    public int[] getRow() {
        return row;
    }

    /**
     * Get the col.
     *
     * @return the col
     */
    public int[] getCol() {
        return col;
    }

    /**
     * Get the sgn.
     *
     * @return the sgn
     */
    public boolean[] getSgn() {
        return sgn;
    }

    /**
     * Get the dummy.
     *
     * @return the dummy
     */
    public boolean isDummy() {
        return isDummy;
    }

    /**
     * Set the dummy.
     *
     * @param dummy
     *            the dummy to set
     */
    public void setDummy(boolean dummy) {
        this.isDummy = dummy;
    }

    /**
     * Get the integerValues.
     *
     * @return the integerValues
     */
    public List<Integer> getIntegerValues() {
        return integerValues;
    }

    /**
     * Set the integerValues.
     *
     * @param integerValues
     *            the integerValues to set
     */
    public void setIntegerValues(List<Integer> integerValues) {
        this.integerValues = integerValues;
    }

}
