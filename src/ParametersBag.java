import java.io.File;

public class ParametersBag {
    private String path;
    private long limit;
    public ParametersBag(String[] args){
        String lSep = System.lineSeparator();
        String inputExample = "Input example -> java -jar $$$.jar -d %dirPath -l %limit(12B, 128Kb)";
        if (args.length != 4)
            throw new IllegalArgumentException(lSep + " Wrong number of params! " + lSep + inputExample);
        if (!new File(args[1]).exists())
            throw new IllegalArgumentException(lSep + " Wrong path! "+ lSep + inputExample);
        this.path = args[1];
        if (!args[3].matches("\\d+[BKMGT]b?"))
            throw new IllegalArgumentException(lSep + " Wrong limit format! "+ lSep + inputExample);
        this.limit = Node.getSizeFromHumanReadable(args[3]);

    }
    public String getPath(){
        return this.path;
    }
    public long getLimit(){
        return this.limit;
    }
}
