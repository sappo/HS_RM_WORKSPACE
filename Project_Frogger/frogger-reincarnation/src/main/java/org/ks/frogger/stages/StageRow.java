package org.ks.frogger.stages;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 *
 * @author Kevin Sapper 2011
 */
public class StageRow {

  private int rowLevel;

  private int gameObjectAcceleration;

  private long lastObjectAdded;

  public StageRow(int rowLevel, int gameObjectAcceleration) {
    this.rowLevel = rowLevel;
    this.gameObjectAcceleration = gameObjectAcceleration;
    this.lastObjectAdded = 0;
  }

  public int getRowLevel() {
    return rowLevel;
  }

  public int getGameObjectAcceleration() {
    return gameObjectAcceleration;
  }

  public long getLastObjectAdded() {
    return lastObjectAdded;
  }

  public void setLastObjectAdded(long lastObjectAdded) {
    this.lastObjectAdded = lastObjectAdded;
  }

  @Override
  public boolean equals(Object obj) {
    EqualsBuilder builder = new EqualsBuilder();
    if (obj instanceof StageRow) {
      StageRow that = (StageRow) obj;
      builder.append(this.rowLevel, that.rowLevel);
    }
    return builder.isEquals();
  }
}
