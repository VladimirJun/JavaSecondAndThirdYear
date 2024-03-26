package org.example;

import org.example.dataOutputUtils.DataOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.dataOutputUtils.DataOutput.getFilesMatchingPattern;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DataOutputTest {
    @Mock
    private File mockedDirectory;

    @BeforeEach
    public void setUp() {
        mockedDirectory = mock(File.class);
    }
//task1
    @Test
    void writeArrayToBinaryStream() throws IOException {
        int[] arr = {10, 9, 8, 7, 6};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        org.example.dataOutputUtils.DataOutput.writeArrayToBinaryStream(arr, outputStream);

        try (DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(outputStream.toByteArray()))) {
            int[] resultArray = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                resultArray[i] = dataInputStream.readInt();
            }
            assertArrayEquals(resultArray, arr);
        }
    }

    @Test
    void writeArrayToBinaryStream2() throws IOException {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        org.example.dataOutputUtils.DataOutput.writeArrayToBinaryStream(arr, outputStream);
        try (DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(outputStream.toByteArray()))) {
            int[] resultArray = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                resultArray[i] = dataInputStream.readInt();
            }
            assertArrayEquals(resultArray, arr);
        }
    }

    @Test
    void writeArrayToBinaryStream3() throws IOException {
        int[] arr = {};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        org.example.dataOutputUtils.DataOutput.writeArrayToBinaryStream(arr, outputStream);
        try (DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(outputStream.toByteArray()))) {
            int[] resultArray = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                resultArray[i] = dataInputStream.readInt();
            }
            assertArrayEquals(resultArray, arr);
        }
    }
//task2
    @Test
    void readArrayFromBinaryStreamTest() {
        int[] expectedArray = {1, 2, 3, 4, 5};
        byte[] byteArray;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            for (int value : expectedArray) {
                dataOutputStream.writeInt(value);
            }
            dataOutputStream.flush();
            byteArray = outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray)) {
            int[] resultArray = org.example.dataOutputUtils.DataOutput.readArrayFromBinaryStream(inputStream);
            assertArrayEquals(expectedArray, resultArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//task3
    @Test
    void readArrayRandomAccessFile() throws IOException {
        RandomAccessFile testRandomAccessFile = null;
        try {
            testRandomAccessFile = new RandomAccessFile("testfile.txt", "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            testRandomAccessFile.writeInt(10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            testRandomAccessFile.writeInt(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        testRandomAccessFile.writeInt(30);

        // Установка позиции и чтение массива
        int[] resultArray = org.example.dataOutputUtils.DataOutput.readArrayRandomAccessFile(testRandomAccessFile, 0, 3);
        int[] expectedArray = {10, 20, 30};
        assertArrayEquals(expectedArray, resultArray);
        try {
            testRandomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//task4(MOCK)
    @Test
    void getFilesWithExtension1() {
        File file1 = mock(File.class);
        File file2 = mock(File.class);
        File file3 = mock(File.class);
        File file4 = mock(File.class);
        when(mockedDirectory.listFiles()).thenReturn(new File[]{file1, file2, file3, file4});
        when(file1.isFile()).thenReturn(true);
        when(file1.getName()).thenReturn("file1.txt");
        when(file2.isFile()).thenReturn(true);
        when(file2.getName()).thenReturn("file2.jpg");
        when(file3.isFile()).thenReturn(true);
        when(file3.getName()).thenReturn("file3.jpg");
        when(file4.isFile()).thenReturn(true);
        when(file4.getName()).thenReturn("file4.txt");

        List<File> result = org.example.dataOutputUtils.DataOutput.getFilesWithExtension(mockedDirectory, ".txt");
        List<File> expected = new ArrayList<>();
        expected.add(file1);
        expected.add(file4);
        assertEquals(expected, result);
    }

    @Test
    void getFilesWithExtension2() {
        File file1 = mock(File.class);
        File file2 = mock(File.class);
        when(mockedDirectory.listFiles()).thenReturn(new File[]{file1, file2});
        when(file1.isFile()).thenReturn(true);
        when(file1.getName()).thenReturn("file1.txt");
        when(file2.isFile()).thenReturn(true);
        when(file2.getName()).thenReturn("file2.jpg");
        List<File> result = org.example.dataOutputUtils.DataOutput.getFilesWithExtension(mockedDirectory, ".txt");
        assertEquals("file1.txt", result.get(0).getName());
    }

    @Test
    void getFilesWithExtension3() {
        File file1 = mock(File.class);
        File file2 = mock(File.class);
        File file3 = mock(File.class);
        when(mockedDirectory.listFiles()).thenReturn(new File[]{file1, file2, file3});
        when(file1.isFile()).thenReturn(true);
        when(file1.getName()).thenReturn("file1.cpp");
        when(file2.isFile()).thenReturn(true);
        when(file2.getName()).thenReturn("file2.jpg");
        when(file2.isFile()).thenReturn(true);
        when(file2.getName()).thenReturn("file3.pptx");
        List<File> result = org.example.dataOutputUtils.DataOutput.getFilesWithExtension(mockedDirectory, ".txt");
        assertEquals(result, new ArrayList<File>());
    }
//task5(FIX ME)
    @Test
    public void testGetFilesMatchingPattern() {
        File file1 = mock(File.class);
        File file2 = mock(File.class);
        File directory = mock(File.class);

        // Устанавливаем поведение для заглушек
        Mockito.when(directory.listFiles()).thenReturn(new File[]{file1, file2});
        Mockito.when(file1.getName()).thenReturn("file1.txt");
        Mockito.when(file2.getName()).thenReturn("file2.jpg");
        Mockito.when(file1.isDirectory()).thenReturn(false);
        Mockito.when(file2.isDirectory()).thenReturn(false);

        String patternRegex = ".*\\.txt"; // Паттерн для поиска файлов с расширением .txt
        getFilesMatchingPattern(directory, patternRegex);
    }
    @Test
    public void testGetFilesMatchingPattern2() {
        String directoryPath = "/path/to/directory";
        String patternRegex = ". * \\.txt";
        List<String> expectedFiles = Arrays.asList("/path/to/directory/file1.txt", "/path/to/directory/file2.txt");
        System.out.println(DataOutput.getFilesMatchingPattern(new File(directoryPath),patternRegex));

    }
}
