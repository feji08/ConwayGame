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
        int[] crd = TerminalUI.chooseKillCell(this.grid.getRowSize(),this.grid.getColumnSize());
        int r = crd[0];
        int c = crd[1];
        //alive validation
        if(!grid.isAlive(r,c)){
            System.out.println("Invalid input! HINT: this cell is not alive. ");
            this.killCell();
        }else{
            if(grid.getCamp(r,c).equals(this.camp)){//alive and belong to the player's self
                System.out.println("Invalid input! HINT: this cell is yours"+"("+this.camp+").");
                this.killCell();
            }else{this.grid.killCell(r,c);}
        }
    }

    public void activateCell() {
        int[] crd = TerminalUI.chooseActivateCell(this.grid.getRowSize(),this.grid.getColumnSize());
        int r = crd[0];
        int c = crd[1];
        if(grid.isAlive(r,c)){
            System.out.println("Invalid input! HINT: this cell is already alive. ");
            this.activateCell();
        }else{
            this.grid.activateCell(this.camp,r,c);
        }
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
