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
            basePoint = basePoint.add(acceleration);
        }

    }

    @Override
    public void turn() {
        if (acceleration != null) {
            acceleration = acceleration.inverse();
        }
    }

    @Override
    public abstract BoundingBox getBoundingBox();

    @Override
    public final boolean intersects(Figure collidingFigure) {
        return checkOverlap(collidingFigure);
//        if (this.equals(collidingFigure)) {
//            return false;
//        }
//        if (this.isLeftOf(collidingFigure)) {
//            return false;
//        }
//        if (collidingFigure.isLeftOf(this)) {
//            return false;
//        }
//        if (this.isAbove(collidingFigure)) {
//            return false;
//        }
//        if (collidingFigure.isAbove(this)) {
//            return false;
//        }
//        return true;
    }
    
    /**
     * Check overlapping of the bounding boxes with the following conditions.
     * <p>The left edge of B is to the left of right edge of R.</p>
     * <p>The top edge of B is above the R bottom edge.</p>
     * <p>The right edge of B is to the right of left edge of R.</p>
     * <p>The bottom edge of B is below the R upper edge.</p>
     * @param that figure to compare this to.
     * @return true if all conditions are met, else false.
     */
    private boolean checkOverlap(Figure that) {
        Vector thatPointC = getPointC(that.getBoundingBox());
        Vector thisPointC = getPointC(this.getBoundingBox());
        return ((thisPointC.getX() >= that.getBoundingBox().getBasePoint().getX())
                && (thisPointC.getY() >= that.getBoundingBox().getBasePoint().
                getY())
                && (this.getBoundingBox().getBasePoint().getX() <= thatPointC.
                getX())
                && (this.getBoundingBox().getBasePoint().getY() <= thatPointC.
                getY()));
    }

    /**
     * Inspired by "Eric Panitz - Java will spielen"
     * Checks if this is above that
     * @param that figure to comapre this to.
     * @return true if this is above that, else false
     */
    @Override
    public boolean isAbove(Figure that) {
        Vector pointC = this.getBoundingBox().getBasePoint().add(this.
                getBoundingBox().getDiagonal());
        double height = (this.getBoundingBox().getBasePoint().getY() - pointC.
                getY());
        height = height < 0 ? height * -1 : height;
        return this.getBasePoint().getY() + height < that.getBoundingBox().
                getBasePoint().getY();
    }

    /** 
     * Inspired by "Eric Panitz - Java will spielen"
     * Checks if this is left of that
     * @param that figure to comapre this to.
     * @return true if this is left of that, else false#
     */
    @Override
    public boolean isLeftOf(Figure that) {
        Vector pointC = this.getBoundingBox().getBasePoint().add(this.
                getBoundingBox().getDiagonal());
        double width = (this.getBoundingBox().getBasePoint().getX() - pointC.
                getX());
        width = width < 0 ? width * -1 : width;
        return this.getBoundingBox().getBasePoint().getX() + width < that.
                getBoundingBox().getBasePoint().getX();
    }

    private Vector getPointC(BoundingBox boundingBox) {
        return boundingBox.getBasePoint().add(boundingBox.getDiagonal());
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public String toString() {
        return "Figure<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")" + ">";
    }
}
