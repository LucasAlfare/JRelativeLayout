package com.main;

import javax.swing.*;

@SuppressWarnings("WeakerAccess")
public class Main extends JFrame {

    public Main() {
        setSize(200, 400);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new JRelativeLayout());

        JLabel a = new JLabel("carregando scramble...");
        a.setName("scramble");

        JLabel b = new JLabel("00:00.000");
        b.setName("display");

        JCheckBox c = new JCheckBox("USE inspection");
        c.setName("insp");

        JButton d = new JButton("Statistics");
        d.setName("stats");

        JButton e = new JButton("Solves");
        e.setName("solves");

        add(a, "centerHorizontal=true");
        add(b, "centerInParent=true");
        add(c, "bellow=display centerHorizontal=true");
        add(d, "parentBottom=true");
        add(e, "parentBottom=true parentEnd=true");

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
