package org.ks.frogger.manager;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Highscore {

  private String name;

  private long highscore;

  public Highscore() {
  }

  public Highscore(String name, long highscore) {
    this.name = name;
    this.highscore = highscore;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getHighscore() {
    return highscore;
  }

  public void setHighscore(long highscore) {
    this.highscore = highscore;
  }
}
