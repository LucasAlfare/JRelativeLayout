package com.main;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setSize(400, 400);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        RelativeLayout layout = new RelativeLayout();
        setLayout(layout);

        RelativeLayoutConstraints constraints = new RelativeLayoutConstraints();

        JButton a = new JButton("a");
        JButton b = new JButton("b");
        JButton c = new JButton("c");
        JButton d = new JButton("d");
        JButton e = new JButton("e");

        constraints.parentTop = true;
        constraints.centerHorizontal = true;
        add(a, constraints);
        constraints = new RelativeLayoutConstraints();

        constraints.centerHorizontal = true;
        constraints.centerVertical = true;
        add(b, constraints);
        constraints = new RelativeLayoutConstraints();

        constraints.parentStart = true;
        constraints.parentBottom = true;
        add(c, constraints);
        constraints = new RelativeLayoutConstraints();

        constraints.parentBottom = true;
        constraints.endOf = c;
        add(d, constraints);
        constraints = new RelativeLayoutConstraints();

        constraints.parentEnd = true;
        constraints.parentBottom = true;
        add(e, constraints);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
