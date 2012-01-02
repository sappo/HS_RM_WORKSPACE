package org.ks.frogger.gameobjects;

import com.google.common.base.Optional;
import java.awt.Graphics;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.ks.frogger.events.FroggerDeath;
import org.ks.sf.shape.Figure;
import org.ks.sf.shape.FigureContainer;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class GameObjectContainer {

  private FigureContainer<GameObject> mobileFigureList;

  private FigureContainer<GameObject> immobileFigureList;

  private Frogger frogger;

  @Inject
  private Event<FroggerDeath> deathEvent;

  @PostConstruct
  public void initialize() {
    this.mobileFigureList = new FigureContainer<>();
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

  /**
   * Add a gameobject which can be moved.
   * @param gameobject 
   */
  public void addMobileGameobject(GameObject gameobject) {
    mobileFigureList.add(gameobject);
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
    mobileFigureList.draw(g);
  }

  public void clear() {
    mobileFigureList.clear();
  }

  public void removeFrogger() {
    frogger = null;
  }

  public void moveFigures() {
    mobileFigureList.move();
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
    frogger.moveUp();
    checkFroggerIntersection();
  }

  public void moveFroggerDown() {
    frogger.moveDown();
    checkFroggerIntersection();
  }

  public void checkFroggerIntersection() {
    Optional<GameObject> intersectedFigure = mobileFigureList.
            intersectContainerFigures(frogger);
    intersectedFigure = intersectedFigure.isPresent() ? intersectedFigure : immobileFigureList.
            intersectContainerFigures(frogger);
    if (intersectedFigure.isPresent()) {
      switch (intersectedFigure.get().getCollusionAction()) {
        case KILL:
          FroggerDeath death = new FroggerDeath(FroggerDeath.OVERRUN);
          deathEvent.fire(death);
          break;
        case CARRY:
          break;
        case WIN:
          break;
      }
    }
  }

  /**
   * Resets the Frogger to his start position.
   */
  public void resetFrogger() {
    frogger.reset();
  }
}
