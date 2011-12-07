package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Triangle extends AbstractFigure {

    private final Vector lineB;

    private final Vector lineC;

    private final Rectangle boundingBox;

    public Triangle(Vector basePoint, Vector v, Vector w) {
        super(basePoint);
        this.lineB = v;
        this.lineC = w;
        this.boundingBox = initBoundingBox();
    }

    public Triangle(Vector basePoint, Vector acceleration, Vector v, Vector w) {
        super(basePoint, acceleration);
        this.lineB = v;
        this.lineC = w;
        this.boundingBox = initBoundingBox();
    }

    private Rectangle initBoundingBox() {
        return new Rectangle(getBasePoint(), lineB.add(lineC));
    }

    @Override
    public void draw(Graphics g) {
        Vector pointB = getBasePoint().add(lineC);
        Vector pointC = getBasePoint().add(lineB);
        // draw line from base point to point C
        g.drawLine((int) getBasePoint().getX(), (int) getBasePoint().getY(),
                (int) pointC.getX(), (int) pointC.getY());
        // draw line from base point to point B
        g.drawLine((int) getBasePoint().getX(), (int) getBasePoint().getY(),
                (int) pointB.getX(), (int) pointB.getY());
        //draw line from point B to point C
        g.drawLine((int) pointC.getX(), (int) pointC.getY(),
                (int) pointB.getX(), (int) pointB.getY());
    }

    @Override
    public String toString() {
        return "Triangle<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + lineB.
                getX() + "," + lineB.getY() + ")," + "(" + lineC.getX() + "," + lineC.
                getY() + ")" + ">";
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
        Vector pointB = getBasePoint().add(lineC);
        Vector pointC = getBasePoint().add(lineB);

        Vector lineA = pointC.subtract(pointB);

        // calculate area by using Heron's formula:
        // sqrt(s * (s - a) (s - b) (s - c))
        double s = (lineC.getLength() + lineA.getLength() + lineB.getLength()) / 2;
        double area = Math.sqrt(s * (s - lineA.getLength()) * (s - lineB.
                getLength()) * (s - lineC.getLength()));
        return area;
    }

    @Override
    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}