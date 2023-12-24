package freeway;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class VersionComparator implements Comparator<File> {
    @Override
    public int compare(File s1, File s2) {
        return Arrays.compare(getVersion(s1), getVersion(s2));
    }

    public int[] getVersion(File f) {
        String versionString = f.getName().substring(1).split("__")[0];
        return Arrays.stream(versionString.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }
}
