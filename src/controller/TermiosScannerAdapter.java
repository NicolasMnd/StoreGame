package controller;

import com.misterc.input.InputHandler;
import com.misterc.input.InputType;
import io.github.btj.termios.Terminal;

public class TermiosScannerAdapter implements InputHandler {

    public TermiosScannerAdapter(Runnable runnable) {

    }

    @Override
    public InputType<Byte> getInput() {
        return null;
    }

    @Override
    public boolean stop(InputType inputType) {
        return false;
    }

    private void listenToAWT(Runnable runnable) {
        Terminal.setInputListener(runnable);
    }

}
