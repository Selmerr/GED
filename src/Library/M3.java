package Library;

public class M3 {

    double a11, a12, a13;
    double a21, a22, a23;
    double a31, a32, a33;

    public M3(double a11, double a12, double a13, double a21, double a22, double a23, double a31, double a32, double a33) {
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a21 = a21;
        this.a22 = a22;
        this.a23 = a23;
        this.a31 = a31;
        this.a32 = a32;
        this.a33 = a33;
    }

    public M3(V3 r1, V3 r2, V3 r3) {
        this.a11 = r1.x;
        this.a12 = r1.y;
        this.a13 = r1.z;
        this.a21 = r2.x;
        this.a22 = r2.y;
        this.a23 = r2.z;
        this.a31 = r3.x;
        this.a32 = r3.y;
        this.a33 = r3.z;
    }

    M3 add(M3 m) {
        return new M3(this.a11+ m.a11,this.a12+ m.a12,this.a13+ m.a13,
                this.a21+ m.a21,this.a22+ m.a22,this.a23+ m.a23,
                this.a31+ m.a31,this.a32+ m.a32,this.a33+ m.a33);
    }

    M3 sub(M3 m) {
        return new M3(this.a11-m.a11,this.a12- m.a12,this.a13- m.a13,
                this.a21- m.a21,this.a22- m.a22,this.a23- m.a23,
                this.a31- m.a31,this.a32- m.a32,this.a33- m.a33);
    }

    M3 mul(double k) {
        return new M3(this.a11*k,this.a12*k,this.a13*k,
                this.a21*k,this.a22*k,this.a23*k,
                this.a31*k,this.a32*k,this.a33*k);
    }

    V3 mul(V3 v) {
        return new V3(a11* v.x+a12* v.y+a13*v.z,
                        a21* v.x+a22* v.y+a23* v.z,
                        a31* v.x+a32* v.y+a33* v.z);
    }

    M3 mul(M3 m) {
        return new M3(a11*m.a11+a12*m.a21+a13*m.a31, a11* m.a12+a12* m.a22+a13* m.a32, a11* m.a13+a12* m.a23+a13* m.a33,
                a21* m.a11+a22*m.a21+a23* m.a31, a21* m.a12+a22* m.a22+a23* m.a32, a21* m.a13+a22* m.a23+a23* m.a33,
                a31* m.a11+a32* m.a21+a33* m.a31, a31* m.a12+a32* m.a22+ a33* m.a32, a31* m.a13+a32* m.a23+a33* m.a33);
    }
    @Override
    public String toString() {
        return "M3{" +
                "\na11=" + a11 +
                ", a12=" + a12 +
                ", a13=" + a13 +
                "\na21=" + a21 +
                ", a22=" + a22 +
                ", a23=" + a23 +
                "\na31=" + a31 +
                ", a32=" + a32 +
                ", a33=" + a33 +
                '}';
    }

    public static void main(String[] args) {
        V3 v1 = new V3(1,2,3);
        V3 v2 = new V3(4,5,6);
        V3 v3 = new V3(7,8,9);

        M3 m = new M3(v1,v2,v3);
        M3 m2 = new M3(9,8,7,
                        6,5,4,
                        3,2,1);



        System.out.println(m.a11 + "  " + m.a12 + "  " + m.a13);
        System.out.println(m.a21 + "  " + m.a22 + "  " + m.a23);
        System.out.println(m.a31 + "  " + m.a32 + "  " + m.a33);
        System.out.println(m.toString());
        System.out.println("M1 + M2: "+m.add(m2).toString());
        System.out.println("M1 - M2: "+m.sub(m2).toString());
        System.out.println("M1*V1: "+m.mul(v1).toString());
        System.out.println("M1*M2: "+m.mul(m2).toString());
        System.out.println("M2*M1: "+m2.mul(m).toString());


    }
}
