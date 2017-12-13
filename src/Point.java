//import java.math.*;
public class Point {
    private int x,y;
    public int setX(int _x) {
        return x=_x;
    }
    public int setY(int _y) {
        return y =_y;
    }
    public int getX() {
        return x ;
    }
    public int getY() {
        return y ;
    }

    public Point(int X,int Y) {
        x=X;
        y=Y;
    }

    static public double getDistance(Point pointA, Point pointB) {
        return Math.sqrt(
                Math.pow(pointA.getX() -pointB.getX() ,2) + Math.pow(pointA.getY() -pointB.getY() ,2)  );
    }
}
