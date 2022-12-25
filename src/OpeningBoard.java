import Cell.Grid;
public class OpeningBoard {
    public static void gambit(Grid grid, String camp1, String camp2, int pattern){
        if(pattern == 1){
            grid.activateCell(camp1,2,2);
            grid.activateCell(camp1,2,3);
            grid.activateCell(camp1,2,4);
            grid.activateCell(camp1,3,2);
            grid.activateCell(camp2,3,3);
            grid.activateCell(camp1,3,4);
            grid.activateCell(camp1,4,2);
            grid.activateCell(camp1,4,3);
            grid.activateCell(camp1,4,4);

            grid.activateCell(camp2,7,7);
            grid.activateCell(camp2,7,8);
            grid.activateCell(camp2,7,9);
            grid.activateCell(camp2,8,7);
            grid.activateCell(camp1,8,8);
            grid.activateCell(camp2,8,9);
            grid.activateCell(camp2,9,7);
            grid.activateCell(camp2,9,8);
            grid.activateCell(camp2,9,9);
        }
        if(pattern == 2){

        }
        if(pattern == 3){

        }
        if(pattern == 4){

        }
        if(pattern == 5){

        }
    }
}
