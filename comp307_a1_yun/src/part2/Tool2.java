package part2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: <br/>
 * Tool class, from helper-code.java and my method as well.
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class Tool2 {

    // some bits of java code that you may use if you wish.
    // assumes that the enclosing class has fields:
    static int numCategories;

    // static int numAtts;

    /** the first line of the txt */
    static List<String> categoryNames;

    /**
     * Description: <br/>
     * A method for calculating the entropy of the current node. The maths algorthem is from
     * the slide.
     * 
     * @author Yun Zhou
     * @param instances_currentNodeHave
     * @return the entropy
     */
    public static double calculateImpurity_entropy(List<Instance> instances_currentNodeHave) {
        double liveInstances_num = 0.0;
        double dieInstances_num = 0.0;
        for (Instance instance : instances_currentNodeHave) {
            if (instance.getLiveOrDead().equals("live")) {
                liveInstances_num++;
                continue;
            }
            dieInstances_num++;
        }

        // get the entropy
        double denominator = (liveInstances_num + dieInstances_num)
                * (liveInstances_num + dieInstances_num);
        double numerator = liveInstances_num * dieInstances_num;

        if (denominator == 0.0 || numerator == 0.0) {
            return 0.0;
        }
        // from the tutorial 3
        if (liveInstances_num == dieInstances_num) {
            return Double.POSITIVE_INFINITY;

        }

        double entropy = numerator / denominator;
        return entropy;

    }

    /**
     * Description: <br/>
     * Load the text file that contains Patient instances, and return the list of all patient
     * instances from file. In this case, either training or test file.
     * 
     * @author Yun Zhou
     * @param pathName
     *            the path to the instance file
     * @return the list of all instances from file
     */
    public static List<Instance> onLoad(String pathName) {
        // precondition check
        // check if the path name does not contains the valid file name
        if (!(pathName.contains("hepatitis-training") || pathName.contains("hepatitis-test"))) {
            System.err
                    .print("File not found, please check if the PATH to the HEPATITIS data is correct!");
            // return null;
            throw new IllegalArgumentException();
        }

        // the list to return
        List<Instance> loadedInstance_list = new ArrayList<Instance>();

        try {
            // the fileReader objecet for reading
            File file = new File(pathName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fileReader);

            // the first line is the describtion not the data, so read it first
            String instance_line = bReader.readLine();
            // assign it into the category
            String[] categoryStrings = instance_line.split(" ");
            categoryNames = new ArrayList<String>(Arrays.asList(categoryStrings));
            numCategories = categoryNames.size();

            // System.out.println(categoryNames);
            // System.out.println(numCategories + " categories!!!!!YUN ZHOU!!!");

            // keep reading the txt file until the end of the stream has been reached
            while (instance_line != null) {
                instance_line = bReader.readLine();

                if (instance_line == null) {
                    break;
                }
                // split the line by space
                String[] instance_info = instance_line.split(" ");

                // assign the instance is live or dead, it is the first element
                String category_class_liveOrDie = instance_info[0];

                // the attribute list
                List<Boolean> attributes_list = new ArrayList<>();

                for (int i = 1; i < instance_info.length; i++) {
                    // add it into the list
                    attributes_list.add(Boolean.parseBoolean(instance_info[i]));
                }
                loadedInstance_list.add(new Instance(category_class_liveOrDie, attributes_list));

                // loadedInstance_list.add(new Instance(attributes_list, category_class));
                // System.out.println("fukkkkkkkkkkkkkk");
            }

            bReader.close();// close to release resources

            return loadedInstance_list;
        } catch (Exception e) {
            throw new RuntimeException("The file has not beed read successfully", e);
        }

    }

}
