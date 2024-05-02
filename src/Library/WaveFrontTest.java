package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class WaveFrontTest extends JFrame {
    public static void main(String[] args) {
        WaveFrontTest frame = new WaveFrontTest();
        frame.setTitle("WaveFrontTest");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } // main()

    WaveFrontTest() {
        add(new PaintPanel());
    }  // Constructor

    class PaintPanel extends JPanel {
        Camera camera=new Camera(200,200,500,300);

        WaveFrontElement wf=new WaveFrontElement("src/WaveFrontFiles/utha_teapot.obj");
//        WaveFrontElement wf=new WaveFrontElement("src/WaveFrontFiles/Warsong Attack Helicopter.obj");
//        WaveFrontElement wf=new WaveFrontElement("src/WaveFrontFiles/cube.obj");
//        WaveFrontElement wf=new WaveFrontElement("src/WaveFrontFiles/ball.obj");

        PaintPanel() {
            camera.moveTo(new V3(10,5,2));
            camera.focus(wf.center());
            camera.z=6;
            addMouseMotionListener(new MyMouseMotionListener());
        }

        class MyMouseMotionListener extends MouseMotionAdapter {
            int oldX=0;
            int oldY=0;
            public void mouseDragged(MouseEvent e) {
                int x=e.getX();                                 // new mouse position in pixels
                int y=e.getY();                                 // new mouse position in pixels
                int dx=x-oldX;                                  // displacement
                int dy=-(y-oldY);                               // Add a minus to flip the y-coordinate
                if (dx==0&&dy==0) return;                       // Don't multiply with (0,0). It will make the rotation axis a zero-vector
                V3 v=camera.R.mul(dx).add(camera.U.mul(dy));    // Rotation direction seen from camera view
                V3 u=v.cross(camera.D);                         // The rotation axis
                wf.rotate(u, wf.center(), Math.PI/100);
                repaint();
                oldX=x;
                oldY=y;
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            camera.drawAxis(g);
            wf.draw(camera, g);
        }
    }

} // class MainFrame