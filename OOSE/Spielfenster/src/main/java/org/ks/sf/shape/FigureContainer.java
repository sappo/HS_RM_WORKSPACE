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

    private List<AbstractFigure> figureContainer;

    private int capacity;

    /** Creates a figure container with the passed capacity.
     * @param capacity initial capacity
     * @throws IllegalArgumentException if capacity is negative.
     */
    public FigureContainer(int capacity) throws IllegalArgumentException {
        this.figureContainer = new ArrayList<AbstractFigure>(capacity);
        this.capacity = capacity;
    }

    /** Adds a figure to the container.
     * @param figure to add
     * @throws IllegalArgumentException if figure is null
     * @throws SizeLimitExceededException if capacity is reached.
     */
    public void add(AbstractFigure figure) throws IllegalArgumentException {
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
    public AbstractFigure elementAt(int index) throws ArrayIndexOutOfBoundsException {
        return figureContainer.get(index);
    }

    /**
     * Move all figures in the container.
     */
    public void move() {
        for (AbstractFigure figure : figureContainer) {
            figure.move();
        }
    }

    /**
     * Paint all figures in the container.
     * @param g Graphics object to paint
     */
    public void draw(Graphics g) {
        for (AbstractFigure figure : figureContainer) {
            figure.draw(g);
        }
    }

    @Override
    public void turn() {
        for (AbstractFigure figureX : figureContainer) {
            for (AbstractFigure figureY : figureContainer) {
                if (figureX.isTouches(figureY)) {
                    figureX.turn();
                }
            }
        }
    }
    
    
    @Override
    public double getMass() {
        double mass = 0;
        for (AbstractFigure figure : figureContainer) {
            mass += figure.getMass();
        }
        return mass;
    }


    @Override
    public boolean isTouches(Figure f) {
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
        for (AbstractFigure figure : figureContainer) {
            content.append(figure.toString()).append(";");
        }
        content.append("}");
        return content.toString();
    }

    @Override
    public Rectangle getBoundingBox() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}