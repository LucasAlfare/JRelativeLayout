package com.main;

import javax.swing.*;

@SuppressWarnings("WeakerAccess")
public class Main extends JFrame {

    public Main() {
        setSize(200, 400);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new RelativeLayout());

        JButton a = new JButton("button a");
        a.setName("comp_a");

        JButton b = new JButton("button b");
        b.setName("comp_b");

        JButton c = new JButton("button c");
        c.setName("comp_c");

        JButton d = new JButton("button d");
        d.setName("comp_d");

        JButton e = new JButton("button e");
        e.setName("comp_e");

        add(a, "centroParent=true topoParent=true");
        add(b, "centroParent=true");
        add(c, "abaixoDe=comp_b l=match");
        add(d, "esquerdaParent=true baseParent=true");
        add(e, "direitaParent=true baseParent=true");

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
