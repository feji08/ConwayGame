package UI;
import Cell.Grid;
import Cell.Cell;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TerminalUI{
    public static int[] inputGridSize() {
        System.out.println("Please input the board size (at least 10,10): ");
        String pattern = "[1-9][0-9]*,[1-9][0-9]*";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: raw size comma column size. ");
            TerminalUI.inputGridSize();
            return null;
        }else{
            String[] sizeString = input.split(",",2);
            int[] size = new int[2];
            size[0] = Integer.parseInt(sizeString[0]);
            size[1] = Integer.parseInt(sizeString[1]);
            return size;
        }
    }
    public static String inputName(String player) {
        System.out.println("Please input the name of "+player+" : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input;
    }
    public static String chooseSymbol(String player) {
        System.out.println(player+", please select your symbol: ");
        System.out.print("A.\033[32m"+"#"+"\033[0m"+String.join("", Collections.nCopies(8, " ")));
        System.out.println("B.\033[31m"+"#"+"\033[0m");
        String pattern = "[AaBb]";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! ");
            TerminalUI.chooseSymbol(player);
            return null;
        }else{
            return input;
        }
    }

    public static int[] chooseKillCell(int rowSize, int columnSize) {
        System.out.println("Please choose an enemy cell to kill (for example: 10,10): ");
        String pattern = "[1-9][0-9]*,[1-9][0-9]*";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: raw index comma column index. ");
            TerminalUI.chooseKillCell(rowSize, columnSize);
            return null;
        }else{
            String[] sizeString = input.split(",",2);
            int r = Integer.parseInt(sizeString[0]);
            int c = Integer.parseInt(sizeString[1]);
            if(r > rowSize | c > columnSize){
                System.out.println("Invalid input! HINT: out of index. ");
                TerminalUI.chooseKillCell(rowSize, columnSize);
                return null;
            }else{
                int[] crd = new int[2];
                crd[0] = r;
                crd[1] = c;
                return crd;
            }
        }
    }

    public static int[] chooseActivateCell(int rowSize, int columnSize) {
        System.out.println("Please choose a dead cell to activate (for example: 10,10): ");
        String pattern = "[1-9][0-9]*,[1-9][0-9]*";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: raw index comma column index. ");
            TerminalUI.chooseActivateCell(rowSize, columnSize);
            return null;
        }else{
            String[] sizeString = input.split(",",2);
            int r = Integer.parseInt(sizeString[0]);
            int c = Integer.parseInt(sizeString[1]);
            if(r > rowSize | c > columnSize){
                System.out.println("Invalid input! HINT: out of index. ");
                TerminalUI.chooseActivateCell(rowSize, columnSize);
                return null;
            }else{
                int[] crd = new int[2];
                crd[0] = r;
                crd[1] = c;
                return crd;
            }
        }
    }

    public static void printBoard(Grid grid){
        int width = grid.getColumnSize();
        System.out.println(String.join("", Collections.nCopies(width+2, "-")));
        Cell[][] cells = grid.getCells();
        for(Cell[] rows: cells){
            System.out.print("|");
            for(Cell cell: rows){
                System.out.print(cell);
            }
            System.out.println("|");
        }
        System.out.println(String.join("", Collections.nCopies(width+2, "-")));
    }
}