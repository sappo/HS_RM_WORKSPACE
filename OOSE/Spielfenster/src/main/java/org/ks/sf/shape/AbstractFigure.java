package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;
/**
 * 
 * @author Kevin Sapper 2011
 */
public abstract class AbstractFigure implements Figure {

    private Vector basePoint;

    private Vector acceleration;

    public AbstractFigure(Vector basePoint) {
        this.basePoint = basePoint;
    }

    public AbstractFigure(Vector basePoint, Vector acceleration) {
        this.basePoint = basePoint;
        this.acceleration = acceleration;
    }

    @Override
    public Vector getBasePoint() {
        return basePoint;
    }

    @Override
    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public void move() {
        basePoint = basePoint.add(acceleration);
    }

    @Override
    public void turn() {
        acceleration = acceleration.inverse(basePoint);
    }

    @Override
    public abstract boolean isTouchingBorder(Figure f);

    @Override
    public abstract void draw(Graphics g);

    @Override
    public String toString() {
        return "Figure<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")" + ">";
    }
}
