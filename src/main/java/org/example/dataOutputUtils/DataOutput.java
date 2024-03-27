package org.example.dataOutputUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
public class DataOutput {
    //task1
    public static void writeArrayToBinaryStream(int[] array, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        for (int i = 0; i < array.length; i++) {
            dataOutputStream.writeInt(array[i]);
        }
        dataOutputStream.flush();
        dataOutputStream.close();
    }
    public static int[] readArrayFromBinaryStream(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int[] array = new int[dataInputStream.available()/4];
        for (int i = 0; i < array.length; i++) {
            array[i] = dataInputStream.readInt();
        }
        dataInputStream.close();
        return array;
    }

    public static void writeArrayToStream(int[] array, Writer writer) {
        for (int num : array) {
            try {
                writer.write(num + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readArrayFromStream(int[] array, Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String[] strNums;
        try {
            String line = bufferedReader.readLine();
            strNums = line.split(" ");
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(strNums[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //task3
    public static int[] readArrayRandomAccessFile(RandomAccessFile randomAccessFile, long position, int length) throws IOException {
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
    public List<String> findFilesByRegex(String directoryPath, String regex) {
        List<String> result = new ArrayList<>();
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            return result;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    result.addAll(findFilesByRegex(file.getAbsolutePath(), regex));
                } else if (Pattern.matches(regex, file.getName())) {
                    result.add(file.getAbsolutePath());
                }
            }
        }

        return result;
    }

}