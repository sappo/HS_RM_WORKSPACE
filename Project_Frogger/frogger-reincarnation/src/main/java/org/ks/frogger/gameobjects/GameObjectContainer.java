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

  private List<FrogNest> frogNestList;

  private Frogger frogger;

  @Inject
  private Event<FroggerDeath> deathEvent;

  @Inject
  private Event<FrogNest> winEvent;

  @PostConstruct
  public void initialize() {
    this.border = new FigureContainer<>();
    this.mobileGameObjectList = new FigureContainer<>();
    this.immobileFigureList = new FigureContainer<>();
    this.frogNestList = new ArrayList<>(5);
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
   * @param gameObject 
   */
  public void addMobileGameObject(GameObject gameObject) {
    mobileGameObjectList.add(gameObject);
  }

  /**
   * Adds a gameobject which should not be moved.
   * @param gameObject 
   */
  public void addImmobileGameobject(GameObject gameObject) {
    immobileFigureList.add(gameObject);
  }

  public void addFroggerNest(FrogNest frogNest) {
    frogNestList.add(frogNest);
    immobileFigureList.add(frogNest);
  }

  public void draw(Graphics g) {
    frogger.draw(g);
//    border.draw(g);
    mobileGameObjectList.draw(g);
    immobileFigureList.draw(g);
  }

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
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
    frogger.moveLeft();
    checkFroggerIntersection();
  }

  public void moveFroggerRight() {
    if (frogger.isCarried()) {
      frogger.jumpOff();
    }
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
        winEvent.fire((FrogNest) intersectedFigure);
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