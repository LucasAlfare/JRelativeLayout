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

        // root frame layout is set to a JRelativeLayout too
        setLayout(new JRelativeLayout());


        setVisible(true);
    }

    public static void main(String[] args) {
        new Testing();
    }
}
