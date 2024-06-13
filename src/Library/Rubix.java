package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.Math.*;

public class Rubix extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new Rubix();
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    Rubix() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        Timer timer = new Timer(10, new TimerListener());

        Camera S = new Camera(100, 100, 400, 400);

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
        double phi = PI/1000;

        M3 Rz = I.add(Sz.mul(Math.sin(phi))).add(Sz.mul(Sz).mul(1 - Math.cos(phi)));
        M3 Ry = I.add(Sy.mul(Math.sin(phi))).add(Sy.mul(Sy).mul(1 - Math.cos(phi)));
        M3 Rx = I.add(Sx.mul(Math.sin(phi))).add(Sx.mul(Sx).mul(1 - Math.cos(phi)));
        M3 Ru = I.add(Su.mul(Math.sin(phi))).add(Su.mul(Su).mul(1 - Math.cos(phi)));

        V3 C = new V3(0,0,0);
        V3[] cube = new V3[8];
        ArrayList<Cube> cubes = new ArrayList<>();

        ArrayList<Cube> selectedCubes = new ArrayList<>();
        V3 tx = new V3(1.1,0,0);
        V3 ty = new V3(0,1.1,0);
        V3 tz = new V3(0,0,1.1);


        V3 t1 = new V3(7,7,7);
        V3 t2 = new V3(8,8,8);
        V3 cameraPosition = new V3(10, 5, 2);
        int prevX, prevY;

        V3 row1Center, row3Center, col1Center, col3Center;

        V3 center = new V3(0,0,0);

        DrawPanel() {
            timer.start();
            setFocusable(true);
            addKeyListener(new MyKeyListener());
            addMouseWheelListener(new MyWheelListener());
            addMouseMotionListener(new MyMotionListener());
            cube[0]=new V3(1,1,1);
            cube[1]=new V3(1,1,2);
            cube[2]=new V3(2,1,1);
            cube[3]=new V3(2,1,2);
            cube[4]=new V3(1,2,1);
            cube[5]=new V3(1,2,2);
            cube[6]=new V3(2,2,1);
            cube[7]=new V3(2,2,2);
            Cube cube1 = new Cube(cube);
            for (int l = 0; l<3; l++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        V3[] temp = new V3[8];
                        for (int i = 0; i < 8; i++) {
                            temp[i] = cube[i].add(tz.mul(k)).add(tx.mul(j)).add(ty.mul(l));
                        }
                        cubes.add(new Cube(temp));
                    }
                }
            }
            for (Cube cube : cubes) {
                center = center.add(cube.C);
                System.out.println("Cube Y: "+cube.C.y);
            }
            center = center.mul((double) 1 /cubes.size());
            System.out.println("Center Y: "+center.y);
            S.moveTo(cameraPosition);
            S.focus(center);
            S.z=6;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            S.drawAxis(g);
            S.fillRect(g,cube[0],cube[1],Color.green);
            for (Cube cube : cubes) {
                S.drawLine(g,cube.points[0],cube.points[1]);
                S.drawLine(g,cube.points[1],cube.points[3]);
                S.drawLine(g,cube.points[3],cube.points[2]);
                S.drawLine(g,cube.points[2],cube.points[0]);
                S.drawLine(g,cube.points[4],cube.points[5]);
                S.drawLine(g,cube.points[5],cube.points[7]);
                S.drawLine(g,cube.points[7],cube.points[6]);
                S.drawLine(g,cube.points[6],cube.points[4]);
                S.drawLine(g,cube.points[0],cube.points[4]);
                S.drawLine(g,cube.points[1],cube.points[5]);
                S.drawLine(g,cube.points[3],cube.points[7]);
                S.drawLine(g,cube.points[2],cube.points[6]);
            }
        }


        class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        }

        class MyKeyListener implements KeyListener {

            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_LEFT) {
                    double angle = -PI/10;
                    angle = angle % (2 * Math.PI);
                    M3 Rz = I.add(Sz.mul(Math.sin(angle))).add(Sz.mul(Sz).mul(1 - Math.cos(angle)));
                    for (Cube cube : selectedCubes) {
                        for (int i = 0; i < 8; i++) {
                            cube.points[i] = Rz.mul(cube.points[i].sub(center)).add(center);
                        }
                    }
                }
                if(keyCode == KeyEvent.VK_RIGHT) {
                    double angle = PI/10;
                    angle = angle % (2 * Math.PI);
                    M3 Rz = I.add(Sz.mul(Math.sin(angle))).add(Sz.mul(Sz).mul(1 - Math.cos(angle)));
                    for (Cube cube : selectedCubes) {
                        for (int i = 0; i < 8; i++) {
                            cube.points[i] = Rz.mul(cube.points[i].sub(center)).add(center);
                        }
                    }
                }
                if(keyCode == KeyEvent.VK_UP) {
                    double angle = -PI/10;
                    angle = angle % (2 * Math.PI);
                    M3 Ry = I.add(Sy.mul(Math.sin(angle))).add(Sy.mul(Sy).mul(1 - Math.cos(angle)));
                    for (Cube cube : selectedCubes) {
                        for (int i = 0; i < 8; i++) {
                            cube.points[i] = Ry.mul(cube.points[i].sub(center)).add(center);
                        }
                    }
                }
                if(keyCode == KeyEvent.VK_DOWN) {
                    double angle = PI/10;
                    angle = angle % (2 * Math.PI);
                    M3 Ry = I.add(Sy.mul(Math.sin(angle))).add(Sy.mul(Sy).mul(1 - Math.cos(angle)));
                    for (Cube cube : selectedCubes) {
                        for (int i = 0; i < 8; i++) {
                            cube.points[i] = Ry.mul(cube.points[i].sub(center)).add(center);
                        }
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                double tolerance = 1e-6; // Small tolerance for floating-point comparisons

                if (keyCode == KeyEvent.VK_NUMPAD8) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (cube.C.z > center.z + tolerance) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD8 - Selected cube z: " + cube.C.z + " center z: " + center.z); // Debugging statement
                        }
                    }
                }
                if (keyCode == KeyEvent.VK_NUMPAD5) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (cube.C.z > center.z-tolerance && cube.C.z < center.z+tolerance) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD5 - Selected cube z: " + cube.C.z + " center z: " + center.z); // Debugging statement
                        }
                    }
                }
                if (keyCode == KeyEvent.VK_NUMPAD9) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (cube.C.y > center.y-tolerance && cube.C.y < center.y+tolerance) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD5 - Selected cube z: " + cube.C.z + " center z: " + center.z); // Debugging statement
                        }
                    }
                }
                if (keyCode == KeyEvent.VK_NUMPAD2) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (cube.C.z < center.z - tolerance) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD2 - Selected cube z: " + cube.C.z + " center z: " + center.z); // Debugging statement
                        }
                    }
                }
                if (keyCode == KeyEvent.VK_NUMPAD4) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (cube.C.y < center.y - tolerance) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD4 - Selected cube y: " + cube.C.y + " center y: " + center.y); // Debugging statement
                        }
                    }
                }
                if (keyCode == KeyEvent.VK_NUMPAD1) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (Math.abs(cube.C.y - center.y) < tolerance) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD1 - Selected cube y: " + cube.C.y + " center y: " + center.y); // Debugging statement
                        }
                    }
                }
                if (keyCode == KeyEvent.VK_NUMPAD6) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (cube.C.y > center.y + tolerance) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD6 - Selected cube y: " + cube.C.y + " center y: " + center.y); // Debugging statement
                        }
                    }
                }
                if (keyCode == KeyEvent.VK_NUMPAD0) {
                    selectedCubes.clear();
                    for (Cube cube : cubes) {
                        cube.updateCenter();
                        if (!selectedCubes.contains(cube)) {
                            selectedCubes.add(cube);
                            System.out.println("NUMPAD0 - Selected cube: " + cube); // Debugging statement
                        }
                    }
                }
                repaint();
            }


        }

        class MyMouseListener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                prevX = e.getX();
                prevY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }

        class MyMotionListener implements MouseMotionListener {

            @Override
            public void mouseDragged(MouseEvent e) {
                int currentX = e.getX();
                int currentY = e.getY();

                int deltaX = currentX - prevX;
                int deltaY = currentY - prevY;

                // Update the previous position
                prevX = currentX;
                prevY = currentY;


                // Apply rotations to the camera position
                if (deltaY < 0) {
                    double angle = PI/100;
                    angle = angle % (2 * Math.PI);
                    M3 Ry = I.add(Sy.mul(Math.sin(angle))).add(Sy.mul(Sy).mul(1 - Math.cos(angle)));
                    cameraPosition = Ry.mul(cameraPosition);
                }
                if (deltaY > 0) {
                    double angle = -PI/100;
                    angle = angle % (2 * Math.PI);
                    M3 Ry = I.add(Sy.mul(Math.sin(angle))).add(Sy.mul(Sy).mul(1 - Math.cos(angle)));
                    cameraPosition = Ry.mul(cameraPosition);
                }
                if (deltaX < 0) {
                    double angle = PI/100;
                    angle = angle % (2 * Math.PI);
                    M3 Rz = I.add(Sz.mul(Math.sin(angle))).add(Sz.mul(Sz).mul(1 - Math.cos(angle)));
                    cameraPosition = Rz.mul(cameraPosition);
                }
                if (deltaX > 0) {
                    double angle = -PI/100;
                    angle = angle % (2 * Math.PI);
                    M3 Rz = I.add(Sz.mul(Math.sin(angle))).add(Sz.mul(Sz).mul(1 - Math.cos(angle)));
                    cameraPosition = Rz.mul(cameraPosition);
                }

                // Move the camera to the new position
                S.moveTo(cameraPosition);
                S.focus(center);

                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        }

        class MyWheelListener implements MouseWheelListener {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if(notches<0) {
                    S.z +=1;
                }
                else {
                    S.z -=1;
                }
            }
        }
    }
}
