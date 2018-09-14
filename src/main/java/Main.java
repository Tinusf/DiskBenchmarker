import java.util.Scanner;

public class Main {
    private static String resultList = "";

    public static void main(String[] args) {
        Integer[] fileSizes = {1, 2, 4, 8, 16, 32};
        runTests(fileSizes);
    }

    private static void runTests(Integer[] fileSizes) {
        System.out.println("If you stop the program whilst running you may need to delete a 'DiskBenchFile' file.");
        System.out.println("Mode - Size - Duration - Speed");
        Scanner scanner = new Scanner(System.in);
        for (Integer fileSize : fileSizes) {
            DiskBenchmarker diskBenchmarker = new DiskBenchmarker(fileSize);

            long t0 = System.currentTimeMillis();
            diskBenchmarker.writeTest();
            long t1 = System.currentTimeMillis();
            System.out.println(resultPrinter(t0, t1, fileSize, "Write"));
            System.out.println("Please clear your operating system cache using the following command:" +
                    "\nLinux users:    sudo sh –c \"echo 3 > /proc/sys/vm/drop_caches\" " +
                    "\nWindows users:    wsreset " +
                    "\nPress enter when done");
            scanner.nextLine();

            t0 = System.currentTimeMillis();
            diskBenchmarker.readTest();
            t1 = System.currentTimeMillis();
            System.out.println(resultPrinter(t0, t1, fileSize, "Read"));

            diskBenchmarker.deleteFile();
        }
        System.out.println("Completed!");
        System.out.println(resultList);
    }

    private static String resultPrinter(long t0, long t1, int fileSize, String mode) {
        double duration = (t1 - t0) / 1000d; // in seconds.
        double speedInMBs = (1024 * fileSize) / duration;
        double speedRounded = (double) Math.round(speedInMBs * 1000d) / 1000d;
        String output = (mode + " - " + fileSize + " GB - " + duration + " s  - " + speedRounded + " MB/s");
        resultList += output + "\n";
        return output;
    }
}
