package org.example;

import org.example.dataOutputUtils.DataOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.util.List;

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

        // Проверка результатов
        int[] expectedArray = {10, 20, 30};
        assertArrayEquals(expectedArray, resultArray);
        try {
            testRandomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFilesWithExtension1() {


        File file1 = mock(File.class);
        File file2 = mock(File.class);

        // Устанавливаем поведение для заглушек
        when(mockedDirectory.listFiles()).thenReturn(new File[]{file1, file2});
        when(file1.isFile()).thenReturn(true);
        when(file1.getName()).thenReturn("file1.txt");
        when(file2.isFile()).thenReturn(true);
        when(file2.getName()).thenReturn("file2.jpg");

        List<File> result = org.example.dataOutputUtils.DataOutput.getFilesWithExtension(mockedDirectory, ".txt");

        // Проверяем корректность результата
        assertEquals(1, result.size());
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
    public void testGetFilesMatchingPattern() {
        File directory = new File("testDir");
        File file1 = new File("testDir/testFile1.txt");
        File file2 = new File("testDir/testFile2.doc");
        File[] files = new File[]{file1, file2};
        when(directory.exists()).thenReturn(true);
        when(directory.isDirectory()).thenReturn(true);
        when(directory.listFiles()).thenReturn(files);

        List<File> result = DataOutput.getFilesMatchingPattern(directory.getPath(), ".*\\.txt");

        assertEquals(file1, result.get(0));
    }

}