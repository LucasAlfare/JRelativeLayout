package com.lucasalfare.jrelativelayout.main;

import javax.swing.*;

// static import of the helper method to define constraints
import java.awt.*;

import static com.lucasalfare.jrelativelayout.main.JRelativeLayout.rawConstraints;

public class Testing extends JFrame {

    public Testing() {
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.BLUE);
        p1.setBounds(0, 0, 100, 450);

        JPanel p2 = new JPanel();
        p2.setBackground(Color.GREEN);
        p2.setBounds(100, 0, 100, 300);

        JButton a1 = new JButton("a1");
        JButton b1 = new JButton("b1");

        JButton a2 = new JButton("a2");
        JButton b2 = new JButton("b2");

        p1.setLayout(new JRelativeLayout());
        p2.setLayout(new JRelativeLayout());

        p1.add(a1, rawConstraints().centerVertical());
        p1.add(b1, rawConstraints().below(a1));

        p2.add(a2);
        p2.add(b2, rawConstraints().parentBottom());

        this.add(p1);
        this.add(p2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Testing();
    }
}
