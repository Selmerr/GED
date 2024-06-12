package Library;

import java.awt.*;

public class S2 {

    static final V2 origo = new V2(0,0);

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

    public void drawLine(Graphics g, V2 v1, V2 v2, Color c){
        Color oldColor = g.getColor();
        g.setColor(c);
        drawLine(g, v1, v2);
        g.setColor(oldColor);
    }

    public void drawLine(Graphics g, V2 v1, V2 v2, Color c, float weight){
        Stroke line1 = new BasicStroke(1.0f);
        Stroke line2 = new BasicStroke(weight);
        Graphics2D g2=(Graphics2D)g;
        g2.setStroke(line2);
        drawLine(g, v1, v2, c);
        g2.setStroke(line1);
    }

    void drawAxis(Graphics g) {
        drawLine(g, origo, new V2(1,0));
        drawLine(g, origo, new V2(0,1));
    }

    public void drawPoint(Graphics g, V2 v){
        V2 p=transform(v);                // p in pixels
        g.fillOval((int)p.x-1, (int)p.y-1, 2, 2);
    }
    public void drawPoint(Graphics g, V2 v, int size){
        V2 p=transform(v);                // p in pixels
        g.fillOval((int)p.x-size/2, (int)p.y-size/2, size, size);
    }
    public void drawPoint(Graphics g, V2 v, Color c){
        Color oldColor = g.getColor();
        g.setColor(c);
        drawPoint(g, v);
        g.setColor(oldColor);
    }
    public void drawPoint(Graphics g, V2 v, Color c, int size){
        Color oldColor = g.getColor();
        g.setColor(c);
        drawPoint(g, v, size);
        g.setColor(oldColor);
    }

    void drawString(Graphics g, V2 p, String text){
        V2 pp=transform(p);   // point in pixels
        g.drawString(text, (int)pp.x, (int)pp.y);
    }
    public void moveTo(V2 p){
        O=transform(p);
    }

    public void rotate(double phi){
        M2 R=new M2(Math.cos(phi), -Math.sin(phi),
                Math.sin(phi), Math.cos(phi));
        T=T.matrixMulti(R);
    }

    public void fillRect(Graphics g, Color color, V2 p1, V2 p2) {
        g.setColor(color);
        V2 pp1 = transform(p1);
        V2 pp2 = transform(p2);
        g.fillRect((int) pp1.x, (int) pp1.y, (int) (pp2.x-pp1.x), (int) (pp1.y-pp2.y));
        g.setColor(Color.black);
    }
}
