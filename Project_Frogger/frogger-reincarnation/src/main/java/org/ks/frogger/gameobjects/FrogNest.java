package org.ks.frogger.gameobjects;

import com.google.common.base.Optional;
import java.awt.Graphics;
import java.awt.Image;
import org.ks.sf.shape.Rectangle;

/**
 * This game objects represents a frog nest.
 *
 * @author Kevin Sapper 2011
 */
public class FrogNest extends GameObject {

  private Optional<GameObject> invader = Optional.absent();

  /**
   * Creates a new FrogNest.
   *
   * @param pictureBoundingBox the bounding box of this object
   */
  public FrogNest(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  /**
   * Creates a new FrogNest.
   *
   * @param pictureBoundingBox the bounding box of this object
   * @param image this image painted for this object
   */
  public FrogNest(Rectangle pictureBoundingBox, Image image) {
    super(pictureBoundingBox, image);
  }

  /**
   * Add an invader to the frog nest.
   *
   * @param invader the invader. May be the Frogger itself.
   */
  public void addInvader(GameObject invader) {
    this.invader = Optional.fromNullable(invader);
  }

  /**
   * Remove the invader from the nest.
   */
  public void removeInvader() {
    this.invader = Optional.absent();
  }

  /**
   * {@inheritDoc} This implementation checks if there's an invader present and
   * paint it in that case.
   */
  @Override
  public void draw(Graphics g) {
    super.draw(g);
    if (invader.isPresent()) {
      invader.get().
              draw(g);
    }
  }

  /**
   * {@inheritDoc } This implmentation returns the invaders collusion action if
   * present
   */
  @Override
  public CollusionAction getCollusionAction() {
    CollusionAction action;
    if (invader.isPresent()) {
      action = invader.get().
              getCollusionAction();
    } else {
      action = CollusionAction.OBJECTIVEREACHED;
    }
    return action;
  }
}
