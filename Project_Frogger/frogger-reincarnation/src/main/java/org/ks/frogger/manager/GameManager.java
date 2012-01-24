package org.ks.frogger.manager;

import org.ks.frogger.stages.StageManager;
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
import org.ks.frogger.events.LifeUpdate;
import org.ks.frogger.events.TimeOut;
import org.ks.frogger.factory.GameObjectFactory;
import org.ks.frogger.gameobjects.FrogNest;
import org.ks.frogger.gameobjects.GameObject;
import org.ks.frogger.gameobjects.GameObjectContainer;
import org.ks.frogger.stages.GameMode;
import org.ks.frogger.stages.Stage;

/**
 * Starts, ends and delegates lives and scores
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class GameManager implements KeyListener {

  private static final int LEFT = 37;

  private static final int UP = 38;

  private static final int RIGHT = 39;

  private static final int DOWN = 40;

  private static final int ESC = 27;

  private GameMode gameMode;

  private long remainingLives = 3;

  private long savedFroggers = 5;

  private int currentLevel = 1;

  @Inject
  private GameObjectContainer gameObjectContainer;

  @Inject
  private HighscoreManager highscoreManager;

  @Inject
  private TimeManager timeManager;

  @Inject
  private StageManager stageManager;

  @Inject
  @GameOver
  private Event<Long> gameOverEvent;

  @Inject
  @LifeUpdate
  private Event<Long> lifeUpdateEvent;

  private boolean running = false;

  /**
   * Starts a new game.
   *
   * @param frame the frame to start the game on
   */
  public KeyListener startGame(Dimension gamePanelSize) {
    gameObjectContainer.addBorder(gamePanelSize.height, gamePanelSize.width);

//    gameObjectContainer.addFrogger(createFroggerAtStartPos());

    Stage currentStage = stageManager.getCurrentStage();
    gameMode = currentStage.getGameMode();
    remainingLives = currentStage.getPlayerLives();

    stageManager.initializeCurrentStage();

    lifeUpdateEvent.fire(remainingLives);

    highscoreManager.newHighscore();

    switch (gameMode) {
      case SURVIVAL:
        timeManager.startTimer(currentLevel);
        break;
      case TIME:
        timeManager.startTimer(currentStage.getTimeout());
    }

    running = true;
    return this;
  }

  /**
   * Ends the current game.
   */
  public void endGame() {
    gameObjectContainer.clear();
    gameObjectContainer.removeFrogger();
    savedFroggers = 0;
    running = false;
    timeManager.stopTimer();
  }

  private void prepareNextLevel() {
    currentLevel++;
    savedFroggers = 5;
    gameObjectContainer.resetFrogger();
    gameObjectContainer.clearFrogNestInvaders();
    timeManager.restartTimer(currentLevel);
  }

  /**
   * Listen to an CDI-Event that has the FroggerDeath parameter Decrements the
   * remaining lives.
   *
   * @param death CDI-Event
   */
  public void listenToFroggerDeath(@Observes FroggerDeath death) {
    if (running) {
      if (--remainingLives > 0) {
        gameObjectContainer.resetFrogger();
        lifeUpdateEvent.fire(remainingLives);
        if (gameMode.equals(GameMode.SURVIVAL)) {
          timeManager.restartTimer();
        }
      } else {
        gameOverEvent.fire(highscoreManager.getHighScore());
      }
    }
  }

  /**
   * Listen to an CDI-Event that has the FroggerDeath parameter and the
   * classifier @TimeOut. Act according to game mode.
   *
   * @param death CDI-Event
   * {@link TimeOut}
   */
  public void listenToFroggerDeathByTimeOut(
          @Observes @TimeOut FroggerDeath death) {
    if (running) {
      switch (gameMode) {
        case SURVIVAL:
          listenToFroggerDeath(death);
          break;
        case TIME:
          gameOverEvent.fire(savedFroggers);
          break;
      }
    }
  }

  /**
   * Listen to an CDI-Event that has the FroggerDeath parameter Decrements the
   * remaining lives.
   *
   * @param triggeringObject CDI-Event
   */
  public void listenToFroggerAtGoal(@Observes GameObject triggeringObject) {
    savedFroggers++;
    switch (gameMode) {
      case SURVIVAL:
        if (savedFroggers < 5) {
          FrogNest nest = (FrogNest) triggeringObject;
          // adds an surviving Frogger to the nest.
          nest.addInvader(GameObjectFactory.createFroggerInNest(nest));
          gameObjectContainer.resetFrogger();
          highscoreManager.updateSurvivalHighscore(timeManager.getLevelDelay(),
                  timeManager.getElapsedSeconds(), currentLevel);
          timeManager.restartTimer();
          System.out.println("Froggers left to save " + (5 - savedFroggers));
        } else {
          highscoreManager.updateSurvivalHighscore(30, 5, currentLevel);
          prepareNextLevel();
          System.out.println("Level " + currentLevel);
        }
        break;
      case TIME:
        highscoreManager.updateTimeHighscore();
        gameObjectContainer.resetFrogger();
        break;
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
    if (isRunning()) {
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
        case ESC:
          System.out.println("Not implemented yet!");
          break;
        default:
          System.out.println("Ignore key!");
      }
    }
  }

  /**
   * Checks if the game is currently running
   *
   * @return true if running, else false
   */
  public boolean isRunning() {
    return running;
  }
}
