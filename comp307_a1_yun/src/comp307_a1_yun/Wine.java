package comp307_a1_yun;

import java.util.List;

/**
 * Description: <br/>
 * A class that represent the Wine instance, for assignment 1 part 1.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Wine {

    /** the list of the features that each wine instance have */
    private List<Double> features_list;

    private int classLabel_real, classLabel_guess;

    private double distanceToTestWine;

    /**
     * A constructor. It construct a new instance of Wine.
     *
     * @param features_list
     * @param classLabel_int
     */
    public Wine(List<Double> features_list, int classLabel_int) {
        this.features_list = features_list;
        this.classLabel_real = classLabel_int;

    }

    /**
     * Get the features_list.
     *
     * @return the features_list
     */
    public List<Double> getFeatures_list() {
        return features_list;
    }

    /**
     * Get the classLabel_real.
     *
     * @return the classLabel_real
     */
    public int getClassLabel_real() {
        return classLabel_real;
    }

    /**
     * Get the classLabel_guess.
     *
     * @return the classLabel_guess
     */
    public int getClassLabel_guess() {
        return classLabel_guess;
    }

    /**
     * Get the distanceToTestWine.
     *
     * @return the distanceToTestWine
     */
    public double getDistanceToTestWine() {
        return distanceToTestWine;
    }

    /**
     * Set the features_list.
     *
     * @param features_list
     *            the features_list to set
     */
    public void setFeatures_list(List<Double> features_list) {
        this.features_list = features_list;
    }

    /**
     * Set the classLabel_real.
     *
     * @param classLabel_real
     *            the classLabel_real to set
     */
    public void setClassLabel_real(int classLabel_real) {
        this.classLabel_real = classLabel_real;
    }

    /**
     * Set the classLabel_guess.
     *
     * @param classLabel_guess
     *            the classLabel_guess to set
     */
    public void setClassLabel_guess(int classLabel_guess) {
        this.classLabel_guess = classLabel_guess;
    }

    /**
     * Set the distanceToTestWine.
     *
     * @param distanceToTestWine
     *            the distanceToTestWine to set
     */
    public void setDistanceToTestWine(double distanceToTestWine) {
        this.distanceToTestWine = distanceToTestWine;
    }

}
