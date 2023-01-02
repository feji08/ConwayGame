package Player;

import Cell.Grid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player testPlayer;
    Grid testGrid;


    @BeforeEach
    void initialize(){
        Grid testGrid = new Grid(new int[]{10, 10});
        Player testPlayer = new Player(testGrid,"Tester","b");
        this.testGrid = testGrid;
        this.testPlayer = testPlayer;
    }

    @Test
    void killCell() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("8,8".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        PrintStream stdout = System.out;
        System.setOut(ps);

        this.testGrid.activateCell("s",8,8);
        this.testPlayer.killCell();


        assertEquals(false,this.testGrid.isAlive(8,8));
    }

    @Test
    void activateCell() {
        if(!this.testGrid.isAlive(8,8)){
            InputStream stdin = System.in;
            System.setIn(new ByteArrayInputStream("8,8".getBytes()));

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(byteArrayOutputStream);
            PrintStream stdout = System.out;
            System.setOut(ps);
            this.testPlayer.activateCell();
        }
        assertEquals(true,this.testGrid.isAlive(8,8));
    }

    @Test
    void isExtinct() {
        assertEquals(true, this.testGrid.isExtinct("s"));
        assertEquals(true,this.testPlayer.isExtinct());
        this.testGrid.activateCell("s",7,7);
        this.testGrid.activateCell("s",8,8);
        this.testGrid.activateCell("b",3,3);
        assertEquals(false,this.testGrid.isExtinct("s"));
        assertEquals(false,this.testPlayer.isExtinct());

        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("8,8".getBytes()));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        assertEquals(false,this.testGrid.isExtinct("s"));
        assertEquals(false,this.testPlayer.isExtinct());

        this.testPlayer.killCell();

        System.setIn(new ByteArrayInputStream("7,7".getBytes()));
        System.setOut(ps);

        this.testPlayer.killCell();
        assertEquals(true,this.testGrid.isExtinct("s"));
        assertEquals(false,this.testPlayer.isExtinct());

        this.testGrid.killCell(3,3);
        assertEquals(true,this.testPlayer.isExtinct());
    }

    @Test
    void getName() {
        assertEquals("Tester",this.testPlayer.getName());
    }

    @Test
    void getCamp() {
        assertEquals("b",this.testPlayer.getCamp());
        this.testPlayer.setCamp("a");
        assertEquals("a",this.testPlayer.getCamp());
    }
}