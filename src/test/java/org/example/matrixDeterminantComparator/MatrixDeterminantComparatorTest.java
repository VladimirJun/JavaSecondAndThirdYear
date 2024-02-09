package org.example.matrixDeterminantComparator;

import junit.framework.TestCase;
import org.example.matrixTest.DiagMatrix;

public class MatrixDeterminantComparatorTest extends TestCase {
    public void testComparatorFirst() {
        MatrixDeterminantComparator comparator = new MatrixDeterminantComparator();
        DiagMatrix diag1 = new DiagMatrix(3, new double[]{3, 4, 5});
        DiagMatrix diag2 = new DiagMatrix(2, new double[]{3, -4});
        DiagMatrix diag3 = new DiagMatrix(2, new double[]{-4, 3});
        DiagMatrix diag4 = new DiagMatrix(3, new double[]{-3, 4, 5});
        System.out.println("===TEST ONE EXECUTED===");
        assertEquals(0, comparator.compare(diag2, diag3));
    }
        public void testComparatorSecond() {
            MatrixDeterminantComparator comparator = new MatrixDeterminantComparator();
            DiagMatrix diag1 = new DiagMatrix(3, new double[]{3, 4, 5});
            DiagMatrix diag2 = new DiagMatrix(2, new double[]{3, -4});
            DiagMatrix diag3 = new DiagMatrix(2, new double[]{-4, 3});
            DiagMatrix diag4 = new DiagMatrix(3, new double[]{-3, 4, 5});
            System.out.println("===TEST TWO EXECUTED===");
            assertTrue(comparator.compare(diag1, diag3) > 0);
        }
        public void testComparatorThird() {
            MatrixDeterminantComparator comparator = new MatrixDeterminantComparator();
            DiagMatrix diag1 = new DiagMatrix(3, new double[]{3, 4, 5});
            DiagMatrix diag2 = new DiagMatrix(2, new double[]{3, -4});
            DiagMatrix diag3 = new DiagMatrix(2, new double[]{-4, 3});
            DiagMatrix diag4 = new DiagMatrix(3, new double[]{-3, 4, 5});
            System.out.println("===TEST THREE EXECUTED===");
            assertTrue(comparator.compare(diag4, diag1) < 0);
        }
    }
