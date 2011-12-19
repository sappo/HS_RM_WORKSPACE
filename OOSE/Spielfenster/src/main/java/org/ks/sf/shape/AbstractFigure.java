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
//        return this.calculateIntersection(collidingFigure);
        return checkOverlap(collidingFigure);
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
        Vector thatPointC = that.getBoundingBox().getBasePoint().add(that.
                getBoundingBox().getDiagonal());
        Vector thisPointC = this.getBoundingBox().getBasePoint().add(this.
                getBoundingBox().getDiagonal());
        return ((thisPointC.getX() >= that.getBoundingBox().getBasePoint().getX())
                && (thisPointC.getY() >= that.getBoundingBox().getBasePoint().
                getY())
                && (this.getBoundingBox().getBasePoint().getX() <= thatPointC.
                getX())
                && (this.getBoundingBox().getBasePoint().getY() <= thatPointC.
                getY()));
    }

    /**
     * Check if 
     * this.x1 < that.x1 < this.x2 
     * @param collidingFigure
     * @return 
     */
    private boolean calculateIntersection(Figure collidingFigure) {
        Vector pointC = getPointC(getBoundingBox());
        double fX1 = getBoundingBox().getBasePoint().getX();
        double fX2 = pointC.getX();
        // smaller number has to be 1
        if (fX1 > fX2) {
            double temp = fX1;
            fX1 = fX2;
            fX2 = temp;
        }
        double fY1 = getBoundingBox().getBasePoint().getY();
        double fY2 = pointC.getY();
        // smaller number has to be 1
        if (fY1 > fY2) {
            double temp = fY1;
            fY1 = fY2;
            fY2 = temp;
        }

        pointC = getPointC(collidingFigure.getBoundingBox());
        double cfX1 = collidingFigure.getBoundingBox().getBasePoint().getX();
        double cfX2 = pointC.getX();
        // smaller number has to be 1
        if (cfX1 > cfX2) {
            double temp = cfX1;
            cfX1 = cfX2;
            cfX2 = temp;
        }
        double cfY1 = collidingFigure.getBoundingBox().getBasePoint().getY();
        double cfY2 = pointC.getY();
        // smaller number has to be 1
        if (cfY1 > cfY2) {
            double temp = cfY1;
            cfY1 = cfY2;
            cfY2 = temp;
        }

        return ((fX1 <= cfX1 && cfX1 <= fX2) || (fX1 <= cfX2 && cfX2 <= fX2))
                && ((fY1 <= cfY1 && cfY1 <= fY2) || (fY1 <= cfY2 && cfY2 <= fY2))
                || ((cfX1 <= fX1 && fX1 <= cfX2) || (cfX1 <= fX2 && fX2 <= cfX2))
                && ((cfY1 <= fY1 && fY1 <= cfY2) || (cfY1 <= fY2 && fY2 <= cfY2));
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
