package pl.retsuz.figures;

import pl.retsuz.points.Point;

public class Quadrangle implements Figures{
    private Point tab[];
    private double surface;
    private double perimeter;

    public Quadrangle(){
        tab = new Point[4];
        tab[0]=new Point();
        tab[1]=new Point();
        tab[2]=new Point();
        tab[3]=new Point();
        surface=0;
        perimeter=0;
    }
    public Quadrangle(Point a, Point b, Point c, Point d){
        tab = new Point[4];
        tab[0]=a;
        tab[1]=b;
        tab[2]=c;
        tab[3]=d;
        surface=0;
        perimeter=0;
    }

    private void countSurface(){
        double perimeter1=tab[0].distance(tab[1])+tab[1].distance(tab[2])+tab[2].distance(tab[0]);
        double perimeter2=tab[0].distance(tab[2])+tab[2].distance(tab[3])+tab[3].distance(tab[0]);

        surface = Math.sqrt(perimeter1/2 * (perimeter1/2 - tab[0].distance(tab[1]))* (perimeter1/2 - tab[1].distance(tab[2]))
                * (perimeter1/2 - tab[2].distance(tab[0])))+Math.sqrt(perimeter2/2 * (perimeter2/2 - tab[2].distance(tab[3]))
                * (perimeter2/2 - tab[3].distance(tab[0])) * (perimeter2/2 - tab[0].distance(tab[2])));
    }

    private void countPerimeter(){
        perimeter = tab[0].distance(tab[1])+tab[1].distance(tab[2])+tab[2].distance(tab[3])+tab[3].distance(tab[0]);
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

    public double getDiameter(int i){
        return tab[(i)%4].distance(tab[(i+2)%4]);
    }

    public String toString(){
        String result = "";
        result+= "P0" + tab[0].toString()+ " P1"+ tab[1].toString() +" P2"+tab[2].toString()+" P3"+tab[3].toString();
        return result;
    }
}
