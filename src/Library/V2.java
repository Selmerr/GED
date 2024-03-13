package Library;

import java.lang.Math;

public class V2 {

    double x;
    double y;

    public V2(double x, double y) {
        this.x = x;
        this.y = y;
    }


    V2 add(V2 v) {
        return new V2(x + v.x, y + v.y);
    }

    double skalarprodukt(V2 v) {
        return (x*v.x+y*v.y);
    }

    V2 mul(double s) {
        return new V2(x*s, y*s);
    }

    V2 sub(V2 v) {
        return new V2(x-v.x, y-v.y);
    }

    V2 crossVector(V2 v) {
        return new V2(-(v.y), v.x);
    }

    double lengthV() {
        return Math.sqrt((x*x)+(y*y));
    }

    double archV() {
        return (Math.acos(x));
    }


// cos(30 grader) = sin(60 grader), cos(60) = sin(30)



    @Override
    public String toString() {
        return "("+x+", "+ y+")";
    }

    public static void main(String[] args) {
        V2 v1 = new V2(1,2);
        V2 v2 = new V2(3,5);
        V2 v3 = v1.add(v2);
        System.out.println(v1.toString());
        System.out.println(v2.toString());
        System.out.println(v3.toString());
        System.out.println(v2.lengthV());
    }
}
