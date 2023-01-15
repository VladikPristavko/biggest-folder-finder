import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        ParametersBag bag = new ParametersBag(args);
        Node root = new Node(new File(bag.getPath()), bag.getLimit());
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);
    }
}
