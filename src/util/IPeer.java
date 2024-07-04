package util;

import java.util.List;
import java.util.function.Function;

public interface IPeer<T> {

    List<T> getPeers();

    void addPeer(T type);

    void executeOnPeer(Function<T, Boolean> func);

}
