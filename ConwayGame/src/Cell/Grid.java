package Cell;
import java.util.ArrayList;
public class Grid implements IGrid{
    //also need cells of different players
    private final Cell[][] cells;
    private final int rowSize;
    private final int columnSize;

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
        //(1,0),(1,max),(2,0),(2,max),(3,0),(3,max)...
        for(int i = 1; i < rowSize-1; i ++){
            this.cells[i][0] = new EdgeCell(i,0);
            this.cells[i][columnSize-1] = new EdgeCell(i,columnSize-1);
        }
        //row
        for(int j = 1; j < columnSize-1; j++){
            this.cells[0][j] = new EdgeCell(0,j);
            this.cells[rowSize-1][j] = new EdgeCell(rowSize-1,j);
        }
        //corner cells
        this.cells[0][0] = new CornerCell(0,0);
        this.cells[0][columnSize-1] = new CornerCell(0,columnSize-1);
        this.cells[rowSize-1][columnSize-1] = new CornerCell(rowSize-1,columnSize-1);
        this.cells[rowSize-1][0] = new CornerCell(rowSize-1,0);
        //assign neighbors to cells
        for(int i = 0; i < rowSize; i ++){
            for(int j = 0; j < columnSize; j++){
                ArrayList<int[]> neighborsCrd = this.cells[i][j].findNeighbors(this.rowSize,this.columnSize);
                ArrayList<Cell> neighbors = new ArrayList<>();
                for (int[] crd : neighborsCrd) {
                    neighbors.add(this.cells[crd[0]][crd[1]]);
                }
                this.cells[i][j].assignNeighbors(neighbors);
            }
        }
    }

    public boolean isAlive(int rowIndex, int columnIndex) {
        return cells[rowIndex][columnIndex].isAlive();
    }

    public String getCamp(int rowIndex, int columnIndex) {
        return cells[rowIndex][columnIndex].getCamp();
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
                if(cell.getCamp().equals(camp) && cell.isAlive()){
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
