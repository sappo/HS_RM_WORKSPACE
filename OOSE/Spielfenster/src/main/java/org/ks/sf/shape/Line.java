package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

public class Line extends Figure {

  private Vector v;

  public Line(Vector fusspunkt, Vector v) {
    super(fusspunkt);
    this.v = v;
  }

  public void zeichne(Graphics g) {
    int x1 = (int) getFusspunkt().getX();
    int y1 = (int) getFusspunkt().getY();
    Vector spitze = getFusspunkt().add(v);
    int x2 = (int) spitze.getX();
    int y2 = (int) spitze.getY();
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public Vector getFusspunkt() {
    return super.getFusspunkt();
  }

  @Override
  public String toString() {
    return "Line<(" + getFusspunkt().getX() + "," + getFusspunkt().getY() + ")," + "(" + v.getX() + "," + v.getY() + ")" + ">";
  }
}
