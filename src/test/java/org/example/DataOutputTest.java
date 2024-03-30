package org.example;

import org.example.dataOutputUtils.DataOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
    //task1
    @Test
    void writeArrayToBinaryStream() throws IOException {
        int[] arr = {10, 9, 8, 7, 6};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutput.writeArrayToBinaryStream(arr, outputStream);
        assertArrayEquals(new byte[]{0, 0, 0, 10, 0, 0, 0, 9, 0, 0, 0, 8, 0, 0, 0, 7, 0, 0, 0, 6}, outputStream.toByteArray());


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
            byteArray = outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray)) {
            int[] resultArray = DataOutput.readArrayFromBinaryStream(inputStream);
            assertArrayEquals(expectedArray, resultArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //task2
    @Test
    public void testWriteArrayToStream() {
        StringWriter stringWriter = new StringWriter();
        int[] array = {1, 2, 3, 4, 5};

        DataOutput.writeArrayToStream(array, stringWriter);

        String result = stringWriter.toString();
        assertEquals("1 2 3 4 5 ", result);
    }

    @Test
    public void testReadArrayFromStream() {
        int[] array = new int[5];
        Reader reader = new StringReader("1 2 3 4 5");
        DataOutput.readArrayFromStream(array, reader);

        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    //task3
    @Test
    void readArrayRandomAccessFile() throws IOException {
        RandomAccessFile testRandomAccessFile = null;
        try {
            testRandomAccessFile = new RandomAccessFile("testfile.txt", "rw");
            testRandomAccessFile.writeInt(10);
            testRandomAccessFile.writeInt(20);
            testRandomAccessFile.writeInt(30);
            testRandomAccessFile.writeInt(30);
            testRandomAccessFile.writeInt(30);
            int[] resultArray = DataOutput.readArrayRandomAccessFile(testRandomAccessFile, 3, 5);
            int[] expectedArray = {30, 30, 30};
            assertArrayEquals(expectedArray, resultArray);
            testRandomAccessFile.close();
        } catch (IOException e) {
            e.getStackTrace();
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
        List<File> result = DataOutput.getFilesWithExtension(mockedDirectory, ".txt");
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
    //task5


    @Test
    public void testFilterByNameRegex2() throws IOException {
        File f3 = new File("D:\\Coding\\lab7\\testDir");
        List<File> filteredFiles = DataOutput.filterByNameRegex(f3, ".*\\.txt");
        File expected1 = new File("D:\\Coding\\lab7\\testDir\\accept1.txt");
        File expected2 = new File("D:\\Coding\\lab7\\testDir\\testDir2\\accept2.txt");
        List<File> actual = new ArrayList<File>();
        actual.add(expected1);
        actual.add(expected2);
        assertEquals(filteredFiles,actual);
    }

    @Test
    public void testFilterByNameRegex3() throws IOException {
        File f1 = new File("D:\\Coding\\lab7\\src\\main\\java\\org\\example\\houseFlatPerson");
        List<File> filteredFiles = DataOutput.filterByNameRegex(f1, ".*\\.txt");
        assertEquals(filteredFiles,new ArrayList<File>());
    }

}

