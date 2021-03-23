package com.lucasalfare.jrelativelayout.main;

import javax.swing.*;

// static import of the helper method to define constraints
import java.awt.*;

import static com.lucasalfare.jrelativelayout.main.JRelativeLayout.rawConstraints;

/**
 * This should inflate a frame that looks like something:
 * |-----------------------------|
 * | C           A               |
 * |                             |
 * |                             |
 * |                           B |
 * |                             |
 * |                             |
 * |                             |
 * -------------------------------
 */

public class Testing extends JFrame {

    public Testing() {
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //root layout of the main frame
        setLayout(new FlowLayout());

        // creates two example panels
        JPanel p1 = new JPanel();
        p1.setBackground(Color.BLUE);
        p1.setSize(100, 300);

        JPanel p2 = new JPanel();
        p2.setBackground(Color.GREEN);
        p2.setSize(100, 300);

        // creates some components to be added in the panels
        JButton a1 = new JButton("a1");
        JButton b1 = new JButton("b1");

        JButton a2 = new JButton("a2");
        JButton b2 = new JButton("b2");

        // define layout manager of the child panels as JRelativeLayout
        p1.setLayout(new JRelativeLayout());
        p2.setLayout(new JRelativeLayout());

        // setup positions of components
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
