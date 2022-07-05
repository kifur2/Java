package matrix.parallel;

import matrix.IMatrix;
import matrix.Matrix;

public class MyThreads implements Runnable{
    protected int threadId;
    static IMatrix a;
    static IMatrix b;
    public static IMatrix result;
    public MyThreads(int id){
        this.threadId=id;
    }
    public static void setMatrix( IMatrix a, IMatrix b){
        MyThreads.a = a;
        MyThreads.b = b;
        result =  new Matrix(a.rowCount(),b.columnCount());
    }
    @Override
    public void run(){
        int i,j,k;
        for(i=threadId;i<result.rowCount();i+=Runtime.getRuntime().availableProcessors()){
            for(j=0;j<result.columnCount();j++){
                result.getData()[i][j]=0;
                for(k=0;k< a.columnCount();k++)
                    result.getData()[i][j]+=a.getData()[i][k]*b.getData()[k][j];
            }
        }
    }
}
