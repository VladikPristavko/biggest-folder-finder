import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {
    private Node node;

    public FolderSizeCalculator(Node node){
        this.node = node;
    }

    @Override
    protected Long compute() {
        if (node.getFolder().isFile()){
            long size = node.getFolder().length();
            node.setSize(size);
            return size;
        }
        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>();
        File[] files = node.getFolder().listFiles();
        assert files != null;
        for (File file : files) {
            Node child = new Node(file, node.getSizeLimit());
            FolderSizeCalculator folderSizeCalculator = new FolderSizeCalculator(child);
            folderSizeCalculator.fork();
            subTasks.add(folderSizeCalculator);
            node.addChild(child);
        }
        for (FolderSizeCalculator task : subTasks){
            sum += task.join();
        }
        node.setSize(sum);
        return sum;
    }
}
