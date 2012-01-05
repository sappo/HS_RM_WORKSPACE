package org.ks.frogger.manager;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class HighscoreManager {

  private long highScore = 0;

  public void updateHighscore(long maxTime, long timeElapsed, int level) {
    double timeFactor = (double) (maxTime - timeElapsed) / maxTime;
    double levelFactor = 0.1 * level;
    double multiplicator =  1000;
    
    highScore += Math.round(timeFactor * levelFactor * multiplicator);
    System.out.println("Highscore: " + highScore);
  }

  public void resetHighscore() {
    highScore = 0;
  }

  public long getHighScore() {
    return highScore;
  }
}
