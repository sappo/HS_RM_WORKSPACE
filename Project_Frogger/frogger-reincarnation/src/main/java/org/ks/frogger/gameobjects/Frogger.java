package org.ks.frogger.gameobjects;

import org.ks.sf.math.Vector;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper
 */
public class Frogger extends GameObject {

  private final Rectangle startPosition;

  private boolean carried = false;

  private boolean sittingInNest = false;

  /**
   * Create a new Frogger at the given start position.
   * @param startPosition the Froggers start position
   */
  private Frogger(Rectangle startPosition) {
    super(startPosition);
    this.startPosition = new Rectangle(startPosition);
  }

  /**
   * Only works if not sitting in nest!
   */
  public void moveRight() {
    if (!sittingInNest) {
      move(new Vector(50, 0));
    }
  }

  /**
   * Only works if not sitting in nest!
   */
  public void moveLeft() {
    if (!sittingInNest) {
      move(new Vector(-50, 0));
    }
  }

  /**
   * Only works if not sitting in nest!
   */
  public void moveUp() {
    move(new Vector(0, -50));
  }

  /**
   * Only works if not sitting in nest!
   */
  public void moveDown() {
    if (!sittingInNest) {
      move(new Vector(0, 50));
    }
  }

  public void jumpOff() {
    if (!sittingInNest) {
      this.carried = false;
      setAcceleration(null);
    }
  }

  /**
   * Only works if not sitting in nest!
   */
  public void jumpOn(GameObject carrier) {
    if (!sittingInNest) {
      this.carried = true;
      setAcceleration(carrier.getAcceleration());
    }
  }

  public boolean isCarried() {
    return carried;
  }

  /**
   * Resets the Frogger to his start position
   */
  public void reset() {
    setPosition(new Rectangle(startPosition));
  }

  @Override
  public CollusionAction getCollusionAction() {
    CollusionAction action = CollusionAction.NOTHING;
    if (sittingInNest) {
      action = CollusionAction.KILL;
    }
    return action;
  }

  /**
   * Creates a new Frogger at the given start position.
   * @param startPosition position to draw the frogger.
   * @return a new Frogger
   */
  public static Frogger newInstanceAtStart(Rectangle startPosition) {
    return new Frogger(startPosition);
  }

  /**
   * Creates a new Frogger inside the given nest. Nest must be at least as 
   * large as the Frogger
   * @param the size of the frogger to place in the nest
   * @param nest the nest to place the frogger inside
   * @return a new Frogger
   */
  public static Frogger newInstanceInNest(Vector froggerSize, FrogNest nest) {
    Vector nestSize = nest.getDiagonal();
    Vector froggerPositionOffset = nestSize.subtract(froggerSize).mult(0.5);
    Vector froggerPosition = nest.getBasePoint().add(froggerPositionOffset);
    Frogger frogger = new Frogger(new Rectangle(froggerPosition, froggerSize));
    frogger.sittingInNest = true;
    return frogger;
  }
}
