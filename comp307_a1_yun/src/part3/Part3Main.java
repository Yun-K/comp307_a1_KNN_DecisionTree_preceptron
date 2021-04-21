package part3;

public class Part3Main extends Preceptron {
    public Part3Main(String filePath) {
        super(filePath);
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println(
                    "Please enter one argument, which should be the path of the image.data.");
            return;
        }
        String filePath = args[0];

        // String filePath = "/Users/11973/git/comp307_a1_yun/comp307_a1_yun/ass1_data/part3/"
        // + "image.data";

        System.out.println("============ Part 3 Yun ZHou, 300442776===============");
        Preceptron preceptron = new Preceptron(filePath);
        preceptron.execute();
    }

}
