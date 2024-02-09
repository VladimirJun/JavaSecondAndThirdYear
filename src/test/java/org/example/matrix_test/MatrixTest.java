package org.example.matrix_test;

import junit.framework.TestCase;
import org.example.matrix.DiagMatrix;
import org.example.matrix.Matrix;
import org.example.matrix.UpTriangleMatrix;
import org.junit.jupiter.api.Assertions;

public class MatrixTest extends TestCase {

    public void testSetElem() {
        double[] elems = {1, 2, 3, 4};
        Matrix m = new Matrix(2);
        System.out.println(m);
        m.setElem(0, 0, 1);
        m.setElem(0, 1, 2);
        m.setElem(1, 0, 3);
        m.setElem(1, 1, 4);
        System.out.println(m);
    }

    public void testCountDetTwoOnTwo() {
        System.out.println("===TEST ONE EXECUTED===");
        Matrix m = new Matrix(2);
        m.setElem(0, 0, 1);
        m.setElem(0, 1, 2);
        m.setElem(1, 0, 3);
        m.setElem(1, 1, 4);
        //Добавили в матрицу числа
        assertEquals(-2.0, m.countDet());
    }

    public void testCountDetThreeOnThree() {
        System.out.println("===TEST TWO EXECUTED===");
        double[] elems1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Matrix m1 = new Matrix(3);
        m1.setElem(0, 0, 1);
        m1.setElem(0, 1, 2);
        m1.setElem(0, 2, 3);
        m1.setElem(1, 0, 4);
        m1.setElem(1, 1, 5);
        m1.setElem(1, 2, 6);
        m1.setElem(2, 0, 7);
        m1.setElem(2, 1, 8);
        m1.setElem(2, 2, 10);
        m1.toString();
        assertEquals(-3.0, m1.countDet());
    }

    public void testCountDetBigNumbers() {
        System.out.println("===TEST THREE EXECUTED===");
        Matrix m2 = new Matrix(3);
        m2.setElem(0, 0, -2);
        m2.setElem(0, 1, 4);
        m2.setElem(0, 2, -6);
        m2.setElem(1, 0, 1);
        m2.setElem(1, 1, -5);
        m2.setElem(1, 2, 2);
        m2.setElem(2, 0, -4);
        m2.setElem(2, 1, 12);
        m2.setElem(2, 2, 11);
        assertEquals(130.0, m2.countDet());
    }


    public void testDiagMatrixFirst() {
        System.out.println("===TEST FOR DIAG ONE EXECUTED===");
        DiagMatrix diagMatrix = new DiagMatrix(3, new double[]{3, 4, 5});
        diagMatrix.toString();
    }

    public void testDiagMatrixSecond() {
        System.out.println("===TEST FOR DIAG TWO EXECUTED===");
        DiagMatrix diagMatrix2 = new DiagMatrix(5, new double[]{1, 3, 4, 5, -8});
        diagMatrix2.toString();
        assertEquals(-480.0, diagMatrix2.countDet());
    }

    public void testDiagMatrixException() {
        System.out.println("===TEST FOR DIAG EXCEPTION EXECUTED===");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DiagMatrix diagMatrix1 = new DiagMatrix(2, new double[]{3, 4});
            diagMatrix1.setElem(1, 2, -4);
        });
    }

    public void testUpTriangleMatrix() {
        System.out.println("===TEST FOR UP TRIANGLE EXECUTED===");
        UpTriangleMatrix up = new UpTriangleMatrix(3);
        up.setElem(0, 0, 1);
        up.setElem(0, 1, 2);
        up.setElem(0, 2, 3);
        up.setElem(1, 1, 5);
        up.setElem(1, 2, 6);
        up.setElem(2, 2, 9);
        up.toString();
    }

    public void testUpTriangleMatrixException() {
        UpTriangleMatrix up1 = new UpTriangleMatrix(3);
        up1.setElem(0, 0, 1);
        up1.setElem(0, 1, 2);
        up1.setElem(0, 2, 3);
        up1.setElem(1, 1, 5);
        up1.setElem(1, 2, 6);
        up1.setElem(2, 2, 9);
        up1.toString();
        System.out.println("===TEST FOR TRIANGLE EXCEPTION EXECUTED===");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DiagMatrix diagMatrix1 = new DiagMatrix(2, new double[]{3, 4});
            up1.setElem(2, 1, -4);
        });
    }

}
