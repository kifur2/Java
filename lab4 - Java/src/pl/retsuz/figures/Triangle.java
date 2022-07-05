package pl.retsuz.figures;

import pl.retsuz.points.Point;

public class Triangle implements Figures{
    private Point tab[];
    private double surface;
    private double perimeter;

    public Triangle(){
        tab = new Point[3];
        tab[0]=new Point();
        tab[1]=new Point();
        tab[2]=new Point();
        surface=0;
        perimeter=0;
    }
    public Triangle(Point a, Point b, Point c){
        tab = new Point[3];
        tab[0]=a;
        tab[1]=b;
        tab[2]=c;
        surface=0;
        perimeter=0;
    }

    private void countSurface(){
        if(perimeter==0)
            countPerimeter();
        double cos = perimeter/2 * (perimeter/2 - tab[0].distance(tab[1]))* (perimeter/2 - tab[1].distance(tab[2]))
                * (perimeter/2 - tab[2].distance(tab[0]));
        surface = Math.sqrt(cos);
    }

    private void countPerimeter(){
        perimeter = tab[0].distance(tab[1])+tab[1].distance(tab[2])+tab[2].distance(tab[0]);
    }

    @Override
    public Point[] getTab(){
        return tab;
    }
    @Override
    public double getSurface(){
        countSurface();
        return surface;
    }

    @Override
    public double getPerimeter(){
        countPerimeter();
        return perimeter;
    }

    @Override
    public void changePoint(int i, double x, double y){
        tab[i].setX(x);
        tab[i].setY(y);
    }

    public double getHeight(int n){
        if(surface==0)
            countSurface();
        return 2 * surface/(tab[(n+1)%3].distance(tab[(n+2)%3]));
    }
    public String toString(){
        String result = "";
        result+= "P0" + tab[0].toString()+ " P1"+ tab[1].toString() +" P2"+tab[2].toString();
        return result;
    }

}
