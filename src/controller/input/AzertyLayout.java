package controller.input;

import java.awt.event.KeyEvent;

public class AzertyLayout implements IKeyTranslator {

    @Override
    public KeyEvent translateKey(KeyEvent key) {
        return key;
    }
}
