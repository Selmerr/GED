package Library;

import Library.S2;
import Library.V2;

import javax.swing.*;
import java.awt.*;

public class Stjernen extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Stjernen();
        frame.setSize(700, 700);
        frame.setTitle("Stjernen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public Stjernen() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        S2 S=new S2(50,50, 120,300);
        int N=5;
        V2[] points=new V2[N];

        DrawPanel(){
            double v=2*Math.PI/N;
            for (int i=0; i<N; i++){
                double x=Math.cos(i*v+Math.PI/2);
                double y=Math.sin(i*v+Math.PI/2);
                points[i]=new V2(x,y);
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            S.drawAxis(g);
            for (int i=0; i<N; i++){
                int j=(i+N/2)%N;
                S.drawLine(g, points[i], points[j]);
            }
        }
    } // class DrawPanel

} // GraphicsApp
