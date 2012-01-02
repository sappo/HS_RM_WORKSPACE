package org.ks.frogger;

import java.awt.Dimension;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.apache.commons.lang.ArrayUtils;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.ks.frogger.events.FroggerDeath;
import org.ks.frogger.events.GameOver;
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

  private JPanel gameOverPanel;
  
  private JPanel gameOverReasonLabel;

  private Timer repaintTimer;

  @PostConstruct
  public void postConstruct() {
    System.out.println("post-construct");
  }

  public void main(@Observes ContainerInitialized event) {
    setVisible(true);

    initMenu();
    initStartPanel();
    initGamePanel();
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

  private void initGameOverScreen() {
    gameOverPanel = new JPanel();
    gameOverPanel.setPreferredSize(new Dimension(500, 300));
    
    JButton tryAgainButton = new JButton("Try again!");
    tryAgainButton.setActionCommand(ActionCommand.NEWGAME);
    tryAgainButton.addActionListener(this);
    gameOverPanel.add(tryAgainButton);
    
    gameOverPanel.add(new JButton("Highscore!"));
    
    gameOverReasonLabel = new JPanel();
    gamePanel.add(gameOverReasonLabel);
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
        actionStopGame(event);
        break;
      case ActionCommand.EXIT:
        System.exit(0);
        break;
    }
  }

  private void actionNewGame(ActionEvent event) {
    KeyListener listener = gameManager.startGame();
    gamePanel.addKeyListener(listener);

    switchToPanel(gamePanel);

    repaintTimer.start();
  }

  private void actionStopGame(ActionEvent event) {
    endGame();
    switchToPanel(startPanel);
  }
  
  public void listenToGameOver(@Observes @GameOver FroggerDeath death){
    endGame();
    System.out.println("Game over because " + death.getReason());
    gameOverReasonLabel.setName(death.getReason());
    switchToPanel(gameOverPanel);
  }
  
  private void endGame() {
    repaintTimer.stop();
    gameManager.endGame();
    //remove the frogger key listener
    ArrayUtils.nullToEmpty(gamePanel.getComponentListeners());
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("pre-destroy");
  }

  private void switchToPanel(JPanel panel) {
    getContentPane().removeAll();
    add(panel);
    panel.requestFocus();
    pack();
  }
}
