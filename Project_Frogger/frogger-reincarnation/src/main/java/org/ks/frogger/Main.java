package org.ks.frogger;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
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

@Singleton
public class Main extends JFrame implements ActionListener {

  @Inject
  private GameManager gameManager;

  @Inject
  private GameObjectContainer gameObjectContainer;

  @Inject
  private HighscoreManager highscoreManager;

  private Container mainCards;

  private Container levelCards;

  private OpeningCard openingCard;

  @Inject
  private StagesCard stagesCard;

  @Inject
  private GameCard gameCard;

  private JPanel gameMetaInfoPanel;

  private HighscoreCard highscoreCard;

  private Timer repaintTimer;

  @PostConstruct
  public void postConstruct() {
    System.out.println("post-construct");
  }

  public void main(@Observes ContainerInitialized event) {
    setVisible(true);
    setLayout(new CardLayout());
//    setPreferredSize(new Dimension(500, 600));


    mainCards = getContentPane();
    levelCards = new JPanel(new CardLayout());

    initOpeningCard();
    initGameCard();
    initStagesCard();
    initHighscoreCard();
    initTimer();

    switchToCard(openingCard);
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

  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case ActionCommand.NEWGAME:
        actionNewGame(event);
        break;
      case ActionCommand.STOPGAME:
        actionEndGame(event);
        break;
      case ActionCommand.SHOWHIGHSCORE:
        actionShowHighscore(event);
        break;
      case ActionCommand.SHOWOPENING:
        switchToCard(openingCard);
        break;
      case ActionCommand.SHOWSTAGES:
        switchToCard(stagesCard);
        break;
      case ActionCommand.EXIT:
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
    gamePanel.requestFocus();
//    switchToGamePanel();

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
    List<Highscore> topTen = highscoreManager.getTopTen();
    for (int i = 0; i < topTen.size(); i++) {
      Highscore highscore = topTen.get(i);

      JLabel name = new JLabel(highscore.getName());
      name.setBounds(86, 162 + (40 * i), 250, 40);
      name.setFont(new Font("Kristen ITC", Font.PLAIN, 20));

      JLabel score = new JLabel(String.valueOf(highscore.getHighscore()));
      score.setBounds(316, 162 + (40 * i), 90, 40);
      score.setHorizontalAlignment(SwingConstants.RIGHT);
      score.setFont(new Font("Kristen ITC", Font.PLAIN, 20));

      highscoreCard.add(name);
      highscoreCard.add(score);
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
    if (highscoreManager.isInTopTen()) {
      String name = JOptionPane.showInputDialog(
              "Enter your name for the highscore!");
      highscoreManager.submitHighscore(name);
    }

  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("pre-destroy");
  }

  private void switchToCard(JPanel panel) {
    CardLayout cardLayout = (CardLayout) mainCards.getLayout();
    cardLayout.show(mainCards, panel.getName());
//    getContentPane().removeAll();
//    add(panel);
//    panel.repaint();
//    panel.requestFocus();
    pack();
  }

  private void switchToGamePanel() {
    getContentPane().removeAll();;
    add(gameCard, BorderLayout.CENTER);
    add(gameMetaInfoPanel, BorderLayout.NORTH);
    gameCard.requestFocus();
    pack();
  }
}