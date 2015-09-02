package internalsolutions;

import userinterface.MineSweeperMinefield;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class constructor fills minefield by mines (using math.random method) and after that set up all fields of each cell
 * at proper status (according to mines positions). After initialization, class checks cells, chosen by player
 * (including checking for victory/ lost conditions), and sets/removes flag from cells.
 *
 * Created by AT on 18.08.2015.
 */
public class MineSweeperMinefieldInterface {

    /** Variable minesAtField used to contain quantity of mines, that should be on field*/
    private Integer minesAtField;

    /** Variable flagsForSetUp used to contain quantity of flags which is available to place (equal to minesAtField
     * after class initialization) */
    private Integer flagsForSetUp;

    /** List minefieldCells used to contain all minefield cells*/
    private ArrayList<MineSweeperMinefieldCell> minefieldCells;

    /** Variable parent used to contain link to MineSweeperMinefield class instance, which created instance of
     * this class*/
    private MineSweeperMinefield parent;

    /**
     * Method fills minefield by mines (using Math.random method) and after that set up all fields of each cell
     * at proper status (according to mines positions)
     * @param parent (contain link to MineSweeperMinefield class instance, which created instance of this class)
     */
    public MineSweeperMinefieldInterface(MineSweeperMinefield parent) {

        this.parent = parent;
        minefieldCells = parent.getMinefieldCells();
        minesAtField = parent.getMinesAtField();
        flagsForSetUp = minesAtField;

        // Filling minefield with mines using  Math.random method
        int numberOfMinesInstalled = 0;
        do {
            int randomPosition = (int) (Math.random() * minefieldCells.size());

            if (!minefieldCells.get(randomPosition).isMineLocatedAtCell()) {
                minefieldCells.get(randomPosition).setMineLocatedAtCell(true);
                minefieldCells.get(randomPosition).setDisabledIcon(parent.getIcon(10));
                numberOfMinesInstalled++;
            }
        } while (numberOfMinesInstalled < minesAtField);

        // Filling all cells of minefield with lists of neighbor cells and quantity of mines, placed around.
        Integer fieldXSize = parent.getFieldXSize();
        Integer fieldYSize = parent.getFieldYSize();
        for (int i = 0; i < fieldYSize; i++)
            for (int j = 0; j < fieldXSize; j++) {
                int mineCounter = 0;
                for (int k = i - 1; k < i + 2; k++) {
                    if (k < 0 || k >= fieldYSize) continue;
                    for (int l = j - 1; l < j + 2; l++) {
                        if (l < 0 || l >= fieldXSize || (k == i && l == j)) continue;
                        if (minefieldCells.get(k * fieldXSize + l).isMineLocatedAtCell()) mineCounter++;
                        minefieldCells.get(i * fieldXSize + j).getNeighborCells().add(minefieldCells.get
                                (k * fieldXSize + l));
                    }
                }
                minefieldCells.get(i * fieldXSize + j).setMinesLocatedAround(mineCounter);
                if (!minefieldCells.get(i * fieldXSize + j).isMineLocatedAtCell())
                    minefieldCells.get(i * fieldXSize + j).setDisabledIcon(parent.getIcon(mineCounter));
            }
    }

    /**
     * Method checks cells, chosen by player (including checking for victory/ lost conditions)
     * @param selectedCell (instance of MineSweeperMinefieldCell class)
     * @return result of checking ("true" if cell was unchecked and without flag in it, "false" at all other cases)
     */
    public boolean mineSweeperCheckCell(MineSweeperMinefieldCell selectedCell) {

        // Checking is cell already checked or have a flag on it.
        if (selectedCell.isFlagSetUpAt() || selectedCell.isCellChecked()) return false;

        // Checking is cell contain mine. If contains, launches game lost scenario.
        if (selectedCell.isMineLocatedAtCell()) {
            parent.setGameFinished(true);
            parent.setGameTimer(false);
            for (MineSweeperMinefieldCell x : minefieldCells) {
                if (x.isMineLocatedAtCell()) x.setEnabled(false);
            }
            selectedCell.setDisabledIcon(parent.getIcon(14));
            return true;
        }

        // Checking, if selected cell not contain mine and have some mines at cells around.
        if (selectedCell.getMinesLocatedAround() != 0) {
            selectedCell.setEnabled(false);
            selectedCell.setCellChecked(true);
        }
        // Checking, if selected cell not contain mine and haven't mines at cells around.
        else {

            // Creating list for cells with zero mines around, which should be automatically checked
            LinkedList<MineSweeperMinefieldCell> zeroCellList = new LinkedList<MineSweeperMinefieldCell>();

            zeroCellList.add(selectedCell);
            int counter = 0;
            do {
                MineSweeperMinefieldCell currentCell = zeroCellList.get(0);
                zeroCellList.remove(currentCell);
                currentCell.setEnabled(false);
                currentCell.setCellChecked(true);

                for(MineSweeperMinefieldCell x : currentCell.getNeighborCells())
                    if (!x.isCellChecked())
                        if(x.getMinesLocatedAround() == 0) zeroCellList.add(x);
                        else {
                            x.setEnabled(false);
                            x.setCellChecked(true);
                        }
            } while (zeroCellList.size() > 0);
        }

        // Counting number of checked cells
        int cellsChecked = 0;
        for(MineSweeperMinefieldCell x : minefieldCells) {
            if (x.isCellChecked()) cellsChecked++;
        }

        // Checking game victory condition (number of checked cells should be equal to number of cells on minefield
        // minus the number of mines
        if (cellsChecked == minefieldCells.size() - minesAtField) {
            parent.setGameFinished(true);
            parent.setGameTimer(false);
            parent.resetFlagsCounter();

            for(MineSweeperMinefieldCell x : minefieldCells)
                if (x.isMineLocatedAtCell()) {
                    x.setDisabledIcon(parent.getIcon(9));
                    x.setEnabled(false);
                }

            parent.MineSweeperMinefieldCheckHighScores();
        }
        return true;
    }

    /**
     * Method set/remove flag from selected cell (if cell not checked yet)
     * @param selectedCell (instance of MineSweeperMinefieldCell class)
     */
    public void MineSweeperMinefieldFlagSet(MineSweeperMinefieldCell selectedCell) {

        if (selectedCell.isCellChecked()) return;
        if (selectedCell.isFlagSetUpAt()) {
            selectedCell.setFlagSetUpAt(false);
            selectedCell.setIcon(parent.getIcon(11));
            flagsForSetUp++;
            parent.setFlagsNubberAtLabel(flagsForSetUp);
        } else {
            if(flagsForSetUp != 0) {
                selectedCell.setFlagSetUpAt(true);
                selectedCell.setIcon(parent.getIcon(9));
                flagsForSetUp--;
                parent.setFlagsNubberAtLabel(flagsForSetUp);
            }
        }
    }
}

