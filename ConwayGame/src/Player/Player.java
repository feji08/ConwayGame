package Player;
import Cell.Grid;
import UI.TerminalUI;

public class Player implements IPlayer{
    private Grid grid;
    private String name;
    private String camp;

    public Player(Grid grid,String name,String camp){
        this.grid = grid;
        this.name = name;
        this.camp = camp;
    }

    public void killCell() {
        int[] crd = TerminalUI.chooseKillCell(this.grid.getRowSize(),this.grid.getColumnSize());
        int r = crd[0];
        int c = crd[1];
        //alive validation
        while(!grid.isAlive(r,c)){
            System.out.println("Invalid input! HINT: this cell is not alive. ");
            TerminalUI.chooseKillCell(this.grid.getRowSize(),this.grid.getColumnSize());
        }
        this.grid.killCell(r,c);
    }

    public void activateCell() {
        int[] crd = TerminalUI.chooseActivateCell(this.grid.getRowSize(),this.grid.getColumnSize());
        int r = crd[0];
        int c = crd[1];
        while(grid.isAlive(0,0)){
            System.out.println("Invalid input! HINT: this cell is already alive. ");
            TerminalUI.chooseActivateCell(this.grid.getRowSize(),this.grid.getColumnSize());
        }
        this.grid.activateCell(this.camp,0,0);
    }

    public boolean isExtinct() {
        if(this.grid.isExtinct(this.camp)){
            return true;
        }else{return false;}
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
