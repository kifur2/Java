package integrals;

public class Trapez extends IntegralAlgorithm{

    public Trapez(){
        this.sum=0;
    }
    @Override
    public void calculateIntegral() {
        double krok = (b - a)/(double)n;
        double x = a;
        for(int i=0; i<n; i++)
        {
            sum+=(function.getValue(x)+ function.getValue(x+krok))/2;
            x=x+krok;
        }
        sum*=krok;
    }
}
