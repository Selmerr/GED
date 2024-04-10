// Demo af event handlere: MouseMotionAdapter, ActionListener, TimerListener
package Library;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class BallApp extends JFrame {
    BallPanel pBall = new BallPanel();
    JPanel pButtons = new JPanel();
    JRadioButton rBlack = new JRadioButton("Black");
    JRadioButton rBlue = new JRadioButton("Blue");
    JRadioButton rRed = new JRadioButton("Red");
    JCheckBox cFilled = new JCheckBox("Filled");
    JTextField fRadius = new JTextField(5);
    JButton bSet = new JButton("Set");
    JButton bDrop = new JButton("Drop");

    public static void main(String[] args) {
        BallApp frame = new BallApp();
        frame.setTitle("BallApp");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } // main()

    BallApp() {
        setLayout(new BorderLayout());

        // Ball panel
        add(pBall, BorderLayout.CENTER);
        pBall.setBorder(new LineBorder(Color.BLACK));

        // Buttons panel
        add(pButtons, BorderLayout.SOUTH);

        pButtons.add(rBlack);
        pButtons.add(rBlue);
        pButtons.add(rRed);
        ButtonGroup gColor = new ButtonGroup();
        gColor.add(rBlack);
        gColor.add(rBlue);
        gColor.add(rRed);
        rBlack.setSelected(true);

        pButtons.add(cFilled);
        cFilled.setSelected(true);

        pButtons.add(new JLabel("Radius"));
        pButtons.add(fRadius);
        fRadius.setText("20");

        pButtons.add(bSet);
        pButtons.add(bDrop);

        // Event handlers
        MyButtonListener l=new MyButtonListener();
        rBlack.addActionListener(l);
        rBlue.addActionListener(l);
        rRed.addActionListener(l);
        cFilled.addActionListener(l);
        bSet.addActionListener(l);
        bDrop.addActionListener(l);
    }

    class BallPanel extends JPanel {
        int x=300;
        int y=200;
        int r=20;
        Color color=Color.BLACK;
        boolean filled=true;

        BallPanel() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    x=e.getX();
                    y=e.getY();
                    repaint();
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    x=e.getX();
                    y=e.getY();
                    repaint();
                }
            });
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setForeground(color);
            if (filled)
                g.fillOval(x-r, y-r, 2*r, 2*r);
            else
                g.drawOval(x-r, y-r, 2*r, 2*r);
        }
    } // class BallPanel

    class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Black":
                    pBall.color=Color.BLACK;
                    break;
                case "Blue":
                    pBall.color=Color.BLUE;
                    break;
                case "Red":
                    pBall.color=Color.RED;
                    break;
                case "Filled":
                    pBall.filled=!pBall.filled;
                    break;
                case "Set":
                    try {
                        String s=fRadius.getText();
                        pBall.r=Integer.parseInt(s);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Indtast kun tal");
                        fRadius.setText(Integer.toString(pBall.r));
                    }
                    break;
                case "Drop":
                    timer.start();
                    break;
            }
            pBall.repaint();
        }
    } // MyButtonListener

    Timer timer=new Timer(100, new MyTimerListener());
    class MyTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (pBall.getHeight()>pBall.y+pBall.r) {
                pBall.y+=5;
            } else {
                timer.stop();
            }
            pBall.repaint();
        }
    }




} // BallApp