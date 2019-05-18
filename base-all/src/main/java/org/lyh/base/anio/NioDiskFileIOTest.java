package org.lyh.base.anio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2018/5/13 21:22
 */
public class NioDiskFileIOTest {
    private static final int BUF_SIZE = 4096 * 1024;
    public static final String C_WINDOWS_SYSTEM32_DRIVERS_ETC_HOSTS = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    public static final String F_TEMP_HOSTS = "F:/temp/hosts";

    public static void main(String[] args) throws IOException  {
        File inputFile = new File(C_WINDOWS_SYSTEM32_DRIVERS_ETC_HOSTS);
        File outFile = new File(F_TEMP_HOSTS);

        FileChannel inputFileChannel = new RandomAccessFile(inputFile, "r").getChannel();
        FileChannel outputFileChannel = new RandomAccessFile(outFile, "rw").getChannel();

        ByteBuffer readBuffer = ByteBuffer.allocateDirect(BUF_SIZE);

        while(inputFileChannel.read(readBuffer) != -1){
            int readLength = readBuffer.position();
        }
    }
}
