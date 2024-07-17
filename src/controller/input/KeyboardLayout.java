package controller.input;

import java.awt.event.KeyEvent;

public class KeyboardLayout {

    private final LayoutType type;

    public KeyboardLayout(LayoutType type) {
        this.type = type;
    }

    public KeyEvent getKey(KeyEvent key) {
        if(type == LayoutType.AZERTY)
            return new AzertyLayout().translateKey(key);
        else if(type == LayoutType.QWERTY)
            return new QwertyLayout().translateKey(key);
        return key;
    }

    public enum LayoutType {
        AZERTY, QWERTY
    }

}
