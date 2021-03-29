package part3;

/**
 * Description: <br/>
 * The feature class, part 3 need to generate 50 random feature.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Feature {
    private int[] row;

    private int[] col;

    private boolean[] sgn;

    /**
     * A constructor. It construct a new instance of Feature.
     *
     */
    public Feature() {
        setUpRandomFeature();
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
            if (rand < .5) {
                value = true;
            }
            sgn[i] = value;
        }

    }

    /**
     * Description: <br/>
     * From the assignment handout. Return the value of a given image.
     * 
     * @author Yun Zhou
     * @param image
     *            the image to be workd on
     * @return Return the value of a given image.
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
     * Set the row.
     *
     * @param row
     *            the row to set
     */
    public void setRow(int[] row) {
        this.row = row;
    }

    /**
     * Set the col.
     *
     * @param col
     *            the col to set
     */
    public void setCol(int[] col) {
        this.col = col;
    }

    /**
     * Set the sgn.
     *
     * @param sgn
     *            the sgn to set
     */
    public void setSgn(boolean[] sgn) {
        this.sgn = sgn;
    }

}
