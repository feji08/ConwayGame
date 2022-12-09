package Cell;
import Cell.Cell;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid implements IGrid{
    //also need cells of different players
    private Cell[][] cells;
    private int rowSize;
    private int columnSize;

    public Grid(int[] size){
        rowSize = size[0];
        columnSize = size[1];
        //create new cells
        this.cells = new Cell[rowSize][columnSize];
        //normal cells
        for(int i = 1; i < rowSize-1; i ++){
            for(int j = 1; j < columnSize-1; j++){
                this.cells[i][j] = new NormalCell(i,j);
            }
        }
        ////edge cells
        //column
        for(int i = 1; i < rowSize-1; i ++){
            this.cells[i][0] = new NormalCell(i,0);
            this.cells[i][columnSize-1] = new NormalCell(i,columnSize-1);
        }
        //row
        for(int j = 1; j < columnSize-1; j++){
            this.cells[0][j] = new NormalCell(0,j);
            this.cells[rowSize-1][j] = new NormalCell(rowSize-1,j);
        }
        //corner cells
        this.cells[0][0] = new CornerCell(0,0);
        this.cells[0][columnSize-1] = new CornerCell(0,columnSize-1);
        this.cells[rowSize-1][columnSize-1] = new CornerCell(rowSize-1,columnSize-1);
        this.cells[rowSize-1][0] = new CornerCell(rowSize-1,0);
        //assign neighbors to cells
        for(int i = 0; i < rowSize; i ++){
            for(int j = 0; j < columnSize; j++){
                Iterator neighborsCrd = this.cells[i][j].findNeighbors();
                ArrayList<Cell> neighbors = new ArrayList<>();
                while(neighborsCrd.hasNext()){
                    int[] crd = (int[]) neighborsCrd.next();
                    neighbors.add(this.cells[crd[0]][crd[1]]);
                }
                Iterator iNeighbors = neighbors.iterator();
                this.cells[i][j].assignNeighbors(iNeighbors);
            }
        }
    }

    public boolean isAlive(int rowIndex, int columnIndex) {
        return cells[rowIndex][columnIndex].isAlive();
    }

    public void activateCell(String camp, int rowIndex, int columnIndex){
        cells[rowIndex][columnIndex].bornInCamp(camp);
    }

    public void killCell(int rowIndex, int columnIndex) {
        cells[rowIndex][columnIndex].takeMurder();
    }

    public void generate() {
        for(Cell[] cells: this.cells) {
            for (Cell cell : cells) {
                cell.predict();
            }
        }
        for(Cell[] cells: this.cells) {
            for (Cell cell : cells) {
                cell.generate();
            }
        }
    }

    public boolean isExtinct(String camp){
        for(Cell[] cells: this.cells) {
            for (Cell cell : cells) {
                if(cell.isAlive()){
                    return false;
                }
            }
        }
        return true;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
