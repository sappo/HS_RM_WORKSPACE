package org.ks.frogger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.ks.frogger.events.GameOver;
import org.ks.frogger.events.LifeUpdate;
import org.ks.frogger.events.ScoreUpdate;
import org.ks.frogger.events.TimeData;
import org.ks.frogger.events.TimeUpdate;
import org.ks.frogger.gameobjects.GameObjectContainer;
import org.ks.frogger.manager.GameManager;

@Singleton
public class Main extends JFrame implements ActionListener {

  @Inject
  private GameManager gameManager;

  @Inject
  private GameObjectContainer gameObjectContainer;

  private JMenuBar menu;

  private JMenu gameMenu;

  private JMenuItem newGameMenuItem;

  private JMenuItem stopGameMenuItem;

  private JMenuItem exitMenuItem;

  private JPanel startPanel;

  private JPanel gamePanel;

  private JPanel gameMetaInfoPanel;

  private JProgressBar progressBar;
  
  private JLabel lifeDataLabel;
  
  private JLabel scoreDataLabel;

  private JPanel gameOverPanel;

  private JLabel gameOverScoreLabel;

  private Timer repaintTimer;

  @PostConstruct
  public void postConstruct() {
    System.out.println("post-construct");
  }

  public void main(@Observes ContainerInitialized event) {
    setVisible(true);
    getContentPane().setLayout(new BorderLayout());

    initMenu();
    initStartPanel();
    initGamePanel();
    initGameMetaInfoPanel();
    initGameOverScreen();
    initTimer();

    switchToPanel(startPanel);
  }

  private void initTimer() {
    repaintTimer = new Timer(50, new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        gameObjectContainer.moveFigures();
        repaint();
      }
    });
  }

  private void initStartPanel() {
    startPanel = new JPanel();
    startPanel.setPreferredSize(new Dimension(500, 300));
  }

  private void initGamePanel() {
    gamePanel = new JPanel() {

      @Override
      public Dimension getPreferredSize() {
        return new Dimension(800, 600);
      }

      @Override
      protected void paintComponent(Graphics g) {
        gameObjectContainer.draw(g);
      }
    };
  }

  private void initGameMetaInfoPanel() {
    gameMetaInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    gameMetaInfoPanel.setPreferredSize(new Dimension(800, 30));
    gameMetaInfoPanel.setBackground(Color.LIGHT_GRAY);

    JLabel lifeLabel = new JLabel("Lives:");
    lifeDataLabel = new JLabel();

    JLabel timeLabel = new JLabel("Time:");

    progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
    progressBar.setBorderPainted(false);
    progressBar.setPreferredSize(new Dimension(250, 20));
    progressBar.setForeground(Color.YELLOW);
    progressBar.setBackground(Color.GRAY);
    progressBar.setMinimum(0);


    JLabel scoreLabel = new JLabel("Score:");
    scoreDataLabel = new JLabel();
    
    gameMetaInfoPanel.add(lifeLabel);
    gameMetaInfoPanel.add(lifeDataLabel);
    gameMetaInfoPanel.add(timeLabel);
    gameMetaInfoPanel.add(progressBar);
    gameMetaInfoPanel.add(scoreLabel);
    gameMetaInfoPanel.add(scoreDataLabel);
  }

  private void initGameOverScreen() {
    gameOverPanel = new JPanel();
    gameOverPanel.setPreferredSize(new Dimension(500, 300));

    JButton tryAgainButton = new JButton("Try again!");
    tryAgainButton.setActionCommand(ActionCommand.NEWGAME);
    tryAgainButton.addActionListener(this);
    gameOverPanel.add(tryAgainButton);

    gameOverPanel.add(new JButton("Highscore!"));

    gameOverScoreLabel = new JLabel();
    gameOverPanel.add(gameOverScoreLabel);
  }

  private void initMenu() {
    menu = new JMenuBar();

    gameMenu = new JMenu("Game");

    newGameMenuItem = new JMenuItem("New Game");
    newGameMenuItem.setActionCommand(ActionCommand.NEWGAME);
    newGameMenuItem.addActionListener(this);

    stopGameMenuItem = new JMenuItem("Stop Game");
    stopGameMenuItem.setActionCommand("stopGame");
    stopGameMenuItem.addActionListener(this);

    exitMenuItem = new JMenuItem("Exit");
    exitMenuItem.setActionCommand("exit");
    exitMenuItem.addActionListener(this);

    gameMenu.add(newGameMenuItem);
    gameMenu.add(stopGameMenuItem);
    gameMenu.add(exitMenuItem);

    menu.add(gameMenu);

    setJMenuBar(menu);
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
      case ActionCommand.EXIT:
        System.exit(0);
        break;
    }
  }

  private void actionNewGame(ActionEvent event) {
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

    switchToGamePanel();

    repaintTimer.start();
  }

  private void actionEndGame(ActionEvent event) {
    endGame();
    switchToPanel(startPanel);
  }

  public void listenToGameOver(@Observes @GameOver Long score) {
    endGame();
    System.out.println("Game over! Score" + score);
    gameOverScoreLabel.setText("Score " + score);
    switchToPanel(gameOverPanel);
  }

  public void listenToTimeUpdate(@Observes @TimeUpdate TimeData timeData) {
    progressBar.setMaximum(timeData.getMaxTime().intValue());
    progressBar.setValue(timeData.getRemainingTime().intValue());
    progressBar.setString(timeData.getRemainingTime().toString() + "s");
  }
  
  public void listenToLifeUpdate(@Observes @LifeUpdate Long lives) {
    lifeDataLabel.setText(lives.toString());
  }
  
  public void listenToScoreUpdate(@Observes @ScoreUpdate Long score) {
    scoreDataLabel.setText(score.toString());
  }

  private void endGame() {
    repaintTimer.stop();
    gameManager.endGame();
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("pre-destroy");
  }

  private void switchToPanel(JPanel panel) {
    getContentPane().removeAll();
    add(panel, BorderLayout.CENTER);
    panel.requestFocus();
    pack();
  }

  private void switchToGamePanel() {
    getContentPane().removeAll();;
    add(gamePanel, BorderLayout.CENTER);
    add(gameMetaInfoPanel, BorderLayout.NORTH);
    gamePanel.requestFocus();
    pack();
  }
}