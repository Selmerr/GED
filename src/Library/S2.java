package Library;

import java.awt.*;

public class S2 {

    V2 origo = new V2(0,0);

    V2 O;

    M2 S;

    M2 F;

    M2 T;

    S2(int Sx, int Sy, int Ox, int Oy) {
        O = new V2(Ox,Oy);
        S = new M2(Sx,0,0,Sy);
        F = new M2(1,0,0,-1);
        T = F.matrixMulti(S);
    }

    V2 transform(V2 v) {
        return T.vectorMulti(v).add(O);
    }

    void drawLine(Graphics g, V2 p1, V2 p2) {

        V2 pp1 = transform(p1);
        V2 pp2 = transform(p2);
        g.drawLine((int) pp1.x, (int) pp1.y, (int) pp2.x, (int) pp2.y);

    }

    void drawAxis(Graphics g) {
        drawLine(g, origo, new V2(1,0));
        drawLine(g, origo, new V2(0,1));
    }

    void drawPoint(Graphics g, V2 p) {
        V2 pp = transform(p);
        g.fillOval((int) pp.x-1, (int) pp.y-1, 2,2);
    }
}
