package listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that will delegate mouse input to the controller layer
 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    private List<IMouseNotifier> mouseListeners;

    public MouseHandler() {
        this.mouseListeners = new ArrayList<>();
    }

    public void subscribeListener(IMouseNotifier listener) {
        this.mouseListeners.add(listener);
    }

    public void unsubscribe(IMouseNotifier listener) {
        this.mouseListeners.remove(listener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(IMouseNotifier listener : mouseListeners)
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
        for(IMouseNotifier listener : mouseListeners)
            listener.hover(e.getX(), e.getY());
    }

}
