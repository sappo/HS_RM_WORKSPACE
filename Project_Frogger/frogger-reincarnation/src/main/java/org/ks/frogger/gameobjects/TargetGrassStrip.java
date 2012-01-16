package org.ks.frogger.gameobjects;

import javax.swing.ImageIcon;
import org.ks.sf.shape.Rectangle;

public class TargetGrassStrip extends GameObject {

  public TargetGrassStrip(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  public TargetGrassStrip(Rectangle pictureBoundingBox, ImageIcon image) {
    super(pictureBoundingBox, image);
  }

  @Override
  public CollusionAction getCollusionAction() {
    return CollusionAction.OBJECTIVEREACHED;
  }
}