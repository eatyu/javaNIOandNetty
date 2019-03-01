package com.jerry.javanio.learn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 练习使用NIO的方式获取file
 */
public class IOandNIOinFilePractice {
    public static void main(String[] args) {
        getFileAccess();
    }

    private static void getFileAccess() {
        String path = IOandNIOinFilePractice.class.getClassLoader().getResource("nio.txt").getPath();
        try {
            RandomAccessFile accessFile = new RandomAccessFile(path, "rw");

            FileChannel channel = accessFile.getChannel();

            ByteBuffer bb = ByteBuffer.allocate(1024);
            int read = channel.read(bb);
            if (read != -1) {
                bb.flip();
                System.out.println((char) bb.get());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
