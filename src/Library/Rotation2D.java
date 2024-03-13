package Library;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.*;

public class Rotation2D extends JFrame{

        public static void main(String[] args) {
            JFrame frame = new Rotation2D();
            frame.setSize(700, 700);
            frame.setTitle("Stjernen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        }

        public Rotation2D() {
            add(new DrawPanel());
        }

        class DrawPanel extends JPanel {
            S2 S=new S2(50,50, 120,300);

            V2 A = new V2(2,2);
            V2 B = new V2(4,2);
            V2 C = new V2(4,4);
            V2 D = new V2(2,4);

            V2 T1 = new V2(2,2);
            V2 T2 = new V2(3,4);
            V2 T3 = new V2(4,2);

            V2 TV = new V2(-1,1);

            V2 TT1 = T1.add(TV);
            V2 TT2 = T2.add(TV);
            V2 TT3 = T3.add(TV);

            M2 reflectMatrix = new M2(1,0,0,-1);

            V2 P = A.add(B).add(C).add(D).mul(1.0/4); // rotationspunkt

            double phi = PI/10;         //rotationsvinkel

            M2 M = new M2(cos(phi), -sin(phi),sin(phi), cos(phi));

            V2 Ar = M.vectorMulti(A.sub(P)).add(P);
            V2 Br = M.vectorMulti(B.sub(P)).add(P);
            V2 Cr = M.vectorMulti(C.sub(P)).add(P);
            V2 Dr = M.vectorMulti(D.sub(P)).add(P);

            V2 Aro = M.vectorMulti(A);
            V2 Bro = M.vectorMulti(B);
            V2 Cro = M.vectorMulti(C);
            V2 Dro = M.vectorMulti(D);

            V2 yvector = new V2(0,3);

            V2 Tr1 = reflectMatrix.vectorMulti(T1.sub(yvector)).add(yvector);
            V2 Tr2 = reflectMatrix.vectorMulti(T2.sub(yvector)).add(yvector);
            V2 Tr3 = reflectMatrix.vectorMulti(T3.sub(yvector)).add(yvector);

            //Opgave D
            V2 Ce = new V2(2,2); //Cirkel centrum
            double CirkelR = 1;



            ArrayList<V2> cirkelPeripheri = new ArrayList<V2>();
            ArrayList<V2> ellipsePeripheri = new ArrayList<V2>();
            M2 stretchX = new M2(2, 0, 0, 1);
            M2 stretchY = new M2(1,0,0,0.5);
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                S.drawAxis(g);

//                S.drawLine(g,A,B);
//                S.drawLine(g,B,C);
//                S.drawLine(g,C,D);
//                S.drawLine(g,D,A);
//                S.drawLine(g,Dr,Ar);
//                S.drawLine(g,Br,Ar);
//                S.drawLine(g,Br,Cr);
//                S.drawLine(g,Cr,Dr);
//                S.drawLine(g,Dro,Aro);
//                S.drawLine(g,Bro,Aro);
//                S.drawLine(g,Bro,Cro);
//                S.drawLine(g,Cro,Dro);

//                S.drawLine(g, T1,T2);
//                S.drawLine(g, T3,T2);
//                S.drawLine(g, T1,T3);
//
//                S.drawLine(g, Tr1,Tr2);
//                S.drawLine(g, Tr3,Tr2);
//                S.drawLine(g, Tr1,Tr3);

                //Lav cirkelpunkter i array
                for (double v=0; v<2*Math.PI; v+=0.01){         // vinkel er parameter
                    V2 p=Ce.add(new V2(CirkelR*Math.cos(v), CirkelR*Math.sin(v))); // punkter på ellipse
                    cirkelPeripheri.add(p);
                }
                //Stretch and compress
                for (V2 pny : cirkelPeripheri) {
                    pny = stretchX.vectorMulti(pny).sub(new V2(2,0));
                    pny = stretchY.vectorMulti(pny).add(new V2(0,1));
                    ellipsePeripheri.add(pny);
                }

                //Tegn cirkel
                for (V2 p : cirkelPeripheri) {
                    S.drawPoint(g,p);
                }
                //Tegn ellipse
                for (V2 p : ellipsePeripheri) {
                    S.drawPoint(g,p);
                }
            }
        } // class DrawPanel
    } // GraphicsApp

