package org.example.MatrixInterface;

public interface IMatrix {
    double getElem(int i,int j);
    void setElem(int i,int j,double elem);
    double countDet();
}
