package org.ks.sf.shape;

import java.awt.Graphics;
import java.util.ArrayList;
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

    private int capacity;

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
        for (Figure figure : figureContainer) {
            figure.move();
            for (Figure figureX : figureContainer) {
                if (figure.intersects(figureX)) {
                    figure.turn();
                    figureX.turn();
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
        throw new UnsupportedOperationException("Not supported yet.");
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
    public boolean intersects(Figure f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector getBasePoint() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}