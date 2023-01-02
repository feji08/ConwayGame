package UI;

import Cell.Grid;
import Player.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TerminalUITest {

    private final InputStream stdin = System.in;

    @AfterEach
    public void restoreSystemIn() {
        System.setIn(stdin);
    }

    private void provideInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void inputGridSize() {
        provideInput("10,10");
        int[] res = new int[2];
        res = TerminalUI.inputGridSize();
        assertEquals(10,res[0]);
        assertEquals(10,res[1]);

    }

    @Test
    void inputName() {
        provideInput("ABC");
        String actualRes = TerminalUI.inputName("Tester");
        assertEquals("ABC",actualRes);
    }

    @Test
    void chooseSymbol() {
        Grid testGrid = new Grid(new int[]{10,10});
        Player testPlayer = new Player(testGrid,"Tester","b");
        provideInput("a");
        TerminalUI.chooseSymbol("ACDEF",testPlayer);
        provideInput("b");
        assertEquals(Symbol.B.getValue(),TerminalUI.chooseSymbol("ACDEF",testPlayer));

        provideInput("c");
        assertEquals(Symbol.C.getValue(),TerminalUI.chooseSymbol("ABDEF",testPlayer));

        provideInput("D");
        assertEquals(Symbol.D.getValue(),TerminalUI.chooseSymbol("ABCEF",testPlayer));

        provideInput("a");
        assertEquals(Symbol.A.getValue(),TerminalUI.chooseSymbol("ABDEF",testPlayer));
    }

    @Test
    void chooseKillCell() {
        provideInput("5,5");
        Grid testGrid = new Grid(new int[]{10,10});
        testGrid.activateCell("g",5,5);

        provideInput("5,5");
        assertEquals(true,testGrid.isAlive(5,5));
        TerminalUI.chooseKillCell(testGrid,"b");
        //TerminalUI.chooseKillCell(10,10);
        assertEquals(false,testGrid.isAlive(5,5));
    }

    @Test
    void chooseActivateCell() {
    }

    @Test
    void printBoard() {
        Grid testGrid = new Grid(new int[]{10,10});
        TerminalUI.printBoard(testGrid);
    }

    @Test
    void printMultipleTimes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);
        TerminalUI.printMultipleTimes("A",6);
        String outputText = byteArrayOutputStream.toString();
        String expectedRes = "AAAAAA\n";
        assertEquals(expectedRes,outputText.toString());
    }
}