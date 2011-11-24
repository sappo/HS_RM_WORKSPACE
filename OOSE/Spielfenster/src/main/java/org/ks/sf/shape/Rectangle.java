package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Rectangle extends Figure {

  private Vector v;

  public Rectangle(Vector fusspunkt, Vector v) {
    super(fusspunkt);
    this.v = v;
  }

  public Rectangle(Vector fusspunkt, Vector geschwindigkeit, Vector v) {
    super(fusspunkt, geschwindigkeit);
    this.v = v;
  }

  @Override
  public void zeichne(Graphics g) {
    Vector fusspunktVSumme = v.add(getFusspunkt());
    g.drawRect((int) getFusspunkt().getX(), (int) getFusspunkt().getY(),
            (int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY());
  }

  @Override
  public String toString() {
    return "Rectangle<(" + getFusspunkt().getX() + "," + getFusspunkt().getY() + ")," + "(" + v.
            getX() + "," + v.getY() + ")" + ">";
  }
}
