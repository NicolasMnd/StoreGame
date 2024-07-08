package listeners;

public interface InputNotifier {

    void hover(int x, int y);

    void leftClick(int x, int y);

    void rightClick(int x, int y);

    void enterCharacter();

}
