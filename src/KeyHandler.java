import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    private final DemoPanel panel;

    public KeyHandler(DemoPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ENTER) {
            panel.search();
        }

        if(code == KeyEvent.VK_P) {
            panel.autoSearch();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
