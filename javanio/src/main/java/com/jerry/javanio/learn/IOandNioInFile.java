package com.jerry.javanio.learn;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class IOandNioInFile {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.forEach(s -> System.out.println(s));

        StringBuffer sb;
        ByteBuffer bb;


//        method1();
        method1_1();
    }


    public static void method2() {
        InputStream in = null;
        try {
            in = IOandNioInFile.class.getClassLoader().getResourceAsStream("nomal_io.txt");
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buf[i]);
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void method1() {
        RandomAccessFile aFile = null;
        try {
            String path = IOandNioInFile.class.getClassLoader().getResource("nio.txt").getPath();
//            path = path.substring(1);
            System.out.println();
            System.out.println(path);
            aFile = new RandomAccessFile(path, "rw");
            FileChannel fileChannel = aFile.getChannel();

//            FileInputStream fileInputStream = new FileInputStream(path);
//            FileChannel fileChannel = fileInputStream.getChannel();


            ByteBuffer buf = ByteBuffer.allocate(1024);
            System.out.println(buf.toString());
            // 读文件数据到buf中
            int bytesRead = fileChannel.read(buf);
            System.out.println("bytesRead == " + bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
            System.out.println("end");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 学习 rewind 方法
     */
    public static void method1_1() {
        RandomAccessFile file;
        try {
            String path = IOandNioInFile.class.getClassLoader().getResource("nio.txt").getPath();
            file = new RandomAccessFile(path, "rw");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(1024);
            int read = fileChannel.read(bb);
            if (read != -1) {
                bb.flip();
                System.out.println("1:" + (char) bb.get());
                System.out.println("2:" + (char) bb.get());
                System.out.println("3:" + (char) bb.get());
                System.out.println("4:" + (char) bb.get());
                System.out.println("5:" + (char) bb.get());

                // rewind将position 置为 0 ，limit保持不变
                bb.rewind();
                System.out.println("6:" + (char) bb.get());
                System.out.println("7:" + (char) bb.get());
                System.out.println("8:" + (char) bb.get());
                System.out.println("9:" + (char) bb.get());
                System.out.println("10:" + (char) bb.get());

            }


        } catch (Exception e) {

        }

    }


}
