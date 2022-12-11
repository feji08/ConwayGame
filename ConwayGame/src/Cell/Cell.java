package Cell;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Cell implements ICell {

    //child classes differ in
    //this.neighbors: number of neighbors

    //private symbol/color/
    //owner/group/camp
    private final int rowIndex;
    private final int columnIndex;
    private boolean alive;
    private boolean predictAlter;
    private boolean predictFlag;//indicating if a prediction is done after last generation
    private String camp;

    private String predictCamp;
    private Cell[] neighbors;
    private final int neighborSize;

    public Cell(int rowIndex, int columnIndex,int neighborSize){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.alive = false;
        this.predictAlter = false;
        this.camp = " ";
        this.neighborSize = neighborSize;
        this.neighbors = new Cell[neighborSize];
        this.predictCamp = this.camp;
    }
    public ArrayList findNeighbors(int rowSize, int columnSize){
        //should be overridden
        return null;
    }
    public void assignNeighbors(ArrayList neighbors){
        for(int i = 0; i < this.neighborSize; i++){
            this.neighbors[i] = (Cell) neighbors.get(i);
        }
    }

    public void bornInCamp(String camp){
        //The player can set a cell alive
        //ONLY when the cell is dead
        assert !this.isAlive();
        this.alive = true;
        this.camp = camp;
    }

    public void takeMurder() {
        assert this.isAlive();
        this.alive = false;
        this.camp = " ";
    }

    public void predict(){
        //predict the state of next generation
        //BUT WITHOUT updating the REAL state, because it will influence other cells
        assert !this.predictFlag;
        this.predictAlter = false;//default stay
        this.predictCamp = this.camp;
        int num = numAliveNeighbors();
        if(!this.alive && num == 3) {
                this.predictAlter = true;
                this.predictCamp = dominantNeighborCamp();
            }//reborn
        else if(this.alive && (num<2 | num>3)){
                this.predictAlter = true;
                this.predictCamp = " ";
            }//die
        this.predictFlag = true;
    }

    public void generate(){
        //update the prediction to REAL state
        //should only be updated after all cells predicted
        assert this.predictFlag;
        if(this.predictAlter) {
            if (this.alive) {die();} else {born();}//alter state
        }
        this.camp = this.predictCamp;
        this.predictFlag = false;
    }

    public boolean isAlive() {
        return alive;
    }
    public String getCamp() {
        return camp;
    }
    public int getRowIndex() {
        return rowIndex;
    }
    public int getColumnIndex() {
        return columnIndex;
    }

    private void born(){
        //check colors around
        this.alive = true;
    }

    private void die(){
        this.alive = false;
    }
    private String dominantNeighborCamp(){
        assert numAliveNeighbors() == 3;
        //record the camps
        ArrayList<Cell> aliveNeighbors = aliveNeighbors();
        String[] camp = new String[3];
        for(int i = 0; i < 3; i++){
            camp[i] = aliveNeighbors.get(i).getCamp();
            }
        //compare
        if(camp[0]==camp[1]){
            return camp[0];
        }else{return camp[3];}
    }
    public int numAliveNeighbors(){
        //return the number of alive neighbors
        //can be used for test
        int num = 0;
        for(Cell cell:this.neighbors){
            if(cell.isAlive()){
                num += 1;
            }
        }
        return num;
    }

    private ArrayList<Cell> aliveNeighbors(){
        ArrayList<Cell> aliveNeighbors = new ArrayList<>();
        for(Cell cell:this.neighbors){
            if(cell.isAlive()) {
                aliveNeighbors.add(cell);
            }
        }
        return aliveNeighbors;
    }
}
