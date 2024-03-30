package org.example.dataOutputUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class DataOutput {
    //task1
    public static void writeArrayToBinaryStream(int[] array, OutputStream outputStream) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            for (int j : array) {
                dataOutputStream.writeInt(j);
            }
        }
    }

    public static int[] readArrayFromBinaryStream(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int[] array = new int[dataInputStream.available() / 4];
        for (int i = 0; i < array.length; i++) {
            array[i] = dataInputStream.readInt();
        }
        dataInputStream.close();
        return array;
    }

    //task2
    public static void writeArrayToStream(int[] array, Writer writer) {
        try {
            for (int num : array) {
                writer.write(num + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readArrayFromStream(int[] array, Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String[] strNums;
        try {
            bufferedReader.lines();
            String line = bufferedReader.readLine();
            strNums = line.split(" ");
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(strNums[i]);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //task3
    public static int[] readArrayRandomAccessFile(RandomAccessFile randomAccessFile, int position, int length) throws IOException {
        randomAccessFile.seek(position);
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = randomAccessFile.readInt();
        }
        return array;
    }

    //task4
    public static List<File> getFilesWithExtension(File directory, String extension) {
        List<File> files = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile() && file.getName().endsWith(extension)) {
                files.add(file);
            }
        }
        return files;
    }

    //task5
    public static List<File> filterByNameRegex(File root, String regex) {
        List<File> result = new ArrayList<>();
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            if (files == null) return result;

            for (File file : files) {
                if (file.isDirectory()) {
                    result.addAll(DataOutput.filterByNameRegex(file, regex));
                } else if (Pattern.matches(regex, file.getName())) {
                    result.add(file);
                }
            }
        }
        return result;
    }

}