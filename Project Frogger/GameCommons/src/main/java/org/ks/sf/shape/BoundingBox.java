package org.ks.sf.shape;

import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper
 */
public class BoundingBox {

    private Vector basePoint;

    private Vector diagonal;

    public BoundingBox(Vector basePoint, Vector diagonal) {
        this.basePoint = basePoint;
        this.diagonal = diagonal;
    }

    public Vector getBasePoint() {
        return basePoint;
    }

    public Vector getDiagonal() {
        return diagonal;
    }

    /**
     * Check intersect of the bounding boxes with the following conditions.
     * <p>The left edge of B is to the left of right edge of R.</p>
     * <p>The top edge of B is above the R bottom edge.</p>
     * <p>The right edge of B is to the right of left edge of R.</p>
     * <p>The bottom edge of B is below the R upper edge.</p>
     * @param that figure to compare this to.
     * @return true if all conditions are met, else false.
     */
    public final boolean intersects(Figure collidingFigure) {
        Vector thatPointC = getPointC(collidingFigure.getBoundingBox());
        Vector thisPointC = getPointC(this);
        return ((thisPointC.getX() >= collidingFigure.getBoundingBox().
                getBasePoint().getX())
                && (thisPointC.getY() >= collidingFigure.getBoundingBox().
                getBasePoint().
                getY())
                && (this.getBasePoint().getX() <= thatPointC.getX())
                && (this.getBasePoint().getY() <= thatPointC.getY()));

    }

    private Vector getPointC(BoundingBox boundingBox) {
        return boundingBox.getBasePoint().add(boundingBox.getDiagonal());
    }
}
