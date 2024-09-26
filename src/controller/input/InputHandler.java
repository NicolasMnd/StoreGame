package controller.input;

import listeners.InputNotifier;
import util.Logger;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that will delegate mouse input to the controller layer.
 * A {@link javax.swing.JFrame} element will put all input it gets through this class. This class will in turn
 * delegate that input to {@link InputNotifier} in {@link controller.GameController}, which will send concrete commands
 * to {@link controller.GameFacade} via {@link InputCommandeer}
 */
public class InputHandler implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {

    private List<InputNotifier> mouseListeners;
    private List<KeyEvent> repeatedCommandSenders;
    private final IKeyTranslator translator;
    private Logger logger = new Logger("");

    public InputHandler() {
        this.mouseListeners = new ArrayList<>();
        this.repeatedCommandSenders = new ArrayList<>();
        this.translator = new AzertyTranslator();
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
        /*for(InputNotifier listener : mouseListeners)
            listener.enterCharacter(translator.translateKey(e));*/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Press");
        if(this.repeatedCommandSenders.stream().map(KeyEvent::getKeyChar).noneMatch(entry -> entry.equals(e.getKeyChar())))
            this.repeatedCommandSenders.add(translator.translateKey(e));
        repeatedCommandSenders = new KeyCompoundHelper().translateKeys(repeatedCommandSenders);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Relase");
        e = translator.translateKey(e);
        KeyEvent finalE = e; // ?
        this.repeatedCommandSenders = new ArrayList<>(this.repeatedCommandSenders.stream()
                .filter(keyEvent -> !String.valueOf(keyEvent.getKeyChar()).equalsIgnoreCase(String.valueOf(finalE.getKeyChar())))
                .toList()
        );
        repeatedCommandSenders = new KeyCompoundHelper().removeCombination(repeatedCommandSenders, finalE.getKeyChar());

        for (InputNotifier listener : mouseListeners) {
            listener.releaseCharacter(e);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation() == -1) {
            for(InputNotifier listener : mouseListeners)
                listener.scrollUp();
        }
        else if(e.getWheelRotation() == 1) {
            for(InputNotifier listener : mouseListeners)
                listener.scrollDown();
        }
    }

    /**
     * Keys that are still pressed will be resent for function
     */
    public void resendActiveKeys() {
        for (KeyEvent e : repeatedCommandSenders)
            for (InputNotifier listener : mouseListeners) {
                listener.enterCharacter(e);
            }
    }

}
