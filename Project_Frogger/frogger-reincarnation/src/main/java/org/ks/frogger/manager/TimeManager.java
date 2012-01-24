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
 * Manages the game time.
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
    timer.setActionCommand(ActionCommand.TIMEUP.getCommand());

    timeUpdateTimer = new Timer(1000, this);
    timeUpdateTimer.setInitialDelay(0);
    timeUpdateTimer.setActionCommand(ActionCommand.TIMEUPDATE.getCommand());
  }

  /**
   * Start the timer with a trigger delay
   * @param triggerDelay 
   */
  public void startTimer(long triggerDelay) {
    if (!timer.isRunning()) {
      timer.setInitialDelay((int) triggerDelay);
      timer.setDelay((int) triggerDelay);

      timer.start();
      timeUpdateTimer.start();
      stopwatch.start();
    }
  }

  /**
   * Start the timer by passing the current level
   * @param level current level
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
   * Restart the timer.
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
   * Restart the timer for a new level
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
   * Stop the timer.
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
    return millisToSeconds(timer.getDelay()) - stopwatch.elapsedTime(
            TimeUnit.SECONDS);
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    switch (ActionCommand.getActionCommand(event.getActionCommand())) {
      case TIMEUP:
        deathEvent.fire(new FroggerDeath(FroggerDeath.TIMEUP));
        break;
      case TIMEUPDATE:
        timeUpdateEvent.fire(new TimeData(millisToSeconds(timer.getDelay()),
                getRemainingTime()));
        break;
    }
  }
}
