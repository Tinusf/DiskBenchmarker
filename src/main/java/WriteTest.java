import java.util.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.CREATE;

public class WriteTest {
    private final int gigaBytes;
    private final long NBLOCKS;
    private static final int BLOCKSIZE = 8192; // 8KB


    public void test() {
        Path file = Paths.get(System.getProperty("user.dir"), "DiskBenchFile" + gigaBytes);
        try {
            SeekableByteChannel out = Files.newByteChannel(file,EnumSet.of(CREATE, APPEND));
            for (int i = 0; i < NBLOCKS; i ++) {
                ByteBuffer buff = ByteBuffer.allocate(BLOCKSIZE);
                out.write (buff);
            }
            Files.delete(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public WriteTest(int gigaBytes) {
        this.gigaBytes = gigaBytes;
        this.NBLOCKS = 125000 * gigaBytes;
    }
}
