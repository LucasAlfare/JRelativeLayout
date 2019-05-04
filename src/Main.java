import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setSize(600, 600);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new RelativeLayout());

        JButton a = new JButton("button a");
        a.setName("comp a");

        JButton b = new JButton("button b");
        b.setName("comp b");

        JButton c = new JButton("button c");
        JButton d = new JButton("button d");
        JButton e = new JButton("button e");

        add(a, "parametro=kkk teste=");
        add(b, "param=help oiamigo=hehehehe");

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
