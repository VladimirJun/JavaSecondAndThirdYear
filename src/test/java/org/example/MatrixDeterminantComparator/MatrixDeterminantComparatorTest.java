package org.example.MatrixDeterminantComparator;

import junit.framework.TestCase;
import org.example.Matrix.DiagMatrix;

public class MatrixDeterminantComparatorTest extends TestCase {
    public void testComparator() {
        MatrixDeterminantComparator comparator = new MatrixDeterminantComparator();
        DiagMatrix diag1 = new DiagMatrix(3,new double[]{3, 4,5});
        DiagMatrix diag2 = new DiagMatrix(2,new double[]{3, -4});
        DiagMatrix diag3 = new DiagMatrix(2,new double[]{-4, 3});
        DiagMatrix diag4 = new DiagMatrix(3,new double[]{-3, 4,5});
        System.out.println(comparator.compare(diag1,diag2));
        System.out.println("===TEST ONE EXECUTED===");
        assertEquals(0, comparator.compare(diag2, diag3));
        System.out.println("===TEST TWO EXECUTED===");
        assertTrue(comparator.compare(diag1, diag3) > 0);
        System.out.println("===TEST THREE EXECUTED===");
        assertTrue(comparator.compare(diag4, diag1) < 0);

    }




}