package org.ks.frogger.gameobjects;

import java.awt.Image;
import org.ks.sf.shape.Rectangle;

/**
 * 
 * @author Kevin Sapper 2011
 */
public class Strip extends GameObject {

  private CollusionAction collusionAction;
  
  /**
   * Creates a new Stript.
   *
   * @param pictureBoundingBox the bounding box of this object
   */
  public Strip(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  /**
   * Creates a new Strip.
   *
   * @param pictureBoundingBox the bounding box of this object
   * @param image this image painted for this object
   */
  public Strip(Rectangle pictureBoundingBox, Image image) {
    super(pictureBoundingBox, image);
  }

  public void setCollusionAction(CollusionAction collusionAction) {
    this.collusionAction = collusionAction;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CollusionAction getCollusionAction() {
    return collusionAction;
  }
}