import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame win = new JFrame();
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setResizable(false);
        win.add(new DemoPanel());

        win.pack();
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }
}