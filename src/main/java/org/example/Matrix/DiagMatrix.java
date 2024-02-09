package org.example.Matrix;

import java.util.Arrays;

public class DiagMatrix extends Matrix{

    public DiagMatrix(int size) {
        super(size);
    }

    public DiagMatrix(int size, double[] diagonalElements) {
        super(diagonalElements.length);
        for (int i = 0; i < size; i++) {
            setElem(i, i, diagonalElements[i]);
        }
    }
    @Override
    public void setElem(int i, int j, double elem) {
        if (i != j) {
            throw new IllegalArgumentException("Нельзя изменить элемент вне диагонали");
        }
        super.setElem(i, j, elem);
    }
}
