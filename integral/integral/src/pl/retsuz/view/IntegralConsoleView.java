package pl.retsuz.view;

import functions.Function;
import functions.examples.builder.ExampleBuilder;
import functions.examples.cosineexample.CosineExampleBuilder;
import integrals.IntegralAlgorithm;
import java.util.Scanner;

/**
 * Komunkacja człowieka z komputerem odbywa się za pomocą klasy QuadraticEquationSolverConsoleView. Klasa ta implementuje interfejs
 */
public class IntegralConsoleView implements IntegralView {
    private IntegralAlgorithm solver;
    private IntegralAlgorithm solver1;
    private Scanner sc;
    private double exactIntegral;

    protected double parseWithMessage(String message){
        System.out.println(message);
        String line;
        double res;
        try {
            line = sc.nextLine();
            res=Double.parseDouble(line);

        }catch (Exception ex){
            System.err.println("Wprowadzono niepoprawne dane!");
            res= parseWithMessage(message);
        }
        return res;
    }

    protected void parseFactors(){
        ExampleBuilder functionBuilder = new CosineExampleBuilder();
        Function givenExample = functionBuilder.build();
        double a,b;
        int n;
        a = parseWithMessage("Wprowadź współczynnik a:" );
        b = parseWithMessage("Wprowadź współczynnik b: ");
        n = (int) parseWithMessage("Wprowadź współczynnik n: ");
        this.solver.setFunction(givenExample);
        this.solver.setA(a);
        this.solver.setB(b);
        this.solver.setN(n);
        this.solver1.setFunction(givenExample);
        this.solver1.setA(a);
        this.solver1.setB(b);
        this.solver1.setN(n);
        exactIntegral = givenExample.getExactIntegralValue(b)-givenExample.getExactIntegralValue(a);
    }

    protected void displaySolutions(){
        System.out.println("Numeryczna MonteCarlo\t"+solver.getIntegral());
        System.out.println("Numeryczna Trapezy\t"+solver1.getIntegral());
        System.out.println("Dokładna\t"+exactIntegral);
        System.out.println("Błąd MonteCarlo\t\t"+Math.abs(solver.getIntegral()-exactIntegral));
        System.out.println("Błąd Trapezy\t\t"+Math.abs(solver1.getIntegral()-exactIntegral));
    }

    protected void getSolution(){
        try{
            solver.calculateIntegral();
            solver1.calculateIntegral();
            displaySolutions();
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public void View() {
        while(true) {
            parseFactors();
            getSolution();
        }
    }

    @Override
    public void Init(IntegralAlgorithm solver,IntegralAlgorithm solver1) {
        this.solver=solver;
        this.solver1 = solver1;
        this.sc = new Scanner(System.in);
    }
}

