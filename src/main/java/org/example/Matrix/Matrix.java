package org.example.Matrix;

import org.example.MatrixInterface.IMatrix;

import java.util.Arrays;
import java.util.Objects;

public class Matrix implements IMatrix {
    final private int size;
    private final double[] matrix;
    private double det;
    private boolean determinantValueCache;

    public Matrix(int size) {
        this.size = size;
        matrix = new double[size * size];
    }

    public int getSize() {
        return size;
    }

    @Override
    public double getElem(int i, int j) {
        return matrix[i * size + j];
    }

    @Override
    public void setElem(int i, int j, double elem) {
        matrix[i * size + j] = elem;
        determinantValueCache = false;
    }
    private static int calculateDeterminantGauss(double[][] matrix) {
        int size = matrix.length;
        int determinant = 1;

        for (int i = 0; i < size; i++) {
            // Если на главной диагонали встречается ноль, меняем строки местами
            if (matrix[i][i] == 0) {
                for (int j = i + 1; j < size; j++) {
                    if (matrix[j][i] != 0) {
                        swapRows(matrix, i, j);
                        determinant *= -1;
                        break;
                    }
                }
            }
            // Если после перестановки строк все равно ноль, определитель равен нулю
            if (matrix[i][i] == 0) {
                return 0;
            }

            // Приводим матрицу к ступенчатому виду
            for (int j = i + 1; j < size; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = 0; k < size; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }

        // Умножаем элементы главной диагонали, чтобы получить определитель
        for (int i = 0; i < size; i++) {
            determinant *= matrix[i][i];
        }

        return determinant;
    }

    private static void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }


    @Override
    public double countDet() {
        if (!determinantValueCache) {
            double[][] squareMatrix = new double[size][size];
            // Преобразуем одномерный массив в квадратную матрицу
            int index = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    squareMatrix[i][j] = matrix[i * size + j];
                }
            }
            det = calculateDeterminantGauss(squareMatrix);
            determinantValueCache = true;
        }
        return det;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return size == matrix1.size && Arrays.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        System.out.println("Matrix");
        int n = (int) Math.sqrt(matrix.length);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i * n + j] + "\t");
            }
            System.out.println();
        }
        return null;
    }
}
