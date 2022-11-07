import java.io.File;
import java.util.ArrayList;

public class Node {
    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level = 0;
    private long sizeLimit;
    public Node(File folder, long sizeLimit){
        this.folder = folder;
        this.children = new ArrayList<>();
        this.sizeLimit = sizeLimit;
    }
    public long getSizeLimit(){
        return this.sizeLimit;
    }
    public File getFolder() {
        return folder;
    }
    public void addChild(Node node){
        node.setLevel(level + 1);
        children.add(node);
    }
    private void setLevel(int level) {
        this.level = level;
    }
    private int getLevel(){
        return this.level;
    }
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    private static String getHumanReadableSize(long size){
        String[] names = {"B", "Kb", "Mb", "Gb", "Tb"};
        for (int i = 0; i < names.length; i++){
            if (size < Math.pow(1024, i + 1)){
                return Math.round(size/Math.pow(1024,i)*100.0)/100.0 + names[i];
            }
        }
        return "";
    }
    //
    public static long getSizeFromHumanReadable(String size){
        String names = "BKMGT";
        if (size.contains("b")) size = size.substring(0, size.indexOf("b"));
        int index = names.indexOf(size.charAt(size.length()-1));
        return (long) (Double.parseDouble(size.substring(0, size.length() - 1))*Math.pow(1024, index));
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        String size = getHumanReadableSize(getSize());
        if (getSizeFromHumanReadable(size) >= getSizeLimit()) {
            builder.append(folder.getName()).append(" - ")
                    .append(size).append(System.lineSeparator());
            children.forEach(e -> builder.append("  ".repeat(getLevel())).append(e.toString()));
        }
        return builder.toString();
    }
}
