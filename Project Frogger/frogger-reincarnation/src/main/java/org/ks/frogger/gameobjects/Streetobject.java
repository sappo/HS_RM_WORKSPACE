package org.ks.frogger.gameobjects;

import org.ks.frogger.events.FroggerDeath;
import org.ks.sf.shape.Rectangle;

/**
 * An object representing any kind of steetobject e.g car, truck, motorcycle, 
 * etc..
 * @author Kevin Sapper
 */
public class Streetobject extends GameObject {

  public Streetobject(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  @Override
  public CollusionAction getCollusionAction() {
    return CollusionAction.KILL;
  }
  
  public String getDeathCause() {
    return FroggerDeath.OVERRUN;
  }
}
