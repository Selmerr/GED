package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotationCamera extends JFrame {
        public static void main(String[] args) {
            JFrame frame = new RotationCamera();
            frame.setSize(1000, 800);
            frame.setTitle("Rotate Animation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        }

        RotationCamera() {
            add(new DrawPanel());
        }

        class DrawPanel extends JPanel {
            Timer myTimer = new Timer(50, new TimerListener());
            Camera S = new Camera(100,100,400,400);
            int count = 0;

            V3 u     = new V3(1,2,3).unit();

            M3 I = new M3(1,0,0,
                    0,1,0,
                    0,0,1);

            M3 Sz = new M3(0, -1, 0,
                    1, 0, 0,
                    0, 0, 0);

            M3 Sy = new M3(0,0,1,
                    0,0,0,
                    -1,0,0);
            M3 Sx = new M3(0,0,0,
                    0,0,-1,
                    0,1,0);

            M3 Su = new M3(0,-u.z,u.y,
                    u.z,0,-u.x,
                    -u.y,u.x,0);
            double phi = Math.PI/100;

            V3 C = new V3(0,0,0);

            M3 Rz = I.add(Sz.mul(Math.sin(phi))).add(Sz.mul(Sz).mul(1 - Math.cos(phi)));
            M3 Ry = I.add(Sy.mul(Math.sin(phi))).add(Sy.mul(Sy).mul(1 - Math.cos(phi)));
            M3 Rx = I.add(Sx.mul(Math.sin(phi))).add(Sx.mul(Sx).mul(1 - Math.cos(phi)));
            M3 Ru = I.add(Su.mul(Math.sin(phi))).add(Su.mul(Su).mul(1 - Math.cos(phi)));


            V3[] cube = new V3[8];
            DrawPanel() {
                myTimer.start();

                cube[0]=new V3(1,4,1);
                cube[1]=new V3(1,4,3);
                cube[2]=new V3(1,6,1);
                cube[3]=new V3(1,6,3);
                cube[4]=new V3(3,4,1);
                cube[5]=new V3(3,4,3);
                cube[6]=new V3(3,6,1);
                cube[7]=new V3(3,6,3);

                for(V3 p : cube) C=C.add(p);
                C = C.mul(1.0/ cube.length);

                S.moveTo(new V3(10,5,2));
                S.focus(C);
                S.z=6;
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                count++;
                g.drawString("count: "+count,10,10);
                S.drawAxis(g);
                S.drawLine(g,cube[0],cube[1]);
                S.drawLine(g,cube[1],cube[3]);
                S.drawLine(g,cube[3],cube[2]);
                S.drawLine(g,cube[2],cube[0]);
                S.drawLine(g,cube[4],cube[5]);
                S.drawLine(g,cube[5],cube[7]);
                S.drawLine(g,cube[7],cube[6]);
                S.drawLine(g,cube[6],cube[4]);
                S.drawLine(g,cube[0],cube[4]);
                S.drawLine(g,cube[1],cube[5]);
                S.drawLine(g,cube[3],cube[7]);
                S.drawLine(g,cube[2],cube[6]);


                //Rotate cube
                for(int i=0; i<8; i++) {
//                cube[i] = Rz.mul(cube[i].sub(C)).add(C);
//                cube[i] = Ry.mul(cube[i].sub(C)).add(C);
//                cube[i] = Rx.mul(cube[i].sub(C)).add(C);
                    cube[i] = Ru.mul(cube[i].sub(C)).add(C);


                }


            }

            class TimerListener implements ActionListener {
                public void actionPerformed(ActionEvent evt) {
                    repaint();
                }
            }
        }
    }
