package Cell;

public interface IGrid {
    /**
     * can only be killed when it is alive
     * can only be activated when it is dead
     */
    boolean isAlive(int rowIndex, int columnIndex);//check if a cell is alive

    String getCamp(int rowIndex, int columnIndex);//get a cell's camp
    void killCell(int rowIndex, int columnIndex);//kill a cell
    void activateCell(String camp, int rowIndex, int columnIndex);//player activate
    void generate();//new generation
    int getCellNum(String camp);//return the number of cell of this camp
    boolean isExtinct(String camp);//check if one side die out
}