package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final Display display;

    public KeyHandler(Display display) {
        this.display = display;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_I) {
            display.incrementZoom();
            System.out.println("Zoom In");
        }
        if(e.getKeyCode() == KeyEvent.VK_O) {
            display.decrementZoom();
            System.out.println("Zoom Out");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
