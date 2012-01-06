package org.ks.sf.shape;

import com.google.common.base.Optional;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.ks.sf.math.Vector;

/** 
 * Ein Objekt der Klasse FigurBehaelter ist ein Behaelter fuer Figur-Objekte.
 * Ein FigurBehaelter wird mit einer initialen Kapazitaet angelegt. Wird beim
 * Einfuegen von Figur-Objekten in diesen Behaelter die urspruengliche
 * Kapazitaet ueberschritten, dann erweitert sich der Behaelter selbsttaetig.
 */
public class FigureContainer<FigureGeneric extends Figure> implements Figure, Collection<FigureGeneric> {

  private List<FigureGeneric> figureContainer;

  private boolean turnFiguresOnCollusion = false;

  /** Creates a figure new container.
   * @param capacity initial capacity
   * @throws IllegalArgumentException if capacity is negative.
   */
  public FigureContainer() throws IllegalArgumentException {
    this.figureContainer = new ArrayList<>();
  }

  /**
   * Removes all figures from the container.
   */
  public void clear() {
    figureContainer.clear();
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
  public FigureGeneric elementAt(int index) throws ArrayIndexOutOfBoundsException {
    return figureContainer.get(index);
  }

  /**
   * Move all figures in the container.
   */
  @Override
  public void move() {
    for (Figure figure : figureContainer) {
      figure.move();
      if (turnFiguresOnCollusion) {
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

  /**
   * tTrn the direction of all figures in the container around.
   */
  @Override
  public void turn() {
    for (Figure figure : figureContainer) {
      turn(figure);
    }
  }

  /**
   * Turn the direction for the figure passed.
   * @param figure to turn
   */
  private void turn(Figure figure) {
    figure.turn();
  }

  @Override
  public double getMass() {
    double mass = 0;
    for (Figure figure : figureContainer) {
      mass += figure.getMass();
    }
    return mass;
  }

  /**
   * This method check if the container's bounding box intersects with 
   * another figure's bounding box
   */
  @Override
  public boolean intersects(Figure collidingFigure) {
    boolean intersects = false;
    if (!figureContainer.isEmpty()) {
      intersects = getBoundingBox().intersects(collidingFigure);
    }
    return intersects;
  }

  /**
   * This mehtod checks if the passed figure intersects with one of the 
   * containers.
   * figure.
   * @param figure to check the container one's against
   * @return the intersected figure or null
   */
  public Optional<FigureGeneric> intersectContainerFigures(Figure figure) {
    FigureGeneric intersectedFigure = null;
    if (CollectionUtils.isNotEmpty(figureContainer)) {
      for (FigureGeneric containerFigure : figureContainer) {
        if (figure.intersects(containerFigure)) {
          intersectedFigure = containerFigure;
        }
      }
    }
    return Optional.fromNullable(intersectedFigure);
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
    BoundingBox boundingBox = new BoundingBox(new Vector(0, 0), new Vector(0, 0));
    if (!figureContainer.isEmpty()) {
      List<Double> xCoordinates = new ArrayList<Double>();
      List<Double> yCoordinates = new ArrayList<Double>();

      for (Figure figure : figureContainer) {
        boundingBox = figure.getBoundingBox();
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
    }
    return boundingBox;
  }

  public boolean isCheckContainerCollusions() {
    return turnFiguresOnCollusion;
  }

  public void setCheckContainerCollusions(boolean checkContainerCollusions) {
    this.turnFiguresOnCollusion = checkContainerCollusions;
  }

  @Override
  public boolean isEmpty() {
    return figureContainer.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return figureContainer.contains(o);
  }

  @Override
  public Iterator<FigureGeneric> iterator() {
    return figureContainer.iterator();
  }

  @Override
  public Object[] toArray() {
    return figureContainer.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return figureContainer.toArray(a);
  }

  @Override
  public boolean add(FigureGeneric figure) {
    if (figure == null) {
      return false;
    }
    figureContainer.add(figure);
    return true;
  }

  @Override
  public boolean remove(Object object) {
    return figureContainer.remove(object);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return figureContainer.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends FigureGeneric> c) {
    return figureContainer.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return figureContainer.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return figureContainer.retainAll(c);
  }
}