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
    public static String chooseSymbol(String player) {
        System.out.println(player+", please select your symbol: ");
        System.out.print("A.\033[32m"+"#"+"\033[0m"+String.join("", Collections.nCopies(8, " ")));
        System.out.println("B.\033[31m"+"#"+"\033[0m");
        String pattern = "[AaBb]";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! ");
            return TerminalUI.chooseSymbol(player);
        }else {
            if (Pattern.matches("[Aa]", input)) {
                return "\033[32m" + "#" + "\033[0m";
            } else {
                {
                    return "\033[31m" + "#" + "\033[0m";
                }
            }
        }
    }

    public static int[] chooseKillCell(int rowSize, int columnSize) {
        System.out.println("Please choose an enemy cell to kill (for example: 10,10): ");
        String pattern = "[0-9]+,[0-9]+";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: raw index comma column index. ");
            return TerminalUI.chooseKillCell(rowSize, columnSize);
        }else{
            String[] sizeString = input.split(",",2);
            int r = Integer.parseInt(sizeString[0]);
            int c = Integer.parseInt(sizeString[1]);
            if(r >= rowSize | c >= columnSize){
                System.out.println("Invalid input! HINT: out of index. ");
                return TerminalUI.chooseKillCell(rowSize, columnSize);
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
        String pattern = "[0-9]+,[0-9]+";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(!Pattern.matches(pattern,input)){
            System.out.println("Invalid input! HINT: raw index comma column index. ");
            return TerminalUI.chooseActivateCell(rowSize, columnSize);
        }else{
            String[] sizeString = input.split(",",2);
            int r = Integer.parseInt(sizeString[0]);
            int c = Integer.parseInt(sizeString[1]);
            if(r >= rowSize | c >= columnSize){
                System.out.println("Invalid input! HINT: out of index. ");
                return TerminalUI.chooseActivateCell(rowSize, columnSize);
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