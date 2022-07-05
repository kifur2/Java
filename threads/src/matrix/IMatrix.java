package matrix;

import matrix.parallel.MyThreads;


public interface IMatrix {
    /**
     *
     * @return zwraca dane macierzy prostokątnej
     */
    double[][] getData();

    /**
     *
     * @param data dane macierzy prostokątnej
     */
    void setData(double[][] data);

    /**
     *
     * @return zwraca liczbę wierszy
     */
    int rowCount();

    /**
     *
     * @return zwraca liczbę kolumn
     */
    int columnCount();

    /**
     *
     * @return zwraca reprezentację tekstową
     */
    String toString();

    /**
     *
     * @param a macierz A
     * @param b macierz B
     * @return AxB
     */
    static IMatrix multiply(IMatrix a, IMatrix b){
        int resultRows=a.rowCount();
        int resultColumns=b.columnCount();
        IMatrix result = new Matrix(resultRows,resultColumns);
        int colCount = b.rowCount(),i=0,j=0,k=0;
            for(i=0;i<resultRows;i++){
                for(j=0;j<resultColumns;j++){
                    result.getData()[i][j]=0;
                    for(k=0;k<colCount;k++) {
                        result.getData()[i][j] += a.getData()[i][k] * b.getData()[k][j];
                    }
                }
            }

        return result;
    }
    static IMatrix multiplyThreads(IMatrix a, IMatrix b){
        Runnable [] tab = new Runnable[Runtime.getRuntime().availableProcessors()];
        Thread [] threads = new Thread[Runtime.getRuntime().availableProcessors()];
        for(int i=0; i<Runtime.getRuntime().availableProcessors(); i++){
            tab[i] = new MyThreads(i);
            threads[i] = new Thread(tab[i]);
        }
        MyThreads.setMatrix(a,b);
        for (int i=0; i<Runtime.getRuntime().availableProcessors(); i++)
            threads[i].start();
        for (int i=0; i<Runtime.getRuntime().availableProcessors(); i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return MyThreads.result;
    }
}
