package Cell;
import java.util.ArrayList;

public class CornerCell extends Cell {

    public CornerCell(int rowIndex, int columnIndex) {
        super(rowIndex, columnIndex, 3);
    }

    @Override
    public ArrayList findNeighbors(int rowSize, int columnSize) {
        //rowSize = 10, maxIndex = 9
        ArrayList<int[]> neighbors = new ArrayList<>();
        for (int i = this.getRowIndex() - 1; i < this.getRowIndex() + 2; i++) {
            if (i >= 0 && i < rowSize) {
                for (int j = this.getColumnIndex() - 1; j < this.getColumnIndex() + 2; j++) {
                    if (j >= 0 && j < columnSize && (i != this.getRowIndex() | j != this.getColumnIndex())) {
                        //can not be the neighbor of self
                        neighbors.add(new int[]{i, j});
                    }
                }
            }
        }
        assert neighbors.size() == 3;
        return neighbors;
    }
}
