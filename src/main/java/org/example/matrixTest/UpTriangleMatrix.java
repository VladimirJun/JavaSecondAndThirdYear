package org.example.matrixTest;

public class UpTriangleMatrix extends Matrix {
    public UpTriangleMatrix(int size) {
        super(size);
    }

    @Override
    public void setElem(int i, int j, double elem) {
        if (i > j) {
            throw new IllegalArgumentException("Элемент должен быть выше главной диагонали");
        }
        super.setElem(i, j, elem);
    }
}
