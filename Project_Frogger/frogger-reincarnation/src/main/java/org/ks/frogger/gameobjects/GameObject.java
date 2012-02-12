package org.ks.frogger.gameobjects;

import java.awt.Graphics;
import java.awt.Image;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.BoundingBox;
import org.ks.sf.shape.Figure;
import org.ks.sf.shape.Rectangle;

/**
 * This class is the scaffold for all GameObjects.
 * @author Kevin Sapper 2011
 */
public abstract class GameObject implements Figure {

  private Image image;

  private Rectangle pictureBoundingBox;

  /**
   * Create a new GameObject.
   * @param pictureBoundingBox the bounding box for this object
   */
  public GameObject(Rectangle pictureBoundingBox) {
    this.pictureBoundingBox = pictureBoundingBox;
  }

  /**
   * Create a new GameObject.
   * @param pictureBoundingBox the bounding box for this object
   * @param image the image to be drawn by this object.
   */
  public GameObject(Rectangle pictureBoundingBox, Image image) {
    this.image = image;
    this.pictureBoundingBox = pictureBoundingBox;
  }

  /**
   * Sets the game objects to a position 
   * @param position to set the object to
   */
  protected void setPosition(Rectangle position) {
    pictureBoundingBox = position;
  }

  /**
   * {@inherited}
   * This implementation draws a gameobjects image is available.
   * In case there is no image the picture bounding box is drawn.
   */
  public void draw(Graphics g) {
    if (image == null) {
      pictureBoundingBox.draw(g);
    } else {
      pictureBoundingBox.draw(g);
      g.drawImage(image, (int) pictureBoundingBox.getBasePoint().
              getX(), (int) pictureBoundingBox.getBasePoint().
              getY(), null);
    }
  }

  /**
   * Checks weather two figures intersect with each other.
   * @param figure the figure to check against this.
   * @return true if interects, else false
   */
  public boolean intersects(Figure figure) {
    return pictureBoundingBox.intersects(figure);
  }

  /**
   * Get the action which shall be applied by a collusion.
   * @return the action or an empty string for no action.
   */
  public abstract CollusionAction getCollusionAction();

  /**
   * Moves an GameObject according to it's pictureBoundingBox acceleration.
   */
  @Override
  public void move() {
    pictureBoundingBox.move();
  }

  /**
   * Moves an GameObject into the parameter direction.
   */
  public void move(Vector direction) {
    pictureBoundingBox.move(direction);
  }

  /**
   * Turns a GameObject by 180° 
   */
  @Override
  public void turn() {
    pictureBoundingBox.turn();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Vector getBasePoint() {
    return pictureBoundingBox.getBasePoint();
  }

  public Vector getDiagonal() {
    return pictureBoundingBox.getDiagonal();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Vector getAcceleration() {
    return pictureBoundingBox.getAcceleration();
  }

  public void setAcceleration(Vector acceleration) {
    pictureBoundingBox.setAcceleration(acceleration);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getMass() {
    return pictureBoundingBox.getMass();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BoundingBox getBoundingBox() {
    return pictureBoundingBox.getBoundingBox();
  }

  public void setImage(Image image) {
    this.image = image;
  }
}