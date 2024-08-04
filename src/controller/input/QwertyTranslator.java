package controller.input;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class QwertyTranslator implements IKeyTranslator {

    @Override
    public KeyEvent translateKey(KeyEvent key) {

        switch(key.getKeyChar()) {

            case 'q':
                return fabricate(KeyEvent.VK_A, 'a');
            case 'w':
                return fabricate(KeyEvent.VK_Z, 'z');
            case 'a':
                return fabricate(KeyEvent.VK_Q, 'q');
            case ';':
                return fabricate(KeyEvent.VK_M, 'm');
            case 'z':
                return fabricate(KeyEvent.VK_A, 'w');

        }

        return key;
    }

    private KeyEvent fabricate(int code, char c) {
        return new KeyEvent(new JFrame(), 1, 0, 0, code, c);
    }

}
