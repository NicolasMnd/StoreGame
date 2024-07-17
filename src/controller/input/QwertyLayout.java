package controller.input;

import java.awt.event.KeyEvent;

public class QwertyLayout implements IKeyTranslator {

    @Override
    public KeyEvent translateKey(KeyEvent key) {
        switch(key.getKeyChar()) {
            case 'q':
                return new KeyEvent(null, 1, 0, 0, KeyEvent.VK_A, 'a');
            case 'w':
                return new KeyEvent(null, 1, 0, 0, KeyEvent.VK_Z, 'z');
        }
        return null;
    }
}
