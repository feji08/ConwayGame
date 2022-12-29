package UI;

import Cell.Cell;
import Cell.Grid;
import Player.Player;

import java.util.Scanner;
import java.util.regex.Pattern;

public class TerminalUI{
    public static int[] inputGridSize() {
        System.out.println("Please input the board size (at least 10,10): ");
        String pattern = "[1-9][0-9]*,[1-9][0-9]*";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: row size comma column size. ");
            return TerminalUI.inputGridSize();
        }else {
            String[] sizeString = input.split(",", 2);
            int rowSize = Integer.parseInt(sizeString[0]);
            int columnSize = Integer.parseInt(sizeString[1]);
            if ( rowSize < 10 | columnSize < 10) {
                System.out.println("Invalid input! HINT: at least 10,10. ");
                return TerminalUI.inputGridSize();
            } else {
                int[] size = new int[2];
                size[0] = rowSize;
                size[1] = columnSize;
                return size;
            }
        }
    }
    public static String inputName(String player) {
        System.out.println("Please input the name of "+player+" : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    public static String chooseSymbol(String chosenCamp, Player player) {
        System.out.println(player.getName()+", please select your symbol: ");
        for(int i = 0; i < 7; i++){ //7 symbol patterns
            char c = (char)(i+65);
            System.out.print(c);//A
            System.out.print("."+Symbol.valueOf(String.valueOf(c)).getValue());//.(Symbol.A.getValue())
            TerminalUI.printMultipleTimes(" ",8);
        }
        String pattern = "[ABCDEFG]";//7 symbol patterns
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().toUpperCase();//A/a ---> A
        if(!Pattern.matches(pattern,input)){ //invalid
            System.out.println("Invalid input! ");
            return TerminalUI.chooseSymbol(chosenCamp, player);
        }else { //valid: A B C D E F G
            if(Symbol.valueOf(input).getValue().equals(chosenCamp)){ //already chosen
                System.out.println("Invalid input! HINT: this symbol has been chosen!");
                return TerminalUI.chooseSymbol(chosenCamp, player);
            }else {
                return switch (input) {
                    case "A" -> String.valueOf(Symbol.A.getValue());
                    case "B" -> String.valueOf(Symbol.B.getValue());
                    case "C" -> String.valueOf(Symbol.C.getValue());
                    case "D" -> String.valueOf(Symbol.D.getValue());
                    case "E" -> String.valueOf(Symbol.E.getValue());
                    case "F" -> String.valueOf(Symbol.F.getValue());
                    default -> String.valueOf(Symbol.G.getValue());
                };
            }
        }
    }

    public static void chooseKillCell(Grid grid, String camp) {
        int rowSize = grid.getRowSize();
        int columnSize = grid.getColumnSize();
        System.out.println("Please choose an enemy cell to kill (for example: 10,10): ");
        String pattern = "[0-9]+,[0-9]+";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: row index comma column index. ");
            TerminalUI.chooseKillCell(grid, camp);
        }else{
            String[] sizeString = input.split(",",2);
            int r = Integer.parseInt(sizeString[0]);
            int c = Integer.parseInt(sizeString[1]);
            if(r >= rowSize | c >= columnSize){
                System.out.println("Invalid input! HINT: out of index. ");
                TerminalUI.chooseKillCell(grid, camp);
            }else{
                //alive validation
                if(!grid.isAlive(r,c)){
                    System.out.println("Invalid input! HINT: this cell is not alive. ");
                    TerminalUI.chooseKillCell(grid, camp);
                }else{
                    if(grid.getCamp(r,c).equals(camp)){//alive and belong to the player's self
                        System.out.println("Invalid input! HINT: this cell is yours"+"("+camp+").");
                        TerminalUI.chooseKillCell(grid, camp);
                    }else{grid.killCell(r,c);}
                }
            }
        }
    }

    public static void chooseActivateCell(Grid grid, String camp) {
        int rowSize = grid.getRowSize();
        int columnSize = grid.getColumnSize();
        System.out.println("Please choose a dead cell to activate (for example: 10,10): ");
        String pattern = "[0-9]+,[0-9]+";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: row index comma column index. ");
            TerminalUI.chooseActivateCell(grid, camp);
        }else{
            String[] sizeString = input.split(",",2);
            int r = Integer.parseInt(sizeString[0]);
            int c = Integer.parseInt(sizeString[1]);
            if(r >= rowSize | c >= columnSize){
                System.out.println("Invalid input! HINT: out of index. ");
                TerminalUI.chooseActivateCell(grid, camp);
            }else{
                if(grid.isAlive(r,c)){
                    System.out.println("Invalid input! HINT: this cell is already alive. ");
                    TerminalUI.chooseActivateCell(grid, camp);
                }else{
                    grid.activateCell(camp,r,c);
                }
            }
        }
    }

    public static void nextGeneration(Grid grid){
        System.out.println("Enter anything to confirm generation: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        grid.generate();
    }

    public static void printBoard(Grid grid){
        int width = grid.getColumnSize();
        TerminalUI.printMultipleTimes("-",width*2+1);
        Cell[][] cells = grid.getCells();
        int rowIdx = 0;
        for(Cell[] rows: cells){
            System.out.print("|");
            for(Cell cell: rows){
                System.out.print(cell.getCamp()+"|");
            }
            System.out.print(rowIdx+"\n");
            rowIdx ++;
        }
        TerminalUI.printMultipleTimes("-",width*2+1);
        for(int i = 0; i < width; i++){
            System.out.print(" "+i);
        }
        System.out.print("\n");
    }

    public static void printMultipleTimes(String string,int times){
        for(int i = 0; i < times; i++){
            System.out.print(string);
        }
        System.out.print("\n");
    }
}