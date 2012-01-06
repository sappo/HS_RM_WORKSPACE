package org.ks.frogger.gameobjects;

import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper 2011
 */
public class BorderObject extends GameObject {

  public BorderObject(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  @Override
  public CollusionAction getCollusionAction() {
    return CollusionAction.KILL;
  }
}
