package com.lucasalfare.jrelativelayout.main;

import javax.swing.*;

// static import of the helper method to define constraints
import java.awt.*;

import static com.lucasalfare.jrelativelayout.main.JRelativeLayout.Constraints.MATCH_PARENT;
import static com.lucasalfare.jrelativelayout.main.JRelativeLayout.rawConstraints;

public class Testing extends JFrame {

    public Testing() {
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // root frame layout is set to a JRelativeLayout too
        setLayout(new JRelativeLayout());

        JPanel p1 = new JPanel();
        p1.setBackground(Color.red);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.green);
        JPanel p3 = new JPanel();
        p3.setBackground(Color.blue);

        this.add(p1, rawConstraints()
                .parentTop()
                .parentStart()
                .percentileWidth(MATCH_PARENT)
                .percentileHeight(20));

        this.add(p2, rawConstraints()
                .parentBottom()
                .parentStart()
                .percentileWidth(50)
                .percentileHeight(10));

        this.add(p3, rawConstraints()
                .parentBottom()
                .parentEnd()
                .percentileWidth(50)
                .percentileHeight(10));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Testing();
    }
}
