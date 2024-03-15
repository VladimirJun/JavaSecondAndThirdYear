package org.example;

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

    //task2
    public static int[] readArrayFromBinaryStream(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int[] array = new int[dataInputStream.available() / 4];
        for (int i = 0; i < array.length; i++) {
            array[i] = dataInputStream.readInt();
        }
        dataInputStream.close();
        return array;
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
    public static List<File> getFilesMatchingPattern(String directoryPath, String patternRegex) {
        List<File> result = new ArrayList<>(); // Создаем и инициализируем список здесь

        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            Pattern pattern = Pattern.compile(patternRegex);

            if (files != null) {
                for (File file : files) {
                    if (file != null && file.isFile() && pattern.matcher(file.getName()).matches()) {
                        result.add(file); // Добавляем файл в список, если он соответствует паттерну
                    }
                }
            }
        }
        return result;
    }
}