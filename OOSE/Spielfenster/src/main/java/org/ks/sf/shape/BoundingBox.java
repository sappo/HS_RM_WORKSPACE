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

}
