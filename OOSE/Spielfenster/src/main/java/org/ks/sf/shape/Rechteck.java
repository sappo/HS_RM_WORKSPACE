package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vektor;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Rechteck extends Figur {

  private Vektor v;

  public Rechteck(Vektor fusspunkt, Vektor v) {
    super(fusspunkt);
    this.v = v;
  }

  @Override
  public void zeichne(Graphics g) {
    Vektor fusspunktVSumme = v.add(getFusspunkt());
    g.drawRect((int) getFusspunkt().getX(), (int) getFusspunkt().getY(),
            (int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY());
  }
}
