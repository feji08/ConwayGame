import Cell.Grid;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpeningBoardTest {

    @Test
    void gambit() {
        Grid testGrid = new Grid(new int[]{10,10});
        OpeningBoard.gambit(testGrid,"b","g",1);

        assertEquals(true,testGrid.isAlive(3,3));
        assertEquals("g",testGrid.getCamp(3,3));

        assertEquals(true,testGrid.isAlive(4,3));
        assertEquals("b",testGrid.getCamp(4,3));

        OpeningBoard.gambit(testGrid,"b","g",2);
        OpeningBoard.gambit(testGrid,"b","g",3);
        OpeningBoard.gambit(testGrid,"b","g",4);
        OpeningBoard.gambit(testGrid,"b","g",5);



    }
}