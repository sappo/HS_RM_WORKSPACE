package org.ks.frogger.gameobjects;

import com.google.common.base.Optional;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.ks.frogger.events.FroggerDeath;
import org.ks.frogger.events.ObjectiveReached;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.FigureContainer;
import org.ks.sf.shape.Rectangle;

/**
 * Wrapper for all GameObjects that act on the current stage.
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class GameObjectContainer {

  private FigureContainer<GameObject> border;

  private FigureContainer<GameObject> mobileGameObjectList;

  private FigureContainer<GameObject> immobileFigureList;

  private List<FrogNest> frogNestList;

  private Frogger frogger;

  @Inject
  private Event<FroggerDeath> deathEvent;

  @Inject
  @ObjectiveReached
  private Event<GameObject> objectiveReachedEvent;

  /**
   * Executed after objects creation.
   */
  @PostConstruct
  public void initialize() {
    this.border = new FigureContainer<GameObject>();
    this.immobileFigureList = new FigureContainer<GameObject>();
    this.mobileGameObjectList = new FigureContainer<GameObject>();
    this.frogNestList = new ArrayList<FrogNest>(5);
  }

  /**
   * Add a frogger if null, else ignores the add.
   * @param frogger the frogger to add
   */
  public void addFrogger(Frogger frogger) {
    if (frogger != null) {
      this.frogger = frogger;
    }
  }

  /**
   * Add a new Border
   * @param height the height of the game panel
   * @param width the width of the game panel
   */
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
   * @param gameObject the mobile game object to add
   */
  public void addMobileGameObject(GameObject gameObject) {
    mobileGameObjectList.add(gameObject);
  }

  /**
   * Adds a gameobject which should not be moved.
   * @param gameObject the immobile game object to add
   */
  public void addImmobileGameobject(GameObject gameObject) {
    immobileFigureList.add(gameObject);
  }

  /**
   * Add the Frogger of this container to a nest.
   * @param frogNest the nest to add to
   */
  public void addFroggerNest(FrogNest frogNest) {
    frogNestList.add(frogNest);
    immobileFigureList.add(frogNest);
  }

  /**
   * Draw all objects.
   */
  public void draw(Graphics g) {
    border.draw(g);
    immobileFigureList.draw(g);
    if (frogger != null) {
      frogger.draw(g);
    }
    mobileGameObjectList.draw(g);
  }

  /**
   * Clear all GameObjects from all containers and references.
   */
  public void clear() {
    removeFrogger();
    border.clear();
    mobileGameObjectList.clear();
    immobileFigureList.clear();
    frogNestList.clear();
  }

  /**
   * Remove all invaders from the nests.
   */
  public void clearFrogNestInvaders() {
    for (FrogNest frogNest : frogNestList) {
      frogNest.removeInvader();
    }
  }

  /**
   * Remove the frogger.
   */
  public void removeFrogger() {
    frogger = null;
  }

  /**
   * Move all figures.
   */
  public void moveFigures() {
    if (frogger.isCarried()) {
      frogger.move();
    }
    mobileGameObjectList.move();
    checkGameObjectsWithinBorders();
    checkFroggerIntersection();
  }

  /**
   * Move frogger the the left.
   */
  public void moveFroggerLeft() {
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
    frogger.moveLeft();
    checkFroggerIntersection();
  }

  /**
   * Move frogger the the right.
   */
  public void moveFroggerRight() {
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
    frogger.moveRight();
    checkFroggerIntersection();
  }

  /**
   * Move frogger the the up.
   */
  public void moveFroggerUp() {
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
    frogger.moveUp();
    checkFroggerIntersection();
  }

  /**
   * Move frogger the the down.
   */
  public void moveFroggerDown() {
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
    frogger.moveDown();
    checkFroggerIntersection();
  }

  /**
   * Check if the Frogger intersects with other game objects.
   */
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
      case OBJECTIVEREACHED:
        objectiveReachedEvent.fire(intersectedFigure);
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