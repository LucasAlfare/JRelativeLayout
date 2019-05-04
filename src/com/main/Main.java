package com.main;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setSize(600, 600);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        RelativeLayout rl = new RelativeLayout();
        setLayout(rl);

        JButton a = new JButton("button a");
        a.setName("comp_a");

        JButton b = new JButton("button b");
        b.setName("comp_b");

        JButton c = new JButton("button c");
        JButton d = new JButton("button d");
        JButton e = new JButton("button e");

        add(a, "centroParent=true");

        rl.alterarComandoDeComponente("comp_a", "topoParent=true", getContentPane());

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
