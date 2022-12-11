import Cell.Grid;
import Player.Player;
import UI.TerminalUI;

public class Game {
    private Grid grid;
    private Player[] players;
    private int generation;
    private boolean nextTurn;
    public Game(){
        this.grid = new Grid(TerminalUI.inputGridSize());
        this.players = new Player[2];
        this.generation = 0;
        this.nextTurn = true;
    }

    public void initialize(){
        Player player1 = new Player(this.grid,TerminalUI.inputName("player1"),"#");
        player1.setCamp(TerminalUI.chooseSymbol("", player1));
        String chosenCamp = player1.getCamp();
        Player player2 = new Player(this.grid,TerminalUI.inputName("player2"),"#");
        player2.setCamp(TerminalUI.chooseSymbol(chosenCamp, player2));
        //order by initial character
        if(player1.getName().compareToIgnoreCase(player2.getName()) < 0 ){
            this.players[0] = player1;
            this.players[1] = player2;
        }else{
            this.players[0] = player2;
            this.players[1] = player1;
        }
    }

    public void boardConfiguration(){
        String camp1 = this.players[0].getCamp();
        this.grid.activateCell(camp1,1,1);
        this.grid.activateCell(camp1,1,2);
        this.grid.activateCell(camp1,2,1);
        this.grid.activateCell(camp1,2,2);
        String camp2 = this.players[1].getCamp();
        this.grid.activateCell(camp2,7,7);
        this.grid.activateCell(camp2,7,8);
        this.grid.activateCell(camp2,8,7);
        this.grid.activateCell(camp2,8,8);
    }
    public void gameOn(){
        while(this.nextTurn) {
            Player currentPlayer = this.players[this.generation%2];
            TerminalUI.printBoard(this.grid);
            System.out.print(currentPlayer.getName()+"("+currentPlayer.getCamp()+"), ");
            currentPlayer.killCell();
            TerminalUI.printBoard(this.grid);
            System.out.print(currentPlayer.getName()+"("+currentPlayer.getCamp()+"), ");
            currentPlayer.activateCell();
            TerminalUI.printBoard(this.grid);
            this.grid.generate();
            this.generation++;
            for (Player player : this.players) {
                if (this.nextTurn && grid.isExtinct(player.getCamp())) {
                    TerminalUI.printBoard(this.grid);
                    System.out.println("Game over: " + player.getName() + " loses!");
                    this.nextTurn = false;
                }
            }
        }

    }
}