package Cell;

import java.util.ArrayList;

public class NormalCell extends Cell{
    public NormalCell(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex, 8);
    }

    @Override
    public ArrayList findNeighbors(int rowSize, int columnSize) {
        ArrayList<int[]> neighbors = new ArrayList<>();
        for(int i = this.getRowIndex()-1; i < this.getRowIndex()+2; i++){
            for(int j = this.getColumnIndex()-1; j < this.getColumnIndex()+2; j++){
                if(i != this.getRowIndex() | j != this.getColumnIndex()){
                    //can not be the neighbor of self
                    neighbors.add(new int[]{i,j});
                }
            }
        }
        assert neighbors.size() == 8;
        return neighbors;
    }

}
