package Library;

public class V3 {

    double x,y,z;

    public V3(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    V3 add(V3 v) {
        return new V3(x+v.x, y+v.y, z+ v.z);
    }

    V3 sub(V3 v) {
        return new V3(x-v.x, y-v.y, z-v.z);
    }

    V3 mul(double k) {
        return new V3(x*k,y*k,z*k);
    }

    double dot(V3 v) {
        return x* v.x+y* v.y+z*v.z;
    }

    V3 cross(V3 v) {
        return new V3(y*v.z - z*v.y,
                        z*v.x - x*v.z,
                        x*v.y - y*v.x);
    }

    double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public V3 unit(){
        double l=length();
        return new V3(x/l, y/l, z/l);
    }

    @Override
    public String toString() {
        return "V3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public static void main(String[] args) {
        V3 v1 = new V3(1,2,3);
        V3 v2 = new V3(3,2,-1);

        System.out.println("V1 + V2: " + v1.add(v2).toString());
        System.out.println("V1 - V2: " + v1.sub(v2).toString());
        System.out.println("V1 dot V2: " + v1.dot(v2));
        System.out.println("V1 X V2: " + v1.cross(v2).toString());
        System.out.println("Length of V1: " + v1.length());
        System.out.println("Unit vector from V1: " + v1.unit().toString());

    }
}
