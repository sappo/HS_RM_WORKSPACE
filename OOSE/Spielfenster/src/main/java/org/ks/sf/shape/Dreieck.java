package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vektor;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Dreieck extends Figur {

  private Vektor v;

  private Vektor w;

  public Dreieck(Vektor fusspunkt, Vektor v, Vektor w) {
    super(fusspunkt);
    this.v = v;
    this.w = w;
  }

  @Override
  public void zeichne(Graphics g) {
    Vektor fusspunktVSumme = getFusspunkt().add(v);
    Vektor fusspunktWSumme = getFusspunkt().add(w);
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
}