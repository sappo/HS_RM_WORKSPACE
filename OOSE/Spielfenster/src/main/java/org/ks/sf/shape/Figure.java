package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

public abstract class Figure {

  private Vector fusspunkt;

  private Vector geschwindigkeit;

  public Figure(Vector fusspunkt) {
    this.fusspunkt = fusspunkt;
  }

  public Figure(Vector fusspunkt, Vector geschwindigkeit) {
    this.fusspunkt = fusspunkt;
    this.geschwindigkeit = geschwindigkeit;
  }

  public Vector getFusspunkt() {
    return fusspunkt;
  }

  public Vector getGeschwindigkeit() {
    return geschwindigkeit;
  }

  public void bewege() {
    fusspunkt = fusspunkt.add(geschwindigkeit);
  }

  public abstract void zeichne(Graphics g);
}
