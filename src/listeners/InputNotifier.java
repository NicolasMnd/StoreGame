package listeners;

import java.awt.event.KeyEvent;

public interface InputNotifier {

    void hover(int x, int y);

    void leftClick(int x, int y);

    void rightClick(int x, int y);

    void enterCharacter(KeyEvent event);

    void releaseCharacter(KeyEvent event);

    void scrollUp();

    void scrollDown();

}
