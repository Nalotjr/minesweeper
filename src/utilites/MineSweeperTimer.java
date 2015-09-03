package utilites;

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * Class used for creating timer which counts and displays time of game at javax.swing object;
 * Created on 15.08.2015.
 * @version 1.5
 */
public class MineSweeperTimer {

    private boolean timerActive = true;
    private Integer timeCounter = 0;
    private Object object = null;
    private DecimalFormat format;

    /**
     * Launch timer which count timer ticks ang displays it at javax.swing object
     * @param timeCountStep (step of timer activation in milliseconds)
     * @param maxCount (maximal number of timer steps which could to be counted)
     * @param object (javax.swing object to display current value of counted time)
     * @param format (format for displayed value of timer's ticks)
     * @throws IllegalArgumentException (if timeCountStep below or equal to zero or maxCount below zero)
     */
    public  MineSweeperTimer (int timeCountStep, int maxCount, Object object, DecimalFormat format)
            throws IllegalArgumentException{

        Timer gameTimer;

        if (timeCountStep <= 0) throw new IllegalArgumentException("Parameter timeCountStep below or equal to zero");
        if (maxCount < 0) throw new IllegalArgumentException("Parameter maxCount below zero");

        this.object = object;
        this.format = format;

        if (object instanceof JLabel) ((JLabel) object).setText(format.format(timeCounter));
        if (object instanceof JButton) ((JButton) object).setText(format.format(timeCounter));

        gameTimer = new Timer(timeCountStep,new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (timerActive && (timeCounter < maxCount)) {
                    timeCounter++;
                    if (object instanceof JLabel) ((JLabel) object).setText(format.format(timeCounter));
                    if (object instanceof JButton) ((JButton) object).setText(format.format(timeCounter));
                }
            }
        });

        gameTimer.start();
    }

    /**
     * Method enable/disable counting and display game time for timer
     * @param timerActive ("true" to enable counting, "false" to disable)
     */
    public void setTimerActive (boolean timerActive) {
        this.timerActive = timerActive;
    }

    /**
     * Method returns current state of activity for timer.
     * @return timerActive ("true" if timer is active, "false" if not)
     */
    public boolean isTimerActive() {
        return timerActive;
    }

    /**
     * Method sets timer counter at 0 and displays this value at object, connected with timer.
     */
    public void resetTimer() {
        timeCounter = 0;
        if (object instanceof JLabel) ((JLabel) object).setText(format.format(timeCounter));
        if (object instanceof JButton) ((JButton) object).setText(format.format(timeCounter));
    }

    /**
     * Method returns quantity of timer's counted ticks.
     * @return timeCounter (counted ticks of timer)
     */
    public Integer getTimeCounter() {
        return timeCounter;
    }
}
