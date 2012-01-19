package org.ks.frogger.manager;

import com.google.common.base.Stopwatch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.swing.Timer;
import org.ks.frogger.ActionCommand;
import org.ks.frogger.events.FroggerDeath;
import org.ks.frogger.events.TimeData;
import org.ks.frogger.events.TimeOut;
import org.ks.frogger.events.TimeUpdate;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class TimeManager implements ActionListener {

    private final long delay = 30000;

    private final long minDelay = 5000;

    private long levelDelay = delay;

    @Inject
    @TimeUpdate
    private Event<TimeData> timeUpdateEvent;

    @Inject
    @TimeOut
    private Event<FroggerDeath> deathEvent;

    private Timer timer;

    private Timer timeUpdateTimer;

    private Stopwatch stopwatch;

    @PostConstruct
    public void initialize() {
        stopwatch = new Stopwatch();

        timer = new Timer((int) delay, this);
        timer.setActionCommand(ActionCommand.TIMEUP);

        timeUpdateTimer = new Timer(1000, this);
        timeUpdateTimer.setInitialDelay(0);
        timeUpdateTimer.setActionCommand(ActionCommand.TIMEUPDATE);
    }

    
    public void startTimer(long triggerDelay) {
     //@TODO: 
    }
    
    /**
     * 
     * @param actionListener
     * @param level 
     */
    public void startTimer(int level) {
        if (!timer.isRunning()) {
            updateLevelDelay(level);

            timer.setInitialDelay((int) levelDelay);
            timer.setDelay((int) levelDelay);

            timer.start();
            timeUpdateTimer.start();
            stopwatch.start();
        }
    }

    /**
     * 
     */
    public void restartTimer() {
        if (timer != null && timer.isRunning()) {
            stopwatch.reset();

            timer.restart();
            timeUpdateTimer.restart();

            stopwatch.start();
        }
    }

    /**
     * 
     * @param currentLevel 
     */
    public void restartTimer(int currentLevel) {
        if (timer != null && timer.isRunning()) {
            stopwatch.reset();

            updateLevelDelay(currentLevel);

            timer.stop();
            timer.setDelay((int) levelDelay);
            timer.setInitialDelay((int) levelDelay);
            timer.start();
            timeUpdateTimer.restart();

            stopwatch.start();
        }
    }

    /**
     * 
     */
    public void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
            timeUpdateTimer.stop();
            stopwatch.stop();
        }
    }

    /**
     * Get the elapsed time since timer start
     * @return the elapsed time in seconds
     */
    public long getElapsedSeconds() {
        return stopwatch.elapsedTime(TimeUnit.SECONDS);
    }

    /**
     * Gets the remaining time until the timer trigger again
     * @return the remaining time in seconds
     */
    public long getRemainingTime() {
        return millisToSeconds(levelDelay) - stopwatch.elapsedTime(TimeUnit.SECONDS);
    }

    /**
     * Get the current level trigger delay.
     * @return level deplay in seconds.
     */
    public long getLevelDelay() {
        return millisToSeconds(levelDelay);
    }

    private void updateLevelDelay(int level) {
        if (levelDelay > minDelay) {
            levelDelay = delay - (level - 1 * 1000);
        } else {
            levelDelay = minDelay;
        }
    }

    private long millisToSeconds(long millis) {
        return millis / 1000;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case ActionCommand.TIMEUP:
                deathEvent.fire(new FroggerDeath(FroggerDeath.TIMEUP));
                break;
            case ActionCommand.TIMEUPDATE:
                timeUpdateEvent.fire(new TimeData(getLevelDelay(),getRemainingTime()));
                break;
        }
    }
}
