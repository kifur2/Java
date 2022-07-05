package pl.retsuz;

import pl.retsuz.solver.QFormulaSolver;
import pl.retsuz.solver.QuadraticFormulaSolver;
import pl.retsuz.view.QuadraticEquationSolverConsoleView;
import pl.retsuz.view.QuadraticEquationSolverView;

public class Main{
    static QFormulaSolver solver;
    static QuadraticEquationSolverView view;
    public static void main(String[] args){
        solver = new QuadraticFormulaSolver();
        view = new QuadraticEquationSolverConsoleView();
        view.Init(solver);
        view.View();
    }
}
