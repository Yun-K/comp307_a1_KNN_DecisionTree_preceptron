package part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Description: <br/>
 * From the helper-code.java.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Instance {

    // private int category;

    private List<Boolean> vals;

    private String liveOrDead;

    // public Instance(int cat, Scanner s) {
    // category = cat;
    // vals = new ArrayList<Boolean>();
    // while (s.hasNextBoolean()) {
    // vals.add(s.nextBoolean());
    // }
    // }

    /**
     * Mine constructor. It construct a new instance of Instance.
     *
     * @param category_class_liveOrDie
     * @param attributes_list
     */
    public Instance(String category_class_liveOrDie, List<Boolean> attributes_list) {
        this.liveOrDead = category_class_liveOrDie;
        this.vals = attributes_list;
    }

    public boolean getAttributeValue(int index) {
        return vals.get(index);
    }
    //
    // public int getCategory() {
    // return category;
    // }

    public String toString() {
        // StringBuilder ans = new StringBuilder(categoryNames.get(category));
        // ans.append(" ");
        // for (Boolean val : vals) {
        // ans.append(val ? "true " : "false ");
        // }
        // return ans.toString();
        return "not implement toString() yet.";

    }

    /**
     * Get the liveOrDead.
     *
     * @return the liveOrDead
     */
    public String getLiveOrDead() {
        return liveOrDead;
    }

}
