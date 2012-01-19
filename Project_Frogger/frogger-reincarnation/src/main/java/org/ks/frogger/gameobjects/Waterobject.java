package org.ks.frogger.gameobjects;

import java.awt.Image;
import org.ks.sf.shape.Rectangle;

/**
 * A WaterObject represents any kind of object which can swimm in the water.
 * E.g. crocodile, turtle, tree trunk, etc...
 * @author Kevin Sapper 2011
 */
public class Waterobject extends GameObject {

  public Waterobject(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  public Waterobject(Rectangle pictureBoundingBox, Image image) {
    super(pictureBoundingBox, image);
  }

  public CollusionAction getCollusionAction() {
    return CollusionAction.CARRY;
  }
}
