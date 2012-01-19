package org.ks.frogger.gameobjects;

import java.awt.Image;
import org.ks.sf.shape.Rectangle;

/**
 * 
 * @author Kevin Sapper 2011
 */
public class Strip extends GameObject {

  private CollusionAction collusionAction;
  
  public Strip(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  public Strip(Rectangle pictureBoundingBox, Image image) {
    super(pictureBoundingBox, image);
  }

  public void setCollusionAction(CollusionAction collusionAction) {
    this.collusionAction = collusionAction;
  }

  @Override
  public CollusionAction getCollusionAction() {
    return collusionAction;
  }
}