package org.ks.frogger.manager;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Holds highscore data
 *
 * @author Kevin Sapper 2011
 */
public class Highscore implements Comparable<Highscore> {

  private String name;

  private long highscore;

  public Highscore() {
  }

  /**
   * Creates a new Highscore
   *
   * @param name of the player
   * @param highscore of the player
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Highscore) {
      Highscore that = (Highscore) obj;
      EqualsBuilder builder = new EqualsBuilder();
      builder.append(this.highscore, that.highscore);
      return builder.isEquals();
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int compareTo(Highscore that) {
    if (this.equals(that)) {
      return 0;
    } else if (this.highscore > that.highscore) {
      return -1;
    } else {
      return 1;
    }
  }
}
