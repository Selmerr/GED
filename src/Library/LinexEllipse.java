package Library;

import javax.swing.*;
import java.awt.*;

public class LinexEllipse extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new LinexEllipse();
        frame.setSize(700, 700);
        frame.setTitle("LinexEllipse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public LinexEllipse() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        S2 S=new S2(50,50, 120,300);
        V2 p0=new V2(4,3);          // Centrum af ellipse (x0,y0)
        double a=3;                 // Store akse
        double b=2;                 // Lille akse
        V2 q0=new V2(2.5, 0);       // Fast punkt på linien (x0,y0)
        double m=2.0/3;             // m er hældningskoefficient

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            S.drawAxis(g);
            for (double v=0; v<2*Math.PI; v+=0.01){         // vinkel er parameter
                V2 p=p0.add(new V2(a*Math.cos(v), b*Math.sin(v))); // punkter på ellipse
                S.drawPoint(g, p);
            }
            for (double k=0; k<5; k+=0.01){        // k er parameter i parameterfremstillingen
                V2 q=q0.add(new V2(1,m).mul(k));   // nye punkter på linien
                S.drawPoint(g, q);
            }
        }
    } // class DrawPanel

} // GraphicsApp