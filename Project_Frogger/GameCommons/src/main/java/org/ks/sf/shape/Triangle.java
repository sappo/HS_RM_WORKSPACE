package org.ks.sf.shape;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Triangle extends AbstractFigure {

    private Vector lineB;

    private Vector lineC;

    private BoundingBox boundingBox;
    
    private boolean dirty = true;

    public Triangle(Vector basePoint, Vector v, Vector w) {
        super(basePoint);
        this.lineB = v;
        this.lineC = w;
    }

    public Triangle(Vector basePoint, Vector acceleration, Vector v, Vector w) {
        super(basePoint, acceleration);
        this.lineB = v;
        this.lineC = w;
    }

    private BoundingBox calculateBoundingBox() {
        Vector pointB = getBasePoint().add(lineC);
        Vector pointC = getBasePoint().add(lineB);

        List<Double> xCoordinates = new ArrayList<Double>();
        xCoordinates.add(getBasePoint().getX());
        xCoordinates.add(pointB.getX());
        xCoordinates.add(pointC.getX());
        Collections.sort(xCoordinates);

        List<Double> yCoordinates = new ArrayList<Double>();
        yCoordinates.add(getBasePoint().getY());
        yCoordinates.add(pointB.getY());
        yCoordinates.add(pointC.getY());
        Collections.sort(yCoordinates);

        Vector rectPointA = new Vector(xCoordinates.get(0), yCoordinates.get(
                0));
        Vector rectPointC = new Vector(xCoordinates.get(2), yCoordinates.get(
                2));
        
        return new BoundingBox(rectPointA, rectPointC.subtract(rectPointA));
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
    public BoundingBox getBoundingBox() {
        if(dirty){
            boundingBox = calculateBoundingBox();
            dirty = false;
        }
        return boundingBox;
    }

    @Override
    public void move() {
        dirty = true;
        super.move();
    }

    @Override
    public boolean intersects(Figure collidingFigure) {
        return getBoundingBox().intersects(collidingFigure);
    }
}