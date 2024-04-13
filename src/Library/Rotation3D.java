package Library;

import javax.swing.*;
import java.awt.*;

public class Rotation3D extends JFrame{

    public static void main(String[] args) {
        JFrame frame = new Rotation2D();
        frame.setSize(700, 700);
        frame.setTitle("Stjernen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public Rotation3D() {
        add(new DrawPanel());
    }
    class DrawPanel extends JPanel {

        S2 S=new S2(50,50, 120,300);

        V3 t1 = new V3(2,3,4);
        V3 t2 = new V3(3,4,2);
        V3 t3 = new V3(4,2,3);


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }


}
