package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Rectangle extends AbstractFigure {

    private final Vector diagonal;

    public Rectangle(Vector basePoint, Vector diagonal) {
        super(basePoint);
        this.diagonal = diagonal;
    }

    public Rectangle(Vector basePoint, Vector acceleration, Vector v) {
        super(basePoint, acceleration);
        this.diagonal = v;
    }

    @Override
    public void draw(Graphics g) {
        Vector fusspunktVSumme = diagonal.add(getBasePoint());
        g.drawRect((int) getBasePoint().getX(), (int) getBasePoint().getY(),
                (int) fusspunktVSumme.getX(), (int) fusspunktVSumme.getY());
    }

    @Override
    public String toString() {
        return "Rectangle<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + diagonal.
                getX() + "," + diagonal.getY() + ")" + ">";
    }

    @Override
    public boolean isTouches(Figure f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getMass() {
        return 1 * getArea();
    }

    private double getArea() {
        Vector pointC = getBasePoint().add(diagonal);
        Vector pointD = new Vector(getBasePoint().getX(), pointC.getY());
        Vector pointB = new Vector(pointC.getX(), getBasePoint().getY());

        Vector lineA = getBasePoint().subtract(pointB);
        Vector lineD = getBasePoint().subtract(pointD);

        return 2 * (lineA.getLength()) + 2 * (lineD.getLength());
    }

    public Vector getDiagonal() {
        return diagonal;
    }

    @Override
    public Rectangle getBoundingBox() {
        return this;
    }
    
}
