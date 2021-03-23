package com.lucasalfare.jrelativelayout.main;

import javax.swing.*;

// static import of the helper method to define constraints
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
        setLayout(new JRelativeLayout());

        JButton a = new JButton("a");
        JButton b = new JButton("b");
        JButton c = new JButton("c");

        add(a, rawConstraints().parentTop().centerHorizontal().marginTop(8).marginStart(8));
        add(b, rawConstraints().parentEnd().centerVertical().marginTop(8).marginEnd(8));
        add(c, rawConstraints().parentTop().marginTop(8).marginStart(8));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Testing();
    }
}
