import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        String folderPath = "/home/pristavkovlad/Java";
        File file = new File(folderPath);
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(calculator));
      //  System.out.println(file.length());
        //Arrays.stream(file.listFiles()).forEach(System.out :: println);
     //   System.out.println(getFolderSize(file));
    }

    public static long getFolderSize(File folder){
        if (folder.isFile()){
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}
