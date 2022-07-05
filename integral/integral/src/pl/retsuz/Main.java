package pl.retsuz;

import functions.Function;
import functions.examples.builder.ExampleBuilder;
import functions.examples.cosineexample.CosineExampleBuilder;
import integrals.IntegralAlgorithm;
import integrals.MonteCarlo;
import integrals.Trapez;
import pl.retsuz.view.IntegralConsoleView;
import pl.retsuz.view.IntegralView;

public class Main {


    protected static IntegralView view;
    protected static IntegralAlgorithm algorithm;
    protected static IntegralAlgorithm algorithm1;
    public static void main(String[] args) {

        algorithm = new MonteCarlo();
        algorithm1 = new Trapez();
        view = new IntegralConsoleView();

        view.Init(algorithm, algorithm1);
        view.View();

        /*double a = 0;
        double b = Math.PI/2;
        int n= 9999999;


        algorithm.calculateIntegral();
        algorithm1.setFunction(givenExample);
        algorithm1.setA(a);
        algorithm1.setB(b);
        algorithm1.setN(n);
        algorithm1.calculateIntegral();

        double monteIntegral = algorithm.getIntegral();
        double trapezIntegral = algorithm1.getIntegral();
        double exactIntegral = givenExample.getExactIntegralValue(b)-givenExample.getExactIntegralValue(a);
        double monteError = Math.abs(monteIntegral-exactIntegral);
        double trapezError = Math.abs(trapezIntegral-exactIntegral);

        System.out.println("Numeryczna monte\t"+monteIntegral);
        System.out.println("Numeryczna trapez\t"+trapezIntegral);
        System.out.println("Dokładna\t"+exactIntegral);
        System.out.println("Błąd monte\t\t"+monteError);
        System.out.println("Błąd trapez\t\t"+trapezError);*/
    }
}
