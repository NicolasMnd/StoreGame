package listeners;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that will delegate mouse input to the controller layer
 */
public class InputHandler implements MouseListener, MouseMotionListener, KeyListener {

    private List<InputNotifier> mouseListeners;

    public InputHandler() {
        this.mouseListeners = new ArrayList<>();
    }

    public void subscribeListener(InputNotifier listener) {
        this.mouseListeners.add(listener);
    }

    public void unsubscribe(InputNotifier listener) {
        this.mouseListeners.remove(listener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(InputNotifier listener : mouseListeners)
            if(e.getButton() == 1)
                listener.leftClick(e.getX(), e.getY());
            else if(e.getButton() == 2)
                listener.rightClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(InputNotifier listener : mouseListeners)
            listener.hover(e.getX(), e.getY());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for(InputNotifier listener : mouseListeners)
            listener.enterCharacter();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
