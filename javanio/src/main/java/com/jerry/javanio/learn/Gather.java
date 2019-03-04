package com.jerry.javanio.learn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Gather {

    public static void main(String[] args) throws IOException {
        gather();
    }

    private static void gather() throws IOException {
        String path = MappedByteLearn.class.getClassLoader().getResource("").getPath();
        System.out.println(path);
        String filename = path + "/gather.txt";
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        ByteBuffer header = ByteBuffer.allocate(50);
        ByteBuffer body = ByteBuffer.allocate(50);
        header.put("header{}".getBytes("utf-8"));
        body.put("b{\"name\":tom}".getBytes("utf-8"));
//        header.flip();
        header.rewind();
        body.rewind();
//        header.put(new byte[]{'1','2'});
//        header.put(new byte[]{'3','2'});
        ByteBuffer[] bbs = {header, body};

        FileOutputStream ops = new FileOutputStream(file);
        FileChannel channel = ops.getChannel();

        channel.write(bbs);
        channel.close();
        ops.close();

    }
}
