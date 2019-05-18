package org.lyh.base.anio;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2018/5/13 21:05
 */
public class AioFileWriteTest {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
        File outFile = new File("F:/temp/hosts");

        FileDescriptor inputFileDescriptor = new RandomAccessFile(inputFile, "r").getFD();
        FileDescriptor outputFileDescriptor = new RandomAccessFile(outFile, "rw").getFD();

//        WindowsAsynchronousFileChannelImpl windowsAsynchronousFileChannel
//                = new WindowsAsynchronousFileChannelImpl(inputFileDescriptor,true,false,null,true);

    }
}
