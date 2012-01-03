package org.ks.frogger.gameobjects;

import com.google.common.base.Optional;
import java.awt.Graphics;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.ks.frogger.events.FroggerDeath;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.FigureContainer;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class GameObjectContainer {

  private FigureContainer<GameObject> border;

  private FigureContainer<GameObject> mobileGameObjectList;

  private FigureContainer<GameObject> immobileFigureList;

  private Frogger frogger;

  @Inject
  private Event<FroggerDeath> deathEvent;

  @PostConstruct
  public void initialize() {
    this.border = new FigureContainer<>();
    this.mobileGameObjectList = new FigureContainer<>();
    this.immobileFigureList = new FigureContainer<>();
  }

  /**
   * Add a frogger if null, else ignores the add.
   * @param frogger 
   */
  public void addFrogger(Frogger frogger) {
    if (frogger != null) {
      this.frogger = frogger;
    }
  }

  public void addBorder(int height, int width) {
    // insert left border
    border.add(new BorderObject(new Rectangle(new Vector(0, 0), new Vector(0,
            height))));
    // insert bottom border
    border.add(new BorderObject(new Rectangle(new Vector(0, height), new Vector(
            width, 0))));
    // insert top border
    border.add(new BorderObject(new Rectangle(new Vector(0, 0), new Vector(width,
            0))));
    // insert right border
    border.add(new BorderObject(new Rectangle(new Vector(width, 0), new Vector(
            0, height))));
  }

  /**
   * Add a gameobject which can be moved.
   * @param gameobject 
   */
  public void addMobileGameObject(GameObject gameobject) {
    mobileGameObjectList.add(gameobject);
  }

  /**
   * Adds a gameobject which should not be moved.
   * @param gameobject 
   */
  public void addImmobileGameobject(GameObject gameobject) {
    immobileFigureList.add(gameobject);
  }

  public void draw(Graphics g) {
    frogger.draw(g);
//    border.draw(g);
    mobileGameObjectList.draw(g);
    immobileFigureList.draw(g);
  }

  public void clear() {
    mobileGameObjectList.clear();
  }

  public void removeFrogger() {
    frogger = null;
  }

  public void moveFigures() {
    if (frogger.isCarried()) {
      frogger.move();
    }
    mobileGameObjectList.move();
    checkGameObjectsWithinBorders();
    checkFroggerIntersection();
  }

  public void moveFroggerLeft() {
    frogger.moveLeft();
    checkFroggerIntersection();
  }

  public void moveFroggerRight() {
    frogger.moveRight();
    checkFroggerIntersection();
  }

  public void moveFroggerUp() {
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
    frogger.moveUp();
    checkFroggerIntersection();
  }

  public void moveFroggerDown() {
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
    frogger.moveDown();
    checkFroggerIntersection();
  }

  /**
   * Analyses the moving direction of a GameObject. Ignores up and down movement.
   * @param gameObject the GameObject to analyse the direction.
   * @return -1 if moving left, 1 if moving right and 0 if not moving
   */
  private int movingDirection(GameObject gameObject) {
    Vector acceleration = gameObject.getAcceleration();
    int direction = 0;
    if (acceleration != null) {
      if (acceleration.getY() == 0 && acceleration.getX() < 0) {
        direction = -1;
      }
      if (acceleration.getY() == 0 && acceleration.getX() > 0) {
        direction = 1;
      }
    }
    return direction;
  }

  public void checkFroggerIntersection() {
    //Prioritise object intersection
    //1st Priority - Border
    Optional<GameObject> intersectedFigure = border.intersectContainerFigures(
            frogger);
    if (intersectedFigure.isPresent()) {
      executeCollusionAction(intersectedFigure.get());
    } else {
      //2nd Priority - Mobile Figures
      intersectedFigure = mobileGameObjectList.intersectContainerFigures(frogger);
      if (intersectedFigure.isPresent()) {
        executeCollusionAction(intersectedFigure.get());
      } else {
        //3rd Priority - Immobile Figures
        intersectedFigure = immobileFigureList.intersectContainerFigures(frogger);
        if (intersectedFigure.isPresent()) {
          executeCollusionAction(intersectedFigure.get());
        }
      }
    }
  }

  private void executeCollusionAction(GameObject intersectedFigure) {
    switch (intersectedFigure.getCollusionAction()) {
      case KILL:
        FroggerDeath death = new FroggerDeath(FroggerDeath.OVERRUN);
        deathEvent.fire(death);
        break;
      case CARRY:
        frogger.jumpOn(intersectedFigure);
        break;
      case WIN:
        break;
    }
  }

  /**
   * Resets the Frogger to his start position.
   */
  public void resetFrogger() {
    frogger.reset();
  }

  private void checkGameObjectsWithinBorders() {
    for (Iterator<GameObject> it = mobileGameObjectList.iterator(); it.hasNext();) {
      GameObject gameObject = it.next();
      if (!gameObject.intersects(border)) {
        it.remove();
      }
    }
  }
}