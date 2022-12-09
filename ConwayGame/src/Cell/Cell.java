package Cell;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Cell implements ICell {

    //child classes differ in
    //this.neighbors: number of neighbors

    //private symbol/color/
    //owner/group/camp
    private int rowIndex;
    private int columnIndex;
    private boolean alive;
    private boolean predictAlive;
    private boolean predictFlag;//indicating if a prediction is done after last generation
    private String camp;
    Cell[] neighbors;

    public Cell(int rowIndex, int columnIndex){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.alive = false;
        this.predictAlive = true;
        this.camp = " ";
    }
    public Iterator findNeighbors(){
        //should be overridden
        this.neighbors = new Cell[8];
        return null;
    }
    public void assignNeighbors(Iterator iNeighbors){
        int i = 0;
        while(iNeighbors.hasNext()){
            this.neighbors[i] = (Cell) iNeighbors.next();
            i++;
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
        assert this.predictFlag == false;
        if(!this.alive && numAliveNeighbors() == 3){
            this.predictAlive = true;
        }else if(numAliveNeighbors() < 2 | numAliveNeighbors() > 3){
            this.predictAlive = false;
        }
        this.predictFlag = true;
    }

    public void generate(){
        //update the prediction to REAL state
        //should only be updated after all cells predicted
        assert this.predictFlag == true;
        if(this.alive != this.predictAlive) {
            if (this.alive) {die();} else {born();}//alter state
        }
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
        this.camp = dominantNeighborCamp();
    }

    private void die(){
        this.alive = false;
        this.camp = " ";
    }
    private String dominantNeighborCamp(){
        assert numAliveNeighbors() == 3;
        //record the frequency
        HashMap<String,Integer> neighborCamp = new HashMap<>();
        for(Cell cell:this.neighbors){
            String camp = cell.getCamp();
            if(!neighborCamp.containsKey(camp)){
                neighborCamp.put(camp,1);
            }else{neighborCamp.put(camp,neighborCamp.get(camp)+1);}
        }
        //compare
        for(Object k: neighborCamp.keySet()){
            if(neighborCamp.get(k) == 2 | neighborCamp.get(k) == 3){
                return (String) k;//must return a value here
            }
        }
        return null;
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

    @Override
    public String toString() {
        return this.camp;
    }
}
