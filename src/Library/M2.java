package Library;

public class M2 {

    double a, b;
    double c, d;

    public M2(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    M2 add(M2 m) {
        return new M2(this.a+ m.a, this.b+ m.b, this.c+ m.c, this.d+ m.d);
    }

    M2 sub(M2 m){    // matrix subtraction
        return new M2(this.a-m.a, this.b-m.b,
                this.c-m.c, this.d-m.d);
    }
    V2 vectorMulti(V2 v) {
        return new V2(a* v.x+b* v.y, c* v.x+d* v.y);
    }

    M2 matrixMulti(M2 m) {
        return new M2(a* m.a + b*m.c, a* m.b + b*m.d,
                      c* m.a + d*m.c, c* m.b + d*m.d);
    }

    M2 numberMulti(double x) {
        return new M2(a*x,b*x,c*x,d*x);
    }

    double determinant() {
        return a*d-b*c;
    }

    M2 adj(){           // Adjungatet Matrix
        return new M2( d,-b,
                -c, a);
    }

    M2 inv(){           // Inverse
        if (determinant()==0) throw new ArithmeticException("Matrix "+this+" has no inverse.");
        return adj().numberMulti(1.0/determinant());
    }

    @Override
    public String toString() {
        return "M2{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }

    public static void main(String[] args) {
        M2 m1 = new M2(2,4,5,3);
        M2 m2 = new M2(3,6,-1,9);
        M2 minv = m1.inv();
        M2 m3 = m1.matrixMulti(m2);
        System.out.println(m3.toString());
    }
}
