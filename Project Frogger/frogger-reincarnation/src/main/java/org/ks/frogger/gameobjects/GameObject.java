package org.ks.frogger.gameobjects;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.BoundingBox;
import org.ks.sf.shape.Figure;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper
 */
public abstract class GameObject implements Figure {

  private ImageIcon image;

  private Rectangle pictureBoundingBox;

  public GameObject(Rectangle pictureBoundingBox) {
    this.pictureBoundingBox = pictureBoundingBox;
  }

  public GameObject(Rectangle pictureBoundingBox, ImageIcon image) {
    this.image = image;
    this.pictureBoundingBox = pictureBoundingBox;
  }

  public Rectangle getPictureBoundingBox() {
    return pictureBoundingBox;
  }

  /**
   * Sets the game objects to a position 
   * @param position to set the object to
   */
  protected void setPosition(Rectangle position) {
    pictureBoundingBox = position;
  }

  /**
   * @inherited
   */
  public void draw(Graphics g) {
    getPictureBoundingBox().draw(g);
  }

  public boolean intersects(Figure figure) {
    return getPictureBoundingBox().intersects(figure);
  }
  
  public abstract CollusionAction getCollusionAction();

  @Override
  public void move() {
    pictureBoundingBox.move();
  }

  @Override
  public void turn() {
    pictureBoundingBox.turn();
  }

  @Override
  public Vector getBasePoint() {
    return pictureBoundingBox.getBasePoint();
  }

  @Override
  public Vector getAcceleration() {
    return pictureBoundingBox.getAcceleration();
  }

  @Override
  public double getMass() {
    return pictureBoundingBox.getMass();
  }

  @Override
  public BoundingBox getBoundingBox() {
    return pictureBoundingBox.getBoundingBox();
  }

  public void setImage(ImageIcon image) {
    this.image = image;
  }
}