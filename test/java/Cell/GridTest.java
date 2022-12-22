package Cell;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    Grid testGrid;

    @BeforeEach
    void Initialize() {
        Grid testGrid = new Grid(new int[]{100, 200});
        this.testGrid = testGrid;
    }

    @Test
    void isAlive() {
        assertEquals(false,this.testGrid.isAlive(10,10));
        this.testGrid.activateCell(" ",10,10);
        assertEquals(true,this.testGrid.isAlive(10,10));
        this.testGrid.killCell(10,10);
        assertEquals(false,this.testGrid.isAlive(10,10));
    }

    @Test
    void activateCell() {
        assertEquals(false,this.testGrid.isAlive(10,10));
        this.testGrid.activateCell(" ",10,10);
        assertEquals(true,this.testGrid.isAlive(10,10));
    }

    @Test
    void killCell() {
        assertEquals(false,this.testGrid.isAlive(10,10));
        this.testGrid.activateCell(" ",10,10);
        assertEquals(true,this.testGrid.isAlive(10,10));
        this.testGrid.killCell(10,10);
        assertEquals(false,this.testGrid.isAlive(10,10));
    }

    @Test
    void generate() {
        this.testGrid.generate();
        this.testGrid.activateCell("b",1,1);
        this.testGrid.generate();
        this.testGrid.activateCell("b",1,1);
        this.testGrid.activateCell("b",1,2);
        this.testGrid.generate();
        this.testGrid.activateCell("b",1,1);
        this.testGrid.activateCell("b",1,2);
        this.testGrid.activateCell("b",1,3);
        this.testGrid.generate();

        for(int i=0;i<100;i++){
            for(int j=0;j<200;j++){
                assertNotNull(this.testGrid.getCells()[i][j]);
            }
        }
    }

    @Test
    void extinct() {
        this.testGrid.activateCell("b",8,8);
        this.testGrid.activateCell("a",3,3);
        assertEquals(false,this.testGrid.isExtinct("a"));
        assertEquals(false,this.testGrid.isExtinct("b"));

        this.testGrid.killCell(8,8);
        assertEquals(true,this.testGrid.isExtinct("b"));

        this.testGrid.killCell(3,3);
        assertEquals(true,this.testGrid.isExtinct("a"));

    }
}