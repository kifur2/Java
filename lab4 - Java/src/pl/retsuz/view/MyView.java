package pl.retsuz.view;

import pl.retsuz.figures.Quadrangle;
import pl.retsuz.figures.Triangle;
import pl.retsuz.points.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MyView implements IView{
    private Scanner sc;
    private double x,y;

    private ArrayList<Point> tabPoints;
    private ArrayList<Triangle> tabTriangle;
    private ArrayList<Quadrangle> tabQuadrangle;

    protected int parseInteger(String message){
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
    protected double parseDouble(String message){
        System.out.printf("%s",message);
        String line;
        double res;
        try{
            line = sc.nextLine();
            res = Double.parseDouble(line);
        }catch (Exception ex){
            System.err.println("Wprowadzono niepoprawne dane!");
            res  = parseDouble(message);
        }
        return res;
    }
    @Override
    public void View(){
        while(true){
            System.out.println("------------");
            System.out.println("----MENU----");
            System.out.println("------------");
            System.out.println("Dostępne opcje: ");
            System.out.println("1: stwórz punkt");
            System.out.println("2: stwórz trójkąt");
            System.out.println("3: stwórz czworokąt");
            System.out.println("4: modyfikuj punkt");
            System.out.println("5: modyfikuj trójkąt");
            System.out.println("6: modyfikuj czworokąt");
            System.out.println("7: wypisz wszystkie punkty");
            System.out.println("8: wypisz wszystkie trójkąty");
            System.out.println("9: wypisz wszystkie czworokąty");
            System.out.println("10: sortuj tablicę trójkątów");
            System.out.println("11: sortuj tablicę czworokątów");
            System.out.println("12: wyznacz odległość między dwoma punktami");
            System.out.println("13: oblicz pole trójkąta");
            System.out.println("14: oblicz pole czworokąta");
            System.out.println("15: oblicz obwód trójkąta");
            System.out.println("16: oblicz obwód czworokąta");
            System.out.println("17: oblicz wysokość trójkąta");
            System.out.println("18: oblicz przekątną czworokąta");
            System.out.println("19: zakończ");
            int option = parseInteger("Wybierz opcję: ");
            int tmp;
            int tmp1;
            switch (option)
            {
                case 1:
                    tabPoints.add(makePoint("Podaj współrzędne nowego punktu: "));
                    break;
                case 2:
                    System.out.println("Podaj wierzchołki trójkąta: ");
                    tabTriangle.add(new Triangle(makePoint("Podaj współrzędne punktu P0: "),
                            makePoint("Podaj współrzędne punktu P1: "),
                            makePoint("Podaj współrzędne punktu P2: ")));
                    break;
                case 3:
                    System.out.println("Podaj wierzchołki czworokąta: ");
                    tabQuadrangle.add(new Quadrangle(makePoint("Podaj współrzędne punktu P0: "),
                            makePoint("Podaj współrzędne punktu P1: "),
                            makePoint("Podaj współrzędne punktu P2: "),
                            makePoint("Podaj współrzędne punktu P3: ")));
                    break;
                case 4:
                    if(tabPoints.size()==0)
                    {
                        System.out.println("Nie ma żadnych punktów w tablicy");
                    }
                    else
                    {
                        printPoints();
                        while(true) {
                            tmp = parseInteger("Który z punktów chcesz zmodyfikować? (podaj numer): ");
                            if (tmp < tabPoints.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        setXY("Podaj nowe współrzędne tego punktu: ");
                        tabPoints.get(tmp).setX(x);
                        tabPoints.get(tmp).setY(y);
                        System.out.printf("Oto zmieniony punkt P%d: ", tmp);
                        System.out.println(tabPoints.get(tmp).toString());
                    }
                    break;
                case 5:
                    if(tabTriangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych trójkątów w tablicy");
                    }
                    else {
                        printTriangles();
                        while (true) {
                            tmp = parseInteger("Który z trójkątów chcesz zmodyfikować? (podaj numer): ");
                            if (tmp < tabTriangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("T%d %s\n", tmp, tabTriangle.get(tmp).toString());
                        while (true) {
                            tmp1 = parseInteger("Który z punktów chcesz zmodyfikować? (podaj numer): ");
                            if (tmp1 < tabTriangle.get(tmp).getTab().length)
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        setXY("Podaj nowe współrzędne tego punktu: ");
                        tabTriangle.get(tmp).changePoint(tmp1, x, y);
                        System.out.printf("Oto zmieniony trójkąt T%d: ", tmp);
                        System.out.println(tabTriangle.get(tmp).toString());
                    }
                    break;
                case 6:
                    if(tabQuadrangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych czworokątów w tablicy");
                    }
                    else {
                        printQuadrangles();
                        while (true) {
                            tmp = parseInteger("Który z czworokątów chcesz zmodyfikować? (podaj numer): ");
                            if (tmp < tabQuadrangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Q%d %s\n", tmp, tabQuadrangle.get(tmp).toString());
                        while (true) {
                            tmp1 = parseInteger("Który z punktów chcesz zmodyfikować? (podaj numer): ");
                            if (tmp1 < tabQuadrangle.get(tmp).getTab().length)
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }

                        setXY("Podaj nowe współrzędne tego punktu: ");
                        tabQuadrangle.get(tmp).changePoint(tmp1, x, y);
                        System.out.printf("Oto zmieniony czworokąt Q%d: ", tmp);
                        System.out.println(tabQuadrangle.get(tmp).toString());
                    }
                    break;
                case 7:
                    if(tabPoints.size()==0)
                    {
                        System.out.println("Nie ma żadnych punktów w tablicy");
                    }
                    else
                        printPoints();
                    break;
                case 8:
                    if(tabTriangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych trójkątów w tablicy");
                    }
                    else
                        printTriangles();
                    break;
                case 9:
                    if(tabQuadrangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych czworokątów w tablicy");
                    }
                    else
                        printQuadrangles();
                    break;
                case 10:
                    if(tabTriangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych trójkątów w tablicy");
                    }
                    else {
                        tabTriangle.sort(Collections.reverseOrder(Comparator.comparingDouble(Triangle::getSurface)));
                        System.out.println("Posortowana tablica: ");
                        printTriangles();
                    }
                    break;
                case 11:
                    if(tabQuadrangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych czworokątów w tablicy");
                    }
                    else {
                        tabQuadrangle.sort(Collections.reverseOrder(Comparator.comparingDouble(Quadrangle::getSurface)));
                        printQuadrangles();
                    }
                    break;
                case 12:
                    if(tabPoints.size()==0)
                    {
                        System.out.println("Nie ma żadnych punktów w tablicy");
                    }
                    else {
                        printPoints();
                        System.out.println("Wybierz dwa punkty: ");
                        while (true) {
                            tmp = parseInteger("Wybierz punkt (podaj numer): ");
                            if (tmp < tabPoints.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        while (true) {
                            tmp1 = parseInteger("Wybierz punkt (podaj numer): ");
                            if (tmp1 < tabPoints.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Odległość: %f\n", tabPoints.get(tmp).distance(tabPoints.get(tmp1)));
                    }
                    break;
                case 13:
                    if(tabTriangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych trójkątów w tablicy");
                    }
                    else {
                        printTriangles();
                        while (true) {
                            tmp = parseInteger("Wybierz trójkąt (podaj numer): ");
                            if (tmp < tabTriangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Pole: %f\n", tabTriangle.get(tmp).getSurface());
                    }
                    break;
                case 14:
                    if(tabQuadrangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych czworokątów w tablicy");
                    }
                    else {
                        printQuadrangles();
                        while (true) {
                            tmp = parseInteger("Wybierz czworokąt (podaj numer): ");
                            if (tmp < tabQuadrangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Pole: %f\n", tabQuadrangle.get(tmp).getSurface());
                    }
                    break;
                case 15:
                    if(tabTriangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych trójkątów w tablicy");
                    }
                    else {
                        printTriangles();
                        while (true) {
                            tmp = parseInteger("Wybierz trójkąt (podaj numer): ");
                            if (tmp < tabTriangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Obwód: %f\n", tabTriangle.get(tmp).getPerimeter());
                    }
                    break;
                case 16:
                    if(tabQuadrangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych czworokątów w tablicy");
                    }
                    else {
                        printQuadrangles();
                        while (true) {
                            tmp = parseInteger("Wybierz czworokąt (podaj numer): ");
                            if (tmp < tabQuadrangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Obwód: %f\n", tabQuadrangle.get(tmp).getPerimeter());
                    }
                    break;
                case 17:
                    if(tabTriangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych trójkątów w tablicy");
                    }
                    else
                    {
                        printTriangles();
                        while (true) {
                            tmp = parseInteger("Wybierz trójkąt (podaj numer): ");
                            if (tmp < tabTriangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("T%d %s\n", tmp, tabTriangle.get(tmp).toString());
                        while (true) {
                            tmp1 = parseInteger("Wybierz punkt z którego ma wychodzić wysokość (podaj numer): ");
                            if (tmp1 < tabTriangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Wysokość trójkąta z punktu P%d: %f\n", tmp1, tabTriangle.get(tmp).getHeight(tmp1));
                    }
                    break;
                case 18:
                    if(tabQuadrangle.size()==0)
                    {
                        System.out.println("Nie ma żadnych czworokątów w tablicy");
                    }
                    else {
                        printQuadrangles();
                        while (true) {
                            tmp = parseInteger("Wybierz czworokąt (podaj numer): ");
                            if (tmp < tabQuadrangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Q%d %s\n", tmp, tabQuadrangle.get(tmp).toString());
                        while (true) {
                            tmp1 = parseInteger("Wybierz punkt z którego ma wychodzić przekątna (podaj numer): ");
                            if (tmp1 < tabQuadrangle.size())
                                break;
                            else
                                System.out.println("Spróbuj ponownie");
                        }
                        System.out.printf("Przekątna czworokąta z punktu P%d: %f\n", tmp1, tabQuadrangle.get(tmp).getDiameter(tmp1));
                    }
                    break;
                case 19:
                    System.out.println("Do zobaczenia");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Błędny numer opcji - spróbuj ponownie");
            }
        }
    }
    @Override
    public void Init(){
        this.sc = new Scanner(System.in);
        tabPoints = new ArrayList<>();
        tabTriangle = new ArrayList<>();
        tabQuadrangle = new ArrayList<>();
    }

    private void setXY(String message) {
        System.out.println(message);
        x = parseDouble("x = ");
        y = parseDouble("y = ");
    }
    private Point makePoint(String message){
        setXY(message);
        return new Point(x, y);
    }

    private void printPoints(){
        for(int i=0; i < tabPoints.size(); i++){
            System.out.printf("P%d %s\n", i,tabPoints.get(i).toString());
        }
    }
    private void printTriangles(){
        for(int i=0; i < tabTriangle.size(); i++){
            System.out.printf("T%d %s\n", i, tabTriangle.get(i).toString());
        }
    }
    private void printQuadrangles(){
        for(int i=0; i < tabQuadrangle.size(); i++){
            System.out.printf("Q%d %s\n", i,tabQuadrangle.get(i).toString());
        }
    }
}
