package org.ks.frogger.manager;

import com.google.common.base.Stopwatch;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.swing.Timer;
import org.ks.frogger.ActionCommand;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class TimeManager {

  private final int delay = 30000;

  private final int minDelay = 5000;

  private int levelDelay = delay;

  private Timer timer;
  
  private Timer timeUpdateTimer;

  private Stopwatch stopwatch;

  @PostConstruct
  public void initialize() {
    stopwatch = new Stopwatch();
    
    timer = new Timer(delay, null);
    timer.setActionCommand(ActionCommand.TIMEUP);
    
    timeUpdateTimer = new Timer(1000, null);
    timeUpdateTimer.setInitialDelay(0);
    timeUpdateTimer.setActionCommand(ActionCommand.TIMEUPDATE);
  }

  public void addActionListener(ActionListener actionListener) {
    boolean addListener = true;
    for (ActionListener listener : timer.getActionListeners()) {
      if (listener.equals(actionListener)) {
        addListener = false;
      }
    }
    if (addListener) {
      timer.addActionListener(actionListener);
      timeUpdateTimer.addActionListener(actionListener);
    }
  }

  /**
   * 
   * @param actionListener
   * @param level 
   */
  public void startTimer(int level) {
    if (!timer.isRunning()) {
      updateLevelDelay(level);

      timer.setInitialDelay(levelDelay);
      timer.setDelay(levelDelay);
      
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
      timer.setDelay(levelDelay);
      timer.setInitialDelay(levelDelay);
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
}
