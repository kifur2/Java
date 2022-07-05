package pl.retsuz.solver.exceptions;

public class InfiniteRootsException extends SolverException{
    public InfiniteRootsException(){
        super("Rownanie ma nieskonczenie wiele rozwiazan!");
    }
}