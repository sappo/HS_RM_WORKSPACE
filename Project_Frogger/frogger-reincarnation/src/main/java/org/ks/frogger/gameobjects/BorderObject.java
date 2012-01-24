package org.ks.frogger.gameobjects;

import org.ks.sf.shape.Rectangle;

/**
 * This objects represents the game panels border.
 *
 * @author Kevin Sapper 2011
 */
public class BorderObject extends GameObject {

  /**
   * Create a new BorderOject
   * @param pictureBoundingBox the rectangle representing the border
   */
  public BorderObject(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  @Override
  public CollusionAction getCollusionAction() {
    return CollusionAction.KILL;
  }
}
