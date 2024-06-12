package Library;

public class Cube {

    V3[] points = new V3[8];

    V3 C = new V3(0,0,0);

    Cube(V3[] points) {
        this.points = points;
        for (V3 p : points) {
            C=C.add(p);
        }
        C=C.mul((double) 1 /8);
    }
}
