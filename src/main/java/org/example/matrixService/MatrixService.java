package org.example.matrixService;

import org.example.matrixTest.Matrix;
import org.example.matrixDeterminantComparator.MatrixDeterminantComparator;

import java.util.Arrays;

public class MatrixService {
    public static Matrix[] sortArrMatrices(Matrix[] matArr){
        Arrays.sort(matArr, new MatrixDeterminantComparator());
        return matArr;
    }

}
