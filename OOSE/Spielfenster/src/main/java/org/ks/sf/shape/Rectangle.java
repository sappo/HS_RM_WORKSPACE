package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Rectangle extends AbstractFigure {

    private final Vector diagonal;
    
    private BoundingBox boundingBox;
    
    private boolean dirty = true;

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
        g.drawRect((int) getBasePoint().getX(), (int) getBasePoint().getY(),
                (int) diagonal.getX(), (int) diagonal.getY());
    }

    @Override
    public String toString() {
        return "Rectangle<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + diagonal.
                getX() + "," + diagonal.getY() + ")" + ">";
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
    public BoundingBox getBoundingBox() {
        if(dirty) {
            boundingBox = new BoundingBox(getBasePoint(), diagonal);
            dirty = false;
        }
        return boundingBox;
    }

    @Override
    public void move() {
        dirty = true;
        super.move();
    }
    
    
}
