package org.ks.frogger.gameobjects;

import java.awt.Image;
import org.ks.frogger.events.FroggerDeath;
import org.ks.sf.shape.Rectangle;

/**
 * A StreetObject represents any kind of vehicle which can drive on a street.
 * E.g. car, van, truck, motorcycle, etc...
 * @author Kevin Sapper 2011
 */
public class Streetobject extends GameObject {

  public Streetobject(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  public Streetobject(Rectangle pictureBoundingBox, Image image) {
    super(pictureBoundingBox, image);
  }

  /**
   * @inherited
   */
  @Override
  public CollusionAction getCollusionAction() {
    return CollusionAction.KILL;
  }

  /**
   * @inherited
   */
  public String getDeathCause() {
    return FroggerDeath.OVERRUN;
  }
}
