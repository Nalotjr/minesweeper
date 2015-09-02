package internalsolutions;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class describes single cell of minefield.
 * Created by AT on 18.08.2015.
 */
public class MineSweeperMinefieldCell extends JButton{

    /** Variable mineLocatedAtCell shows mine presence at cell ("true" if cell contains mine, "false" if not*/
    private boolean mineLocatedAtCell = false;

    /** Variable minesLocatedAround contains quantity of mines, located around cell.*/
    private int minesLocatedAround = 0;

    /** Variable flagSetUpAt - shows if flag ("MINE") was set up at cell ("true" if flag was set up, "false" if not)*/
    private boolean flagSetUpAt = false;

    /** Variable cellChecked - shows was cell already checked or not ("true" if cell was checked, "false" if not)*/
    private boolean cellChecked = false;

    /** List neighborCells - contain links to all cells surrounding this one.*/
    private ArrayList<MineSweeperMinefieldCell> neighborCells = new ArrayList<>();

    public MineSweeperMinefieldCell (){
        super();
    }

    /** Getter for mineLocatedAtCell variable */
    public boolean isMineLocatedAtCell() {
        return mineLocatedAtCell;
    }

    /** Setter for mineLocatedAtCell variable */
    public void setMineLocatedAtCell(boolean mineLocatedAtCell) {
        this.mineLocatedAtCell = mineLocatedAtCell;
    }

    /** Getter for minesLocatedAround variable */
    public int getMinesLocatedAround() {
        return minesLocatedAround;
    }

    /** Setter for minesLocatedAround variable */
    public void setMinesLocatedAround(int minesLocatedAround) {
        this.minesLocatedAround = minesLocatedAround;
    }

    /** Getter for flagSetUpAt variable */
    public boolean isFlagSetUpAt() {
        return flagSetUpAt;
    }

    /** Setter for flagSetUpAt variable */
    public void setFlagSetUpAt(boolean flagSetUpAt) {
        this.flagSetUpAt = flagSetUpAt;
    }

    /** Getter for cellChecked variable */
    public boolean isCellChecked() {
        return cellChecked;
    }

    /** Setter for cellChecked variable */
    public void setCellChecked(boolean cellChecked) {
        this.cellChecked = cellChecked;
    }

    /** Getter for neighborCells list */
    public ArrayList<MineSweeperMinefieldCell> getNeighborCells() {
        return neighborCells;
    }

}
