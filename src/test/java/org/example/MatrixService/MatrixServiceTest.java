package org.example.MatrixService;

import junit.framework.TestCase;
import org.example.Matrix.DiagMatrix;
import org.example.Matrix.Matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatrixServiceTest extends TestCase {
    public void testSort(){
        DiagMatrix diag1 = new DiagMatrix(2,new double[]{4,3});   //det = 12
        DiagMatrix diag2 = new DiagMatrix(2,new double[]{3, -4}); //det  = -12
        DiagMatrix diag3 = new DiagMatrix(3,new double[]{3,4,5}); //det = 60
        Matrix[] mArr = {diag3,diag2,diag1};
        Matrix[] sortedArr = {diag2,diag1,diag3};
//        System.out.println(Arrays.toString(MatrixService.SortArrMatrices(mArr)));
        System.out.println("===TEST ONE EXECUTED===");
        assertArrayEquals(sortedArr,MatrixService.sortArrMatrices(mArr));
    }
}