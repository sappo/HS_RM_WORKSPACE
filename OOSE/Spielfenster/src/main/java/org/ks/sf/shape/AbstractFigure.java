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
        acceleration = acceleration.inverse();
    }

    @Override
    public abstract BoundingBox getBoundingBox();

    @Override
    public final boolean intersects(Figure collidingFigure) {
        return this.calculateIntersection(collidingFigure);
    }
    
//    public boolean isAbove(Figure that){
//        return this.getBasePoint().getY()+getHeight()<that.getBoundingBox().getBasePoint().getY();
//    }
//
//    public boolean isLeftOf(Figure that){
//        return this.getBoundingBox().getBasePoint().getX()+this.getWidth()< that.getBoundingBox().getBasePoint().getX();
//    }   

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
                && ((fY1 <= cfY1 && cfY1 <= fY2) || (fY1 <= cfY2 && cfY2 <= fY2)) || 
                ((cfX1 <= fX1 && fX1 <= cfX2) || (cfX1 <= fX2 && fX2 <= cfX2))
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
