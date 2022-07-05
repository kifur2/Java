package pl.retsuz.points;

public class Point {
    private double x;
    private double y;
    public Point() {
        x=0; y=0;
    }
    public Point(double x, double y){
        this.x = x;
        this.y =y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }

    public double distance (Point A){
        return Math.sqrt((this.x-A.x)*(this.x-A.x)+(this.y-A.y)*(this.y-A.y));
    }
    public String toString(){
        String result = "";
        result+="("+x+','+y+")";
        return result;
    }
}
