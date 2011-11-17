package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vektor;

public abstract class Figur {

  private Vektor fusspunkt;

  public Figur(Vektor fusspunkt) {
    this.fusspunkt = fusspunkt;
  }

  public Vektor getFusspunkt() {
    return fusspunkt;
  }

  public abstract void zeichne(Graphics g);
}
