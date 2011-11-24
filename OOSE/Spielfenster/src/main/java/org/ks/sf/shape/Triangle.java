package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Triangle extends Figure {

  private Vector v;

  private Vector w;

  public Triangle(Vector fusspunkt, Vector v, Vector w) {
    super(fusspunkt);
    this.v = v;
    this.w = w;
  }

  public Triangle(Vector fusspunkt, Vector geschwindigkeit, Vector v, Vector w) {
    super(fusspunkt, geschwindigkeit);
    this.v = v;
    this.w = w;
  }

  @Override
  public void zeichne(Graphics g) {
    Vector fusspunktVSumme = getFusspunkt().add(v);
    Vector fusspunktWSumme = getFusspunkt().add(w);
    // draw line from fusspunkt to v
    g.drawLine((int) getFusspunkt().getX(), (int) getFusspunkt().getY(),
            (int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY());
    // draw line from fusspunkt to w
    g.drawLine((int) getFusspunkt().getX(), (int) getFusspunkt().getY(),
            (int) fusspunktWSumme.getX(), (int) fusspunktWSumme.getY());
    //draw line from v to w
    g.drawLine((int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY(),
            (int) fusspunktWSumme.getX(), (int) fusspunktWSumme.getY());
  }

  @Override
  public String toString() {
    return "Triangle<(" + getFusspunkt().getX() + "," + getFusspunkt().getY() + ")," + "(" + v.
            getX() + "," + v.getY() + ")," + "(" + w.getX() + "," + w.getY() + ")" + ">";
  }
}