package org.ks.frogger.manager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ks.frogger.ActionCommand;
import org.ks.frogger.events.FroggerDeath;
import org.ks.frogger.events.GameOver;
import org.ks.frogger.events.LifeUpdate;
import org.ks.frogger.events.ScoreUpdate;
import org.ks.frogger.events.TimeData;
import org.ks.frogger.events.TimeUpdate;
import org.ks.frogger.gameobjects.FrogNest;
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
public class GameManager implements KeyListener, ActionListener {

  private static final int LEFT = 37;

  private static final int UP = 38;

  private static final int RIGHT = 39;

  private static final int DOWN = 40;

  private static final Vector froggerStartPos = new Vector(250, 50);

  private static final Vector froggerSize = new Vector(30, 30);

  private long remainingLives = 3;

  private long remainingFroggersToSave = 5;

  private int currentLevel = 1;

  @Inject
  private GameObjectContainer gameObjectContainer;

  @Inject
  private HighscoreManager highscoreManager;

  @Inject
  private TimeManager timeManager;

  @Inject
  @GameOver
  private Event<Long> gameOverEvent;

  @Inject
  @TimeUpdate
  private Event<TimeData> timeUpdateEvent;

  @Inject
  @ScoreUpdate
  private Event<Long> scoreUpdateEvent;

  @Inject
  @LifeUpdate
  private Event<Long> lifeUpdateEvent;

  private boolean gameOver = false;

  @PostConstruct
  public void initialize() {
  }

  private Frogger createFroggerAtStartPos() {
    return Frogger.newInstanceAtStart(
            new Rectangle(froggerStartPos, froggerSize));
  }

  private Frogger createFroggerInNest(FrogNest nest) {
    return Frogger.newInstanceInNest(froggerSize, nest);
  }

  private Streetobject createCar(int streetLvl) {
    return new Streetobject(new Rectangle(new Vector(-50, streetLvl * 50),
            new Vector(1, 0), new Vector(50, 25)));
  }

  private Waterobject createTreeTrunk(int waterLvl) {
    return new Waterobject(new Rectangle(new Vector(-50, waterLvl * 200),
            new Vector(2, 0), new Vector(50, 25)));
  }

  private FrogNest createFrogNest(int number) {
    return new FrogNest(new Rectangle(new Vector(100 * number, 350),
            new Vector(2, 0), new Vector(75, 75)));
  }

  /**
   * Starts a new game.
   *  @param frame the frame to start the game on
   */
  public KeyListener startGame(Dimension gamePanelSize) {
    gameObjectContainer.addBorder(gamePanelSize.height, gamePanelSize.width);

    gameObjectContainer.addFrogger(createFroggerAtStartPos());

    gameObjectContainer.addMobileGameObject(createCar(1));
    gameObjectContainer.addMobileGameObject(createCar(2));
    gameObjectContainer.addMobileGameObject(createCar(3));

    gameObjectContainer.addMobileGameObject(createTreeTrunk(1));

    gameObjectContainer.addFroggerNest(createFrogNest(1));
    gameObjectContainer.addFroggerNest(createFrogNest(2));
    gameObjectContainer.addFroggerNest(createFrogNest(3));
    gameObjectContainer.addFroggerNest(createFrogNest(4));
    gameObjectContainer.addFroggerNest(createFrogNest(5));
    
    lifeUpdateEvent.fire(remainingLives);
    scoreUpdateEvent.fire(highscoreManager.getHighScore());

    timeManager.addActionListener(this);
    timeManager.startTimer(currentLevel);
    return this;
  }

  /**
   * Ends the current game.
   */
  public void endGame() {
    gameObjectContainer.clear();
    gameObjectContainer.removeFrogger();
    remainingLives = 3;
    remainingFroggersToSave = 5;
    highscoreManager.resetHighscore();
    gameOver = false;
    timeManager.stopTimer();
  }

  private void prepareNextLevel() {
    currentLevel++;
    remainingFroggersToSave = 5;
    gameObjectContainer.resetFrogger();
    gameObjectContainer.clearFrogNestInvaders();
    timeManager.restartTimer(currentLevel);
  }

  public void listenToFroggerDeath(@Observes FroggerDeath death) {
    if (!gameOver) {
      if (--remainingLives > 0) {
        gameObjectContainer.resetFrogger();
        timeManager.restartTimer();
        lifeUpdateEvent.fire(remainingLives);
      } else {
        gameOver = true;
        gameOverEvent.fire(highscoreManager.getHighScore());
      }
    }
  }

  public void listenToFroggerInNest(@Observes FrogNest nest) {
    if (--remainingFroggersToSave > 0) {
      // adds an surviving Frogger to the nest.
      nest.addInvader(createFroggerInNest(nest));
      gameObjectContainer.resetFrogger();
      highscoreManager.updateHighscore(timeManager.getLevelDelay(), timeManager.
              getElapsedSeconds(), currentLevel);
      scoreUpdateEvent.fire(highscoreManager.getHighScore());
      timeManager.restartTimer();
      System.out.println("Froggers left to save " + remainingFroggersToSave);
    } else {
      highscoreManager.updateHighscore(30, 5, currentLevel);
      prepareNextLevel();
      System.out.println("Level " + currentLevel);
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
    if (!gameOver) {
      handleKeyInput(e.getKeyCode());
    }
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

  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case ActionCommand.TIMEUP:
        listenToFroggerDeath(new FroggerDeath(FroggerDeath.TIMEUP));
        break;
      case ActionCommand.TIMEUPDATE:
        timeUpdateEvent.fire(new TimeData(timeManager.getLevelDelay(),
                timeManager.getRemainingTime()));
        break;
    }
  }
}