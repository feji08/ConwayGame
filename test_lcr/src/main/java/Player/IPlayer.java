package Player;

public interface IPlayer {
    //kill and activate
    void killCell();
    void activateCell();
    int getCellNum();
    boolean isExtinct();
}
