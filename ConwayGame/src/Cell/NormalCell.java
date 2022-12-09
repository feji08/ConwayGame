package Cell;

import java.util.ArrayList;
import java.util.Iterator;

public class NormalCell extends Cell{
    public NormalCell(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex);
    }

    @Override
    public Iterator findNeighbors() {
        this.neighbors = new Cell[8];
        ArrayList<int[]> neighbors = new ArrayList<>();
        for(int i = this.getRowIndex()-1; i < this.getRowIndex()+1; i++){
            for(int j = this.getColumnIndex()-1; j < this.getColumnIndex()+1; j++){
                if(i > 0 && j > 0 && (i != this.getRowIndex() | j != this.getColumnIndex())){
                    //can not be the neighbor of self
                    neighbors.add(new int[]{i,j});
                }
            }
        }
        assert neighbors.size() == 8;
        Iterator neighborsCrd = neighbors.iterator();
        return neighborsCrd;
    }

}
