import Cell.Grid;
import Player.Player;
import UI.TerminalUI;

public class Game {
    private final Grid grid;
    private final Player[] players;
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
        String camp2 = this.players[1].getCamp();
        OpeningBoard.gambit(this.grid,camp1,camp2,1);
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
            TerminalUI.nextGeneration(this.grid);
            this.generation++;
            System.out.println("Current generation: "+this.generation);
            for (Player player : this.players){
                System.out.println(player.getName()+" have "+player.getCellNum()+" alive cells.");
            }
            for (Player player : this.players) {
                if (this.nextTurn && player.isExtinct()) {
                    TerminalUI.printBoard(this.grid);
                    System.out.println("Game over: " + player.getName() + " loses!");
                    this.nextTurn = false;
                }
            }
        }
    }
}