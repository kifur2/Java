package pl.retsuz.solver.exceptions;

public class ContradictoryEquationException extends SolverException{
    public ContradictoryEquationException(){
        super("Rownanie sprzeczne!");
    }
}