package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGameSenior {

    static long startTime = 0;
    javax.swing.Timer t;

    @BeforeEach
    public void init() {
        startTime = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();
        t = new javax.swing.Timer((int) 4,
                (e) -> {
                    GameSenior senior = new GameSenior();
                    run();
                }
        );

    }

    @Test
    public void testGameSenior() {
        t.start();
    }

    void run() {
        if(startTime + 8 < System.currentTimeMillis()) {
            t.stop();
        }
    }

}
