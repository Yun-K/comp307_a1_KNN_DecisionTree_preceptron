package part1;

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

    /** which class does this wine instacne belongs */
    private int classLabel_real;

    /** the distance to the TestWine instance */
    private double distanceToTestWine;

    /** the feature size, the Wine has 13 attributes, so the value is 13. */
    public static int FEATURESIZE;

    /**
     * A constructor. It construct a new instance of Wine.
     *
     * @param features_list
     *            the list of the features that each wine instance have
     * @param classLabel_real
     *            the class that this wine instacne belongs
     */
    public Wine(List<Double> features_list, int classLabel_real) {
        this.features_list = features_list;
        this.classLabel_real = classLabel_real;
        FEATURESIZE = features_list.size();
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
     * Set the distanceToTestWine.
     *
     * @param distanceToTestWine
     *            the distanceToTestWine to set
     */
    public void setDistanceToTestWine(double distanceToTestWine) {
        this.distanceToTestWine = distanceToTestWine;
    }

}
