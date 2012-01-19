package org.ks.frogger.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.ks.frogger.events.ScoreUpdate;
import org.ks.frogger.stages.StageManager;

/**
 * Manages the highscores of the game
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class HighscoreManager {

  @Inject
  private StageManager stageManager;

  private Highscore highscore;

  private Map<Integer, List<Highscore>> highscoreList;

  private HighscoreLoader loader;

  @Inject
  @ScoreUpdate
  private Event<Long> scoreUpdateEvent;

  @PostConstruct
  public void initialize() {
    try {
      loader = new HighscoreLoader();
      highscoreList = loader.loadHighscore();
    } catch (IOException ex) {
      //@TODO: implement failsafe
    }
  }

  /**
   * Updates the current highscore for survival mode.
   * @param maxTime max. time for level
   * @param timeElapsed elapsed time
   * @param level current level
   */
  public void updateSurvivalHighscore(long maxTime, long timeElapsed, int level) {
    double timeFactor = (double) (maxTime - timeElapsed) / maxTime;
    double levelFactor = 0.1 * level;
    double multiplicator = 1000;

    highscore.setHighscore(highscore.getHighscore() + Math.round(
            timeFactor * levelFactor * multiplicator));
    scoreUpdateEvent.fire(highscore.getHighscore());
  }

  /**
   * Updates the current highscore for time mode.
   * @param maxTime max. time for level
   * @param timeElapsed elapsed time
   * @param level current level
   */
  public void updateTimeHighscore() {
    //increment highscore
    highscore.setHighscore(highscore.getHighscore() + 1);
    scoreUpdateEvent.fire(highscore.getHighscore());
  }

  /**
   * Truncates to old highscore without submitting it to the highscore list
   */
  public void newHighscore() {
    highscore = new Highscore();
    highscore.setHighscore(0L);
    scoreUpdateEvent.fire(highscore.getHighscore());
  }

  /**
   * Submit the current highscore to the highscore list.
   * Does not create a new highscore.
   */
  public void submitHighscore(String name) {
    try {
      Integer stageNo = stageManager.getCurrentStage().
              getStageNo();
      highscore.setName(name);
      // add score to list
      if (highscoreList.containsKey(stageNo)) {
        highscoreList.get(stageNo).
                add(highscore);
      } else {
        List<Highscore> scores = new ArrayList<>();
        scores.add(highscore);
        highscoreList.put(stageNo, scores);
      }
      loader.saveHighscores(highscoreList);
    } catch (IOException ex) {
      //@TODO:
    }
  }

  public long getHighScore() {
    return highscore.getHighscore();
  }

  /**
   * Get the top ten list for a stage.
   * @param stageNo the stage to get the top ten list for
   * @return the top ten list or null
   */
  public List<Highscore> getTopTen(int stageNo) {
    if(highscoreList.isEmpty()) {
      return null;
    }
    List<Highscore> topTenList = highscoreList.get(stageNo);
    Collections.sort(topTenList);
      return topTenList.size() < 10 ? topTenList.subList(0,
            topTenList.size()) : topTenList.subList(0, 10);
  }

  /**
   * Is the current score in the top ten list?
   * @param stageNo the stage no
   * @return true if in top ten, else false
   */
  public boolean isInTopTen(int stageNo) {
    if (highscoreList.isEmpty() || highscoreList.size() < 10) {
      return true;
    }
    List<Highscore> topTenList = getTopTen(stageNo);
    return Long.compare(highscore.getHighscore(),
            topTenList.get(topTenList.size() - 1).
            getHighscore()) > 0;
  }
}
