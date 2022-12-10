package Cell;

import java.util.ArrayList;

public class EdgeCell extends Cell{

    public EdgeCell(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex, 5);
    }

    @Override
    public ArrayList findNeighbors(int rowSize, int columnSize) {
        ArrayList<int[]> neighbors = new ArrayList<>();
        for(int i = this.getRowIndex()-1; i < this.getRowIndex()+2; i++){
            if(i >= 0 && i < rowSize){
                for(int j = this.getColumnIndex()-1; j < this.getColumnIndex()+2; j++){
                    if(j >= 0 && j < columnSize && (i != this.getRowIndex() | j != this.getColumnIndex())){
                        //can not be the neighbor of self
                        neighbors.add(new int[]{i,j});
                    }
                }
            }
        }
        assert neighbors.size() == 5;
        return neighbors;
    }

}