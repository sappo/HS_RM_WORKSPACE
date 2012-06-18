package org.ks.viergewinnt;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.ks.frogger.ActionCommand;
import org.ks.viergewinnt.card.GameCard;
import org.ks.viergewinnt.card.StartCard;
import org.ks.viergewinnt.card.ui.Chip;

/**
 * This is the main frame of the application. Here is were it all starts ;)
 *
 * @author Kevin Sapper 2012
 */
@Singleton
public class Main extends JFrame implements ActionListener {

    private Container cards;

    @Inject
    private GameCard gameCard;

    @Inject
    private GameManager manager;

    @Inject
    private StartCard startCard;

    /**
     * Entry point for the program.
     *
     * @param event CDI event
     */
    public void main(@Observes ContainerInitialized event) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        setTitle("Vier Gewinnt © Kevin Sapper");
        setResizable(false);
        setPreferredSize(new Dimension(GameConstants.WIDTH, GameConstants.HEIGHT));
        setLayout(new CardLayout());
        // Center frame
        setLocationRelativeTo(null);

        initMenu();

        cards = getContentPane();
        initGameCard();
        initStartCard();

        switchToCard(startCard);

        setVisible(true);
    }

    private void initMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        bar.add(fileMenu);

        JMenuItem startItem = new JMenuItem("New Game");
        startItem.setActionCommand(ActionCommand.NEWGAME.getCommand());
        startItem.addActionListener(this);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand(ActionCommand.EXIT.getCommand());
        exitItem.addActionListener(this);

        fileMenu.add(startItem);
        fileMenu.add(exitItem);

        this.setJMenuBar(bar);
    }

    private void initGameCard() {
        gameCard.setName("gameCard");
        cards.add(gameCard, gameCard.getName());
    }

    private void initStartCard() {
        startCard.setName("startCard");
        cards.add(startCard, startCard.getName());
    }

    public void listenToRefresh(@Observes Chip chip) {
        pack();
    }

    private void switchToCard(JPanel panel) {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, panel.getName());
        pack();
    }

    /**
     * {
     *
     * @inheritDoc } This implementation evaluates the action commands for
     * multiple components and acts according to their specification.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (ActionCommand.getActionCommand(event.getActionCommand())) {
            case NEWGAME:
                actionNewGame(event);
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private void actionNewGame(ActionEvent event) {
        manager.startGame();
        gameCard.refreshGameObjects();
        switchToCard(gameCard);
    }

    public void listenToGameFinish(@Observes String winner) {
        startCard.setWinner(winner);
        switchToCard(startCard);
    }
}
