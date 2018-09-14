import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] fileSizes = {1, 2, 4, 8, 16, 32};
        System.out.println("If you stop the program whilst running you may need to delete a 'DiskBenchFile' file.");
        System.out.println("Size - Duration - Speed");
        for (Integer fileSize : fileSizes) {
            long startTime = System.currentTimeMillis();
            WriteTest writeTest = new WriteTest(fileSize);
            writeTest.test();
            long stopTime = System.currentTimeMillis();
            double duration = (stopTime - startTime) / 1000.0; // in seconds.
            double speedInMBs = (1024*fileSize) / duration;
            double speedRounded = (double)Math.round(speedInMBs * 1000d) / 1000d;
            System.out.println("" + fileSize + " GB - " + duration + " s  - " + speedRounded + " MB/s");
        }
    }
}
