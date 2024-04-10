package Library;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.*;

public class ChangeOfBasis extends JFrame{

        public static void main(String[] args) {
            JFrame frame = new ChangeOfBasis();
            frame.setSize(700, 700);
            frame.setTitle("Change of basis");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        }

        public ChangeOfBasis() {
            add(new DrawPanel());
        }

        class DrawPanel extends JPanel {
            S2 S=new S2(50,50, 120,300);
            V2 P = new V2(5,3);

            S2 Sm = new S2(50,50,120,300);

            DrawPanel() {

            }
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                S.drawAxis(g);
                S.drawPoint(g,P);
            }
        } // class DrawPanel
    } // GraphicsApp

