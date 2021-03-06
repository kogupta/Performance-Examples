package vanilla.java.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by peter on 24/11/2014.
 */
public class UnsafeDemo {

    static final Unsafe UNSAFE;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        new MyType();
        memoryUsed();

        long start = memoryUsed();
        MyType mt = new MyType();
        long end = memoryUsed();
        System.out.printf("%s used %,d bytes%n", MyType.class, end - start);

        for (Field field : MyType.class.getDeclaredFields()) {
            long offset = UNSAFE.objectFieldOffset(field);
            System.out.printf("Field %s, offset %d%n", field, offset);
        }
    }

    public static long memoryUsed() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

}
