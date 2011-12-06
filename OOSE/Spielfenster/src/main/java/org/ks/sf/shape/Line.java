package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 * 
 * @author Kevin Sapper 2011
 */
public class Line extends AbstractFigure {

    private Vector v;

    public Line(Vector basepoint, Vector v) {
        super(basepoint);
        this.v = v;
    }

    public Line(Vector basePoint, Vector acceleration, Vector v) {
        super(basePoint, acceleration);
        this.v = v;
    }

    @Override
    public void draw(Graphics g) {
        int x1 = (int) getBasePoint().getX();
        int y1 = (int) getBasePoint().getY();
        Vector spitze = getBasePoint().add(v);
        int x2 = (int) spitze.getX();
        int y2 = (int) spitze.getY();
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String toString() {
        return "Line<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + v.
                getX() + "," + v.getY() + ")" + ">";
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
