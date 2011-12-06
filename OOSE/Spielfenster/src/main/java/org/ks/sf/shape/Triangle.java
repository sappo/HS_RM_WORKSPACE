package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Triangle extends AbstractFigure {

    private Vector v;

    private Vector w;

    public Triangle(Vector basePoint, Vector v, Vector w) {
        super(basePoint);
        this.v = v;
        this.w = w;
    }

    public Triangle(Vector basePoint, Vector acceleration, Vector v, Vector w) {
        super(basePoint, acceleration);
        this.v = v;
        this.w = w;
    }

    @Override
    public void draw(Graphics g) {
        Vector fusspunktVSumme = getBasePoint().add(v);
        Vector fusspunktWSumme = getBasePoint().add(w);
        // draw line from fusspunkt to v
        g.drawLine((int) getBasePoint().getX(), (int) getBasePoint().getY(),
                (int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY());
        // draw line from fusspunkt to w
        g.drawLine((int) getBasePoint().getX(), (int) getBasePoint().getY(),
                (int) fusspunktWSumme.getX(), (int) fusspunktWSumme.getY());
        //draw line from v to w
        g.drawLine((int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY(),
                (int) fusspunktWSumme.getX(), (int) fusspunktWSumme.getY());
    }

    @Override
    public String toString() {
        return "Triangle<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + v.
                getX() + "," + v.getY() + ")," + "(" + w.getX() + "," + w.getY() + ")" + ">";
    }

    @Override
    public boolean isTouchingBorder(Figure f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getMass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}