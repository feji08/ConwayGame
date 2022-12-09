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
        player1.setCamp(TerminalUI.chooseSymbol(player1.getName()));
        Player player2 = new Player(this.grid,TerminalUI.inputName("player2"),"#");
        player2.setCamp(TerminalUI.chooseSymbol(player2.getName()));
        //order by initial character
        if(player1.getName().compareToIgnoreCase(player2.getName()) < 0 ){
            this.players[0] = player1;
            this.players[1] = player2;
        }else{
            this.players[0] = player2;
            this.players[1] = player1;
        }
    }
    public void gameOn(){
        while(nextTurn = true) {
            Player currentPlayer = this.players[this.generation%2];
            TerminalUI.printBoard(this.grid);
            currentPlayer.killCell();
            TerminalUI.printBoard(this.grid);
            currentPlayer.activateCell();
            TerminalUI.printBoard(this.grid);
            this.grid.generate();
            this.generation++;
            TerminalUI.printBoard(this.grid);
            for (Player player : this.players) {
                if (grid.isExtinct(player.getCamp())) {
                    System.out.println("Game over: " + player.getName() + " loses!");
                    this.nextTurn = false;
                }
            }
        }

    }
}