package Library;

import javax.swing.*;
import java.awt.*;

public class GraphicsApp2 extends JFrame {

    public static void main(String[] args) {

        JFrame frame = new GraphicsApp2();
        frame.setSize(600, 600);
        frame.setTitle("GraphicsApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public GraphicsApp2() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        S2 S= new S2(50,50,120,300);

        int N = 5;
        V2[] points = new V2[N];
        DrawPanel() {
            double v = (2*Math.PI)/N;
            for (int i=0; i<N; i++) {
                double x = Math.cos(i*v+Math.PI/2)*2;
                double y = Math.sin(i*v+Math.PI/2)*2;
                points[i] = new V2(x,y);
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            S.drawAxis(g);

//            for (int i=0; i<N; i++) {
//                int j = i+N/2;
//                S.drawLine(g,points[i],points[j%N]);
//            }
//
//            for (double v=0; v<2*Math.PI; v+=0.001) {
//                double x = 2*Math.cos(v);
//                double y = 2*Math.sin(v);
//                S.drawPoint(g, new V2(x,y));
//            }
//
//
//            V2[] trianglePoints = new V2[3];
//            V2 a = S.origo;
//            V2 b = new V2(a.x+3.0, a.y);
//            V2 c = new V2(b.x, b.y+4);
//            trianglePoints[0] = a;
//            trianglePoints[1] = b;
//            trianglePoints[2] = c;
//
//            for (int i=0; i<trianglePoints.length;i++) {
////                S.drawLine(g,trianglePoints[i]);
//            }

        }


    }

}
