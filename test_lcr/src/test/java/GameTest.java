import Player.Player;
import UI.TerminalUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameTest {

    private final InputStream stdin = System.in;
    Game testGame;

    @BeforeAll
    void constructor(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("10,10\nA\n".getBytes());
        System.setIn(in);
        Game testGame = new Game();
        this.testGame = testGame;
    }

    @Test
    void initialize() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("y\nA\nx\nB".getBytes());
        System.setIn(in);
        //this.testGame.initialize();
        // optionally, reset System.in to its original
        //System.setIn(sysInBackup);
    }

    @Test
    void boardConfiguration() {

    }

    @Test
    void gameOn() {
    }
}