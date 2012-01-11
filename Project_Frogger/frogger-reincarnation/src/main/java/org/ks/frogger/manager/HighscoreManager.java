package org.ks.frogger.manager;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.ks.frogger.events.ScoreUpdate;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class HighscoreManager {

  private Highscore highScore;

  private List<Highscore> highscoreList;

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
   * Updates the current highscore.
   * @param maxTime max. time for level
   * @param timeElapsed elapsed time
   * @param level current level
   */
  public void updateHighscore(long maxTime, long timeElapsed, int level) {
    double timeFactor = (double) (maxTime - timeElapsed) / maxTime;
    double levelFactor = 0.1 * level;
    double multiplicator = 1000;

    highScore.setHighscore(highScore.getHighscore() + Math.round(
            timeFactor * levelFactor * multiplicator));
    scoreUpdateEvent.fire(highScore.getHighscore());
  }

  /**
   * Truncates to old highscore without submitting it to the highscore list
   */
  public void newHighscore() {
    highScore = new Highscore();
    highScore.setHighscore(0L);
    scoreUpdateEvent.fire(highScore.getHighscore());
  }

  /**
   * Submit the current highscore to the highscore list.
   * Does not create a new highscore.
   */
  public void submitHighscore(String name) {
    try {
      highScore.setName(name);
      highscoreList.add(highScore);
      loader.saveHighscores(highscoreList);
    } catch (IOException ex) {
      //@TODO:
    }
  }

  public long getHighScore() {
    return highScore.getHighscore();
  }

  public List<Highscore> getTopTen() {
    Collections.sort(highscoreList);
    return highscoreList.size() < 10 ? highscoreList.subList(0, highscoreList.
            size()) : highscoreList.subList(0, 10);
  }

  public boolean isInTopTen() {
    List<Highscore> topTen = getTopTen();
    return Long.compare(highScore.getHighscore(), topTen.get(topTen.size() - 1).
            getHighscore()) > 0;
  }
}
