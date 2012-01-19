package org.ks.frogger.stages;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.ks.frogger.Helper.ImmobileGameObject;
import org.ks.frogger.Helper.MobileGameObject;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class StageRow {

  private int rowLevel;

  private int gameObjectAcceleration;

  private long lastObjectAdded;

  private MobileGameObject mobileGameObjectType;

  private ImmobileGameObject immobileGameObjectType;

  public StageRow(int rowLevel, int gameObjectAcceleration,
          MobileGameObject mobileGameObjectType,
          ImmobileGameObject immobileGameObjectType) {
    this.rowLevel = rowLevel;
    this.gameObjectAcceleration = gameObjectAcceleration;
    this.lastObjectAdded = 0;
    this.mobileGameObjectType = mobileGameObjectType;
    this.immobileGameObjectType = immobileGameObjectType;
  }

  public int getRowLevel() {
    return rowLevel;
  }

  public int getGameObjectAcceleration() {
    return gameObjectAcceleration;
  }

  public MobileGameObject getMobileGameObjectType() {
    return mobileGameObjectType;
  }

  public ImmobileGameObject getImmobileGameObjectType() {
    return immobileGameObjectType;
  }

  public Vector getMobileGameObjectSize() {
    return mobileGameObjectType.getSize();
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
