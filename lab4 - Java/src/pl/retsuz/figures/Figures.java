package pl.retsuz.figures;

import pl.retsuz.points.Point;
import pl.retsuz.view.IView;
import pl.retsuz.view.MyView;

public interface Figures {
    public Point[] getTab();
    public double getSurface();
    public double getPerimeter();
    public void changePoint(int i, double x, double y);
    public String toString();
    public static void main(String[] args) {
        IView view = new MyView();
        view.Init();
        view.View();
    }


}
