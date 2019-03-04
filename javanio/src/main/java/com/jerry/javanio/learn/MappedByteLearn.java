package com.jerry.javanio.learn;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteLearn {

    public static void main(String[] args) throws IOException {
        create5MFile();
        ByteBufferRead();
        MappedBufferRead();

    }

    private static void MappedBufferRead() throws IOException {
        String path = MappedByteLearn.class.getClassLoader().getResource("aa.txt").getPath();
        RandomAccessFile afile = new RandomAccessFile(path, "rw");
        FileChannel channel = afile.getChannel();
        long start = System.currentTimeMillis();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, afile.length());
        long end = System.currentTimeMillis();
        System.out.println("MappedBufferRead:" + (end - start));
        afile.close();
        // 这句加上会 输出不出预期的结果，有空研究一下
//        mappedByteBuffer.flip();
        for (int i = 0; i < 100; i++) {
            System.out.print((char) mappedByteBuffer.get());
        }
    }

    private static void ByteBufferRead() throws IOException {
        String path = MappedByteLearn.class.getClassLoader().getResource("aa.txt").getPath();
        RandomAccessFile rafile = new RandomAccessFile(path, "rw");
        FileChannel channel = rafile.getChannel();
        long start = System.currentTimeMillis();
        ByteBuffer bb = ByteBuffer.allocate((int) rafile.length());
        bb.clear();
        int read = channel.read(bb);
        long end = System.currentTimeMillis();
        System.out.println(read);
        System.out.println("ByteBufferRead:" + (end - start));
        rafile.close();
    }


    private static void create5MFile() throws IOException {
        String path = MappedByteLearn.class.getClassLoader().getResource("").getPath();
        System.out.println(path);
        String filename = path + "/aa.txt";
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        OutputStream ops = new FileOutputStream(file);
        for (int i = 0; i < 10000 * 40; i++) {
            ops.write("i am a line\r\n".getBytes());
        }
        ops.close();
    }

}
