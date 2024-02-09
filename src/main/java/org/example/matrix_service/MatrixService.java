package org.example.matrix_service;

import org.example.matrix_determinant_comparator.MatrixDeterminantComparator;
import org.example.matrix.Matrix;

import java.util.Arrays;

public class MatrixService {
    public static Matrix[] sortArrMatrices(Matrix[] matArr) {
        Arrays.sort(matArr, new MatrixDeterminantComparator());
        return matArr;
    }

}
