package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Rectangle extends AbstractFigure {

  private final Vector diagonal;

  private BoundingBox boundingBox;

  private boolean dirty = true;

  public Rectangle(Rectangle rectangle) {
    super(rectangle.getBasePoint(), rectangle.getAcceleration());
    this.diagonal = rectangle.getDiagonal();
  }

  public Rectangle(Vector basePoint, Vector diagonal) {
    super(basePoint);
    this.diagonal = diagonal;
  }

  public Rectangle(Vector basePoint, Vector acceleration, Vector v) {
    super(basePoint, acceleration);
    this.diagonal = v;
  }

  @Override
  public void draw(Graphics g) {
    Vector pointC = getBasePoint().add(diagonal);
    Vector pointB = new Vector(pointC.getX(), getBasePoint().getY());
    Vector pointD = new Vector(getBasePoint().getX(), pointC.getY());

    g.drawLine((int) getBasePoint().getX(), (int) getBasePoint().getY(),
            (int) pointB.getX(), (int) pointB.getY());
    g.drawLine((int) pointB.getX(), (int) pointB.getY(),
            (int) pointC.getX(), (int) pointC.getY());
    g.drawLine((int) pointC.getX(), (int) pointC.getY(),
            (int) pointD.getX(), (int) pointD.getY());
    g.drawLine((int) pointD.getX(), (int) pointD.getY(),
            (int) getBasePoint().getX(), (int) getBasePoint().getY());
  }

  @Override
  public String toString() {
    return "Rectangle<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + diagonal.
            getX() + "," + diagonal.getY() + ")" + ">";
  }

  @Override
  public double getMass() {
    return 1 * getArea();
  }

  private double getArea() {
    Vector pointC = getBasePoint().add(diagonal);
    Vector pointD = new Vector(getBasePoint().getX(), pointC.getY());
    Vector pointB = new Vector(pointC.getX(), getBasePoint().getY());

    Vector lineA = getBasePoint().subtract(pointB);
    Vector lineD = getBasePoint().subtract(pointD);

    return 2 * (lineA.getLength()) + 2 * (lineD.getLength());
  }

  public Vector getDiagonal() {
    return diagonal;
  }

  @Override
  public BoundingBox getBoundingBox() {
    if (dirty) {
      boundingBox = new BoundingBox(getBasePoint(), diagonal);
      dirty = false;
    }
    return boundingBox;
  }

  @Override
  public void move() {
    dirty = true;
    super.move();
  }

  @Override
  public void move(Vector direction) {
    dirty = true;
    super.move(direction);
  }

  @Override
  public boolean intersects(Figure collidingFigure) {
    return getBoundingBox().intersects(collidingFigure);
  }
}
