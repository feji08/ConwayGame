package Player;
import Cell.Grid;
import UI.TerminalUI;

public class Player implements IPlayer{
    private final Grid grid;
    private final String name;
    private String camp;

    public Player(Grid grid,String name,String camp){
        this.grid = grid;
        this.name = name;
        this.camp = camp;
    }

    public void killCell() {
        TerminalUI.chooseKillCell(this.grid,this.getCamp());
    }

    public void activateCell() {
        TerminalUI.chooseActivateCell(this.grid,this.getCamp());
    }

    public int getCellNum() {
        return this.grid.getCellNum(this.camp);
    }

    public boolean isExtinct() {
        return this.grid.isExtinct(this.camp);
    }
    public String getName() {
        return name;
    }
    public void setCamp(String camp) {
        this.camp = camp;
    }

    public String getCamp() {
        return camp;
    }
}
