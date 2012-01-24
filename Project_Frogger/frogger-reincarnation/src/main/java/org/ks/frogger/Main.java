package org.ks.frogger;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.ks.frogger.cards.GameCard;
import org.ks.frogger.cards.HighscoreCard;
import org.ks.frogger.cards.OpeningCard;
import org.ks.frogger.cards.StagesCard;
import org.ks.frogger.events.GameOver;
import org.ks.frogger.gameobjects.GameObjectContainer;
import org.ks.frogger.manager.GameManager;
import org.ks.frogger.manager.Highscore;
import org.ks.frogger.manager.HighscoreManager;
import org.ks.frogger.stages.StageManager;

/**
 * Main frame off the game.
 *
 * @author Kevin Sapper 2012
 */
@Singleton
public class Main extends JFrame implements ActionListener {

  @Inject
  private GameManager gameManager;

  @Inject
  private GameObjectContainer gameObjectContainer;

  @Inject
  private HighscoreManager highscoreManager;

  @Inject
  private StageManager stageManager;

  private Container mainCards;

  private OpeningCard openingCard;

  @Inject
  private StagesCard stagesCard;

  @Inject
  private GameCard gameCard;

  private HighscoreCard highscoreCard;

  private Timer repaintTimer;

  @PostConstruct
  public void postConstruct() {
    System.out.println("post-construct");
  }

  /**
   * Entry point for the programm.
   *
   * @param event CDI event
   */
  public void main(@Observes ContainerInitialized event) {
    setTitle("Frogger Reincarnation © Kevin Sapper");
    setResizable(false);
    setLayout(new CardLayout());
    // Center frame
    setLocationRelativeTo(null);

    mainCards = getContentPane();

    initOpeningCard();
    initGameCard();
    initStagesCard();
    initHighscoreCard();
    initTimer();

    switchToCard(openingCard);

    setVisible(true);
  }

  private void initTimer() {
    repaintTimer = new Timer(50, new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        if (gameManager.isRunning()) {
          gameObjectContainer.moveFigures();
        }
        repaint();
      }
    });
  }

  private void initOpeningCard() {
    openingCard = new OpeningCard(this);
    mainCards.add(openingCard, openingCard.getName());
  }

  private void initGameCard() {
    gameCard.setName("gameCard");
    mainCards.add(gameCard, gameCard.getName());
  }

  private void initStagesCard() {
    stagesCard.addActionListener(this);
    stagesCard.setName("stagesCard");
    mainCards.add(stagesCard, stagesCard.getName());
  }

  private void initHighscoreCard() {
    highscoreCard = new HighscoreCard(this);
    mainCards.add(highscoreCard, highscoreCard.getName());
  }

  /**
   * {@inheritDoc }
   * This implementation evaluates the action commands for multiple components
   * and acts according to their specification.
   * @param event
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    switch (ActionCommand.getActionCommand(event.getActionCommand())) {
      case NEWGAME:
        actionNewGame(event);
        break;
      case STOPGAME:
        actionEndGame(event);
        break;
      case SHOWHIGHSCORE:
        actionShowHighscore(event);
        break;
      case SHOWOPENING:
        switchToCard(openingCard);
        break;
      case SHOWSTAGES:
        switchToCard(stagesCard);
        break;
      case EXIT:
        System.exit(0);
        break;
    }
  }

  private void actionNewGame(ActionEvent event) {
    JPanel gamePanel = gameCard.getGamePanel();
    KeyListener listener = gameManager.startGame(gamePanel.getPreferredSize());

    boolean addListener = true;
    for (KeyListener keyListener : gamePanel.getKeyListeners()) {
      if (keyListener.equals(listener)) {
        addListener = false;
      }
    }
    if (addListener) {
      gamePanel.addKeyListener(listener);
    }

    switchToCard(gameCard);

    repaintTimer.start();
  }

  private void actionEndGame(ActionEvent event) {
    if (gameManager.isRunning()) {
      endGame();
    }
    switchToCard(openingCard);
  }

  private void actionShowHighscore(ActionEvent event) {
    if (gameManager.isRunning()) {
      endGame();
    }
    List<Highscore> topTen = highscoreManager.getTopTen(1);
    if (CollectionUtils.isNotEmpty(topTen)) {
      highscoreCard.updateHighscore(topTen);
    }
    switchToCard(highscoreCard);
  }

  public void listenToGameOver(@Observes @GameOver Long score) {
    endGame();
    System.out.println("Game over! Score" + score);
    switchToCard(stagesCard);
  }

  private void endGame() {
    repaintTimer.stop();
    gameManager.endGame();
    int stageNo = stageManager.getCurrentStage().
            getStageNo();
    if (highscoreManager.isInTopTen(stageNo)) {
      String name = JOptionPane.showInputDialog(
              "Enter your name for the highscore!");
      highscoreManager.submitHighscore(stageNo, name);
    }

  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("pre-destroy");
  }

  private void switchToCard(JPanel panel) {
    CardLayout cardLayout = (CardLayout) mainCards.getLayout();
    cardLayout.show(mainCards, panel.getName());
    System.out.println(panel);
    pack();
  }
}