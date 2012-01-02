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
        if (acceleration != null) {
            move(acceleration);
        }

    }
    
    public void move(Vector direction) {
        basePoint = basePoint.add(direction);
    }

    @Override
    public void turn() {
        if (acceleration != null) {
            acceleration = acceleration.inverse();
        }
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public String toString() {
        return "Figure<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")" + ">";
    }
}
