package org.example.MatrixService;

import org.example.Matrix.Matrix;
import org.example.MatrixDeterminantComparator.MatrixDeterminantComparator;

import java.util.Arrays;

public class MatrixService {
    public static Matrix[] sortArrMatrices(Matrix[] matArr){
        Arrays.sort(matArr, new MatrixDeterminantComparator());
        return matArr;
    }

}
