package org.ks.frogger.manager;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ks.frogger.events.FroggerDeath;
import org.ks.frogger.events.GameOver;
import org.ks.frogger.gameobjects.Frogger;
import org.ks.frogger.gameobjects.GameObjectContainer;
import org.ks.frogger.gameobjects.Streetobject;
import org.ks.frogger.gameobjects.Waterobject;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper
 */
@ApplicationScoped
public class GameManager implements KeyListener {

  private static final int LEFT = 37;

  private static final int UP = 38;

  private static final int RIGHT = 39;

  private static final int DOWN = 40;

  private int lives = 3;

  @Inject
  private GameObjectContainer gameObjectContainer;

  @Inject
  @GameOver
  private Event<FroggerDeath> gameOverEvent;

  private boolean gameOver = false;

  @PostConstruct
  public void initialize() {
  }

  private Frogger createFrooger() {
    return new Frogger(getFroggerStartPosition());
  }

  private Streetobject createCar(int streetLvl) {
    return new Streetobject(new Rectangle(new Vector(-50, streetLvl * 50),
            new Vector(1, 0), new Vector(50, 25)));
  }

  private Waterobject createTreeTrunk(int waterLvl) {
    return new Waterobject(new Rectangle(new Vector(-50, waterLvl * 200),
            new Vector(2, 0), new Vector(50, 25)));
  }

  /**
   * Starts a new game.
   *  @param frame the frame to start the game on
   */
  public KeyListener startGame(Dimension gamePanelSize) {
    Frogger frogger = createFrooger();
    
    gameObjectContainer.addBorder(gamePanelSize.height, gamePanelSize.width);

    gameObjectContainer.addFrogger(frogger);

    gameObjectContainer.addMobileGameObject(createCar(1));
    gameObjectContainer.addMobileGameObject(createCar(2));
    gameObjectContainer.addMobileGameObject(createCar(3));
    
    gameObjectContainer.addMobileGameObject(createTreeTrunk(1));

    return this;
  }

  public void endGame() {
    gameObjectContainer.clear();
    gameObjectContainer.removeFrogger();
    lives = 3;
    gameOver = false;
  }

  public Rectangle getFroggerStartPosition() {
    return new Rectangle(new Vector(250, 50),
            new Vector(25, 25));
  }

  public void listenToFroggerDeath(@Observes FroggerDeath death) {
    if (!gameOver) {
      if (--lives > 0) {
        gameObjectContainer.resetFrogger();
        System.out.println("Lives left " + lives);
      } else {
        gameOver = true;
        gameOverEvent.fire(death);
      }
    }
  }

  public void keyTyped(KeyEvent e) {
    //ignore event - not triggered by cursor keys
    e.consume();
  }

  public void keyPressed(KeyEvent e) {
    //ignore event - hinder steady movement by holding the key pressed.
  }

  public void keyReleased(KeyEvent e) {
    handleKeyInput(e.getKeyCode());
  }

  private void handleKeyInput(int keyCode) {
    switch (keyCode) {
      case RIGHT:
        gameObjectContainer.moveFroggerRight();
        break;
      case LEFT:
        gameObjectContainer.moveFroggerLeft();
        break;
      case UP:
        gameObjectContainer.moveFroggerUp();
        break;
      case DOWN:
        gameObjectContainer.moveFroggerDown();
        break;
      default:
        System.out.println("Ignore key!");
    }
  }
}
