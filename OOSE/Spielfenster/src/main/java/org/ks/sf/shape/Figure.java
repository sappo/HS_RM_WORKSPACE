package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

public abstract class Figure {

  private Vector fusspunkt;

  public Figure(Vector fusspunkt) {
    this.fusspunkt = fusspunkt;
  }

  public Vector getFusspunkt() {
    return fusspunkt;
  }

  public abstract void zeichne(Graphics g);
}
