package org.ks.frogger.gameobjects;

import org.ks.sf.math.Vector;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper
 */
public class Frogger extends GameObject {
  
  private final Rectangle startPosition;

  /**
   * Create a new Frogger with the given start position.
   * @param startPosition the Froggers start position
   */
  public Frogger(Rectangle startPosition) {
    super(startPosition);
    this.startPosition = new Rectangle(startPosition);
  }

  public void moveRight() {
    getPictureBoundingBox().move(new Vector(10, 0));
  }

  public void moveLeft() {
    getPictureBoundingBox().move(new Vector(-10, 0));
  }

  public void moveUp() {
    getPictureBoundingBox().move(new Vector(0, -25));
  }

  public void moveDown() {
    getPictureBoundingBox().move(new Vector(0, 25));
  }
  
  /**
   * Resets the Frogger to his start position
   */
  public void reset() {
    setPosition(new Rectangle(startPosition));    
  }

  @Override
  public CollusionAction getCollusionAction() {
    return CollusionAction.KILL;
  }
}
