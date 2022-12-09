package Cell;

import java.util.Iterator;

public interface ICell {
    Iterator findNeighbors();//return neighbor's coordinates
    void assignNeighbors(Iterator neighbors);//assign neighborCells
    void bornInCamp(String playerName);//the player activate the cell
    void takeMurder();//the player kill the cell
    /**
     * must predict first, then generate
     */
    void predict();//predict the next generation
    void generate();//complement the generation AFTER prediction
    boolean isAlive();//return life state
    String getCamp();//return which camp it belongs to
    int getRowIndex();
    int getColumnIndex();
    int numAliveNeighbors();//for test
    @Override
    String toString();
}
