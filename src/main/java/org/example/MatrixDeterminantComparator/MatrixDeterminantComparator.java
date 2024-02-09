package org.example.MatrixDeterminantComparator;

import org.example.Matrix.Matrix;

import java.util.Comparator;

public class MatrixDeterminantComparator implements Comparator<Matrix> {

    @Override
    public int compare(Matrix o1, Matrix o2) {
        return (int) (o1.countDet() - (o2.countDet()));
    }
}
