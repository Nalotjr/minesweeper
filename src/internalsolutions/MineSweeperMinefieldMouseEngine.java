package internalsolutions;

import userinterface.MineSweeperMinefield;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class used for mouse event processing.
 * Created by AT on 18.08.2015.
 */
public class MineSweeperMinefieldMouseEngine implements MouseListener {

    private MineSweeperMinefield parent;

    /**
     * Constructor of class
     * @param parent - instance of MineSweeperMinefield class
     */
    public MineSweeperMinefieldMouseEngine (MineSweeperMinefield parent) {

        this.parent = parent;
    }

    /**
     * Method work out mouse key pressing on MineSweeperMinefieldCell object.
     * @param e - MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {

        // Restarting game if it was finished
        if(parent.getGameFinished()) {
            parent.MineSweeperMinefieldReset();
            return;
            }

        MineSweeperMinefieldCell clickedMineFieldCell = (MineSweeperMinefieldCell) e.getSource();
        if(!parent.getGameTimer()) parent.setGameTimer(true);
        if(e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3){
               parent.getMinefieldInterface().MineSweeperMinefieldFlagSet(clickedMineFieldCell);
        } else {
            if(e.getButton() == MouseEvent.BUTTON1) {
                parent.getMinefieldInterface().mineSweeperCheckCell(clickedMineFieldCell);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
