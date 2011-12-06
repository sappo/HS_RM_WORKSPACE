package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Rectangle extends AbstractFigure {

    private Vector v;

    public Rectangle(Vector basePoint, Vector v) {
        super(basePoint);
        this.v = v;
    }

    public Rectangle(Vector basePoint, Vector acceleration, Vector v) {
        super(basePoint, acceleration);
        this.v = v;
    }

    @Override
    public void draw(Graphics g) {
        Vector fusspunktVSumme = v.add(getBasePoint());
        g.drawRect((int) getBasePoint().getX(), (int) getBasePoint().getY(),
                (int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY());
    }

    @Override
    public String toString() {
        return "Rectangle<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + v.
                getX() + "," + v.getY() + ")" + ">";
    }

    @Override
    public boolean isTouchingBorder(Figure f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getMass() {
        return 1 * getArea();
    }

    private double getArea() {
        double a = v.getX() - getBasePoint().getX();
        double b = v.getY() - getBasePoint().getY();
        return 2 * (a) + 2 * (b);
    }
}
