import java.util.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.*;
import java.io.IOException;

import static java.nio.file.StandardOpenOption.*;

public class DiskBenchmarker {
    private final int gigaBytes;
    private final long NBLOCKS;
    private static final int BLOCKSIZE = 8192; // 8KB
    private Path file;

    public DiskBenchmarker(int gigaBytes) {
        this.gigaBytes = gigaBytes;
        this.NBLOCKS = 125000 * gigaBytes;

        String fileName = "DiskBenchFile" + gigaBytes;
        this.file = Paths.get(System.getProperty("user.dir"), fileName);
    }

    public void writeTest() {
        try {
            SeekableByteChannel out = Files.newByteChannel(file, EnumSet.of(CREATE, APPEND));
            for (int i = 0; i < NBLOCKS; i++) {
                ByteBuffer buff = ByteBuffer.allocate(BLOCKSIZE);
                out.write(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readTest() {
        try {
            SeekableByteChannel in = Files.newByteChannel(file, EnumSet.of(CREATE, READ));
            ByteBuffer buff = ByteBuffer.allocate(BLOCKSIZE);
            for (int i = 0; i < NBLOCKS; i++) {
                int bytesRead = in.read(buff);
                buff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile() {
        try {
            Files.delete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

