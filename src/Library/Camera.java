package Library;

import java.awt.*;

public class Camera {

    V3 origo = new V3(0,0,0);

    V3 O = new V3(0,0,0);
    V3 i = new V3(1,0,0);
    V3 j = new V3(0,1,0);
    V3 k = new V3(0,0,1);
    V3 E = new V3(0,0,0);
    V3 D = new V3(1,0,0);
    V3 U = new V3(0,1,0);
    V3 R = new V3(0,0,1);

    double z = 2;

    S2 s2;

    public Camera(int sx, int sy, int ox, int oy) {
        s2= new S2(sx,sy,ox,oy);
    }

    V2 project(V3 p) {
        V3 EP = p.sub(E);
        double d = D.dot(EP);
        double u = U.dot(EP);
        double r = R.dot(EP);
        double rm = r*z/d;
        double um = u*z/d;
        return new V2(rm,um);
    }

    public void moveTo(V3 p) {
        E = new V3(p.x,p.y,p.z);
    }

    void focus(V3 p) {
        D = p.sub(E).unit();
        R = D.cross(k).unit();
        //Unit metode er ikke nødvendig her, da D og R allerede er blevet til enhedsvektorer.
        U = R.cross(D);
    }

    void drawAxis(Graphics g) {
        drawLine(g, O, i);
        drawLine(g, O, j);
        drawLine(g, O, k);
    }

    void drawPoint(Graphics g, V3 p) {
        s2.drawPoint(g, project(p));
    }

    void drawLine(Graphics g, V3 p1, V3 p2) {
        s2.drawLine(g,project(p1),project(p2));
    }

    void fillRect(Graphics g, V3 p1, V3 p2, Color color) {
        s2.fillRect(g,color,project(p1),project(p2));
    }

}
