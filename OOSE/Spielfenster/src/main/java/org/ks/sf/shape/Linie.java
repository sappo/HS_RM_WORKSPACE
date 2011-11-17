package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vektor;

public class Linie extends Figur {

  private Vektor v;

  public Linie(Vektor fusspunkt, Vektor v) {
    super(fusspunkt);
    this.v = v;
  }

  public void zeichne(Graphics g) {
    int x1 = (int) getFusspunkt().getX();
    int y1 = (int) getFusspunkt().getY();
    Vektor spitze = getFusspunkt().add(v);
    int x2 = (int) spitze.getX();
    int y2 = (int) spitze.getY();
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public Vektor getFusspunkt() {
    return super.getFusspunkt();
  }
}
