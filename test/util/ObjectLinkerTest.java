package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static util.Direction.*;

public class ObjectLinkerTest {

    ObjectLinker<Anus> i;
    Anus[][] anusArray = new Anus[][] {
        {new Anus(1, NORTH), new Anus(2, NORTH), new Anus(3, NORTH), new Anus(3, NORTH), new Anus(3, SOUTH), new Anus(2, WEST)},
        {new Anus(1, NORTH), new Anus(3, NORTH), new Anus(3, NORTH), new Anus(3, NORTH), new Anus(3, SOUTH), new Anus(2, NORTH)},
        {new Anus(1, NORTH), new Anus(3, NORTH), new Anus(3, NORTH), new Anus(3, NORTH), new Anus(3, SOUTH), new Anus(2, NORTH)},
        {new Anus(1, NORTH), new Anus(2, NORTH), new Anus(3, NORTH), new Anus(2, NORTH), new Anus(3, NORTH), new Anus(2, NORTH)},
        {new Anus(1, NORTH), new Anus(3, NORTH), new Anus(2, NORTH), new Anus(1, NORTH), new Anus(1, NORTH), new Anus(2, NORTH)},
    };

    @BeforeEach
    public void init() {
        i = new Linker (

                anusArray,

                i -> i.anusNummer >= 3,

                (anus1, anus2) -> {
                    anus1.addAnus(anus2);
                    anus2.addAnus(anus1);
                    return true;
                },

                Anus::arePeers,

                (anus1, anus2) -> true

        );
    }

    @Test
    public void getLengthPeersTest() {
        assertEquals(
                i.getLengthPeers(anusArray[0], 2),
                new Pair<>(2, 3)
        );
        assertEquals(
                i.getLengthPeers(anusArray[1], 1),
                new Pair<>(1, 4)
        );
        assertEquals(
                i.getLengthPeers(anusArray[2], 1),
                new Pair<>(1, 4)
        );
        assertEquals(
                i.getLengthPeers(anusArray[3], 2),
                new Pair<>(2, 1)
        );
    }

    @Test
    public void testContainsPeers() {
        assertEquals(i.containsPeers(anusArray[0], 0),2);
        assertEquals(i.containsPeers(anusArray[0], 1),2);
        assertFalse(i.containsPeers(anusArray[4], 2) > 0);
    }

    @Test
    public void testGetPeerObjectsInRow() {

    }

    private static class Linker extends ObjectLinker<Anus> {
        public Linker(Anus[][] matrix, Predicate<Anus> isPeerObject, BiFunction<Anus, Anus, Boolean> action, BiPredicate<Anus, Anus> arePeers, BiPredicate<Anus, Anus> canBePeers) {
            super(matrix, isPeerObject, action, arePeers, canBePeers);
        }

        @Override
        public int getLinkRowAmount() {
            return 3;
        }
    }

    private static class Anus {

        private final int anusNummer;
        private List<Anus> peers;
        private Direction dir;

        public Anus(int i, Direction dir) {
            this.anusNummer = i;
            this.dir = dir;
        }

        public Direction getDir() {
            return dir;
        }

        public void addAnus(Anus anus) {
            this.peers.add(anus);
        }

        public boolean arePeers(Anus anus) {
            return this.peers.contains(anus);
        }

    }

}
