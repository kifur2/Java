package pl.retsuz;

import matrix.IMatrix;
import matrix.Matrix;
import matrix.generators.DefaultGenerator;

import java.util.Date;
import java.util.Scanner;

public class Main {
    static IMatrix a;
    static IMatrix b;
    static IMatrix c;
    static IMatrix d;


    protected static final Scanner sc = new Scanner(System.in);
    protected static int parseInteger(String message){
        System.out.printf("%s", message);
        String line;
        int res;
        try{
            line = sc.nextLine();
            res = Integer.parseInt(line);
        }catch (Exception ex){
            System.err.println("Wprowadzono niepoprawne dane!");
            res  = parseInteger(message);
        }
        return res;
    }

    public static void main(String[] args) {

        int n1,m1,n2,m2;
        do {
            n1 = parseInteger("Podaj liczbę wierszy pierwszej macierzy:");
            m1 = parseInteger("Podaj liczbę kolumn pierwszej macierzy:");
            n2 = parseInteger("Podaj liczbę wierszy drugiej macierzy:");
            m2 = parseInteger("Podaj liczbę kolumn drugiej macierzy:");
        }while(m1 != n2);
        System.out.println("Generuję macierze...");
        a= DefaultGenerator.generateRandomMatrix(n1,m1,0,3);
        b= DefaultGenerator.generateRandomMatrix(n2,m2,0,3);
        System.out.println("Mnożę macierze klasycznie...");
        Date start = new Date();
        c=IMatrix.multiply(a,b);
        Date end = new Date();
        System.out.println("Czas mnożenia w milisekundach: " + (end.getTime() - start.getTime()));
        //System.out.println(c.toString());
        /*
        Kod wykorzystujący klasę mnożenia współbieżnego
         */
        System.out.println("Mnożę macierze współbieżnie...");
        Date start1 = new Date();
        d=IMatrix.multiplyThreads(a,b);
        Date end1 = new Date();
        System.out.println("Czas mnożenia w milisekundach: " + (end1.getTime() - start1.getTime()));
        //System.out.println(d.toString());
    }
}
