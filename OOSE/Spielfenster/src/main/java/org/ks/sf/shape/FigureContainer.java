package org.ks.sf.shape;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.naming.SizeLimitExceededException;
import org.ks.sf.math.Vector;

/** 
 * Ein Objekt der Klasse FigurBehaelter ist ein Behaelter fuer Figur-Objekte.
 * Ein FigurBehaelter wird mit einer initialen Kapazitaet angelegt. Wird beim
 * Einfuegen von Figur-Objekten in diesen Behaelter die urspruengliche
 * Kapazitaet ueberschritten, dann erweitert sich der Behaelter selbsttaetig.
 */
public class FigureContainer implements Figure {

    private List<Figure> figureContainer;

    private BoundingBox boundingBox;

    private int capacity;

    private boolean checkContainerCollusions = true;

    private boolean dirty = true;

    /** Creates a figure container with the passed capacity.
     * @param capacity initial capacity
     * @throws IllegalArgumentException if capacity is negative.
     */
    public FigureContainer(int capacity) throws IllegalArgumentException {
        this.figureContainer = new ArrayList<Figure>(capacity);
        this.capacity = capacity;
    }

    /** Adds a figure to the container.
     * @param figure to add
     * @throws IllegalArgumentException if figure is null
     * @throws SizeLimitExceededException if capacity is reached.
     */
    public void add(Figure figure) throws IllegalArgumentException {
        if (figure == null) {
            throw new IllegalArgumentException("Cannot add a empty figure.");
        }
        // if container space is left add figure
        if (figureContainer.size() == capacity) {
            capacity *= 2;
        }
        figureContainer.add(figure);
    }

    /** 
     * @return the current capacity
     */
    public int capacity() {
        return capacity;
    }

    /** 
     * @return the number of figures in the container
     */
    public int size() {
        return figureContainer.size();
    }

    /** 
     * Return the figure at the index position.
     * @param index index position
     * @return the figure associated with the index
     * @throws ArrayIndexOutOfBoundsException if the index doesn't exist.
     */
    public Figure elementAt(int index) throws ArrayIndexOutOfBoundsException {
        return figureContainer.get(index);
    }

    /**
     * Move all figures in the container.
     */
    @Override
    public void move() {
        dirty = true;
        for (Figure figure : figureContainer) {
            figure.move();
            if (checkContainerCollusions) {
                for (Figure figureX : figureContainer) {
                    if (figure.intersects(figureX)) {
                        //turn and move away from each other
                        figure.turn();
                        figure.move();
                        figureX.turn();
                        figureX.move();
                    }
                }
            }
        }
    }

    /**
     * Paint all figures in the container.
     * @param g Graphics object to paint
     */
    public void draw(Graphics g) {
        for (Figure figure : figureContainer) {
            figure.draw(g);
        }
    }

    @Override
    public void turn() {
        for (Figure figure : figureContainer) {
            figure.turn();
        }
    }

    @Override
    public double getMass() {
        double mass = 0;
        for (Figure figure : figureContainer) {
            mass += figure.getMass();
        }
        return mass;
    }

    @Override
    public boolean intersects(Figure collidingFigure) {
        if (this.equals(collidingFigure)) {
            return false;
        }
        if (this.isLeftOf(collidingFigure)) {
            return false;
        }
        if (collidingFigure.isLeftOf(this)) {
            return false;
        }
        if (this.isAbove(collidingFigure)) {
            return false;
        }
        if (collidingFigure.isAbove(this)) {
            return false;
        }
        return true;
    }

    @Override
    public Vector getBasePoint() {
        return getBoundingBox().getBasePoint();
    }

    @Override
    public Vector getAcceleration() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** 
     * Return a chained string of all figures in the container.
     * @return the chained string
     */
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder("FigureContainer{");
        for (Figure figure : figureContainer) {
            content.append(figure.toString()).append(";");
        }
        content.append("}");
        return content.toString();
    }

    @Override
    public BoundingBox getBoundingBox() {
        if (dirty) {
            List<Double> xCoordinates = new ArrayList<Double>();
            List<Double> yCoordinates = new ArrayList<Double>();

            for (Figure figure : figureContainer) {
                BoundingBox boundingBox = figure.getBoundingBox();
                Vector basePoint = boundingBox.getBasePoint();
                Vector pointC = basePoint.add(boundingBox.getDiagonal());
                xCoordinates.add(basePoint.getX());
                yCoordinates.add(basePoint.getY());
                xCoordinates.add(pointC.getX());
                yCoordinates.add(pointC.getY());
            }
            Collections.sort(xCoordinates);
            Collections.sort(yCoordinates);

            Vector rectPointA = new Vector(xCoordinates.get(0),
                    yCoordinates.get(
                    0));
            Vector rectPointC = new Vector(xCoordinates.get(
                    xCoordinates.size() - 1),
                    yCoordinates.get(yCoordinates.size() - 1));

            boundingBox = new BoundingBox(rectPointA, rectPointC.subtract(
                    rectPointA));
            dirty = false;
        }
        return boundingBox;
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

    public boolean isCheckContainerCollusions() {
        return checkContainerCollusions;
    }

    public void setCheckContainerCollusions(boolean checkContainerCollusions) {
        this.checkContainerCollusions = checkContainerCollusions;
    }
}