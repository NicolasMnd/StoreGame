package listeners;

import javax.swing.*;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;

/**
 * This interface aims to serve {@link render.View}. This class may contain a {@link JFrame} which can register all kinds of listeners.
 * This class will ask the user to use the specific {@link JFrame#addComponentListener(ComponentListener)} / {@link JFrame#addContainerListener(ContainerListener)} ...
 * and the implementation of the listener somewhere else...
 */
public interface ListenerRegistrator {

    void registerListener(JFrame frame);

}
