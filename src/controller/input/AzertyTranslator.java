package controller.input;

import java.awt.event.KeyEvent;

public class AzertyTranslator implements IKeyTranslator {

    @Override
    public KeyEvent translateKey(KeyEvent key) {
        return key;
    }
}
