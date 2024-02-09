package org.example.matrix_interface;

public interface IMatrix {
    double getElem(int i,int j);
    void setElem(int i,int j,double elem);
    double countDet();
}
