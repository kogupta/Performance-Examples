package vanilla.java.references;

import java.util.stream.IntStream;

/**
 * Created by peter.lawrey on 22/11/2015.
 */
public class ObjectSizeMain {
    public static void main(String[] args) {
        IntStream.range(0, 1).forEach(System.out::println);

        for (int i = 0; i < 3; i++) {
            long used1 = memoryUsed();
            long used2 = memoryUsed();
            if (used1 == used2)
                System.err.println("You need to turn off the TLAB with -XX:-UseTLAB");
            else
                System.out.printf("Space used by one object is " + (used2 - used1) + " bytes%n");
        }
    }

    public static long memoryUsed() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }
}
