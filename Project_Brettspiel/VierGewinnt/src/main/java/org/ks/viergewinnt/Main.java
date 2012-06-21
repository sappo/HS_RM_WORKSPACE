package org.ks.viergewinnt;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.FileUtils;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.ks.frogger.ActionCommand;
import org.ks.viergewinnt.card.GameCard;
import org.ks.viergewinnt.card.StartCard;
import org.ks.viergewinnt.card.ui.Chip;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

    private JMenuItem saveItem;

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

        saveItem = new JMenuItem("Save Game");
        saveItem.setActionCommand(ActionCommand.SAVE.getCommand());
        saveItem.setEnabled(false);
        saveItem.addActionListener(this);

        JMenuItem loadItem = new JMenuItem("Load Game");
        loadItem.setActionCommand(ActionCommand.LOAD.getCommand());
        loadItem.addActionListener(this);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand(ActionCommand.EXIT.getCommand());
        exitItem.addActionListener(this);

        fileMenu.add(startItem);
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
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

    /**
     * Re-renders the all components
     *
     * @param chip
     */
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
            case SAVE:
                actionSaveGame();
                break;
            case LOAD:
                actionLoadGame();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private void actionNewGame(ActionEvent event) {
        manager.startGame();
        gameCard.refreshGameObjects();
        saveItem.setEnabled(true);
        switchToCard(gameCard);
    }

    public void listenToGameFinish(@Observes String winner) {
        startCard.setWinner(winner);
        saveItem.setEnabled(false);
        switchToCard(startCard);
    }

    private void actionSaveGame() {
        //create the Document
        Document doc = null;
        try {
            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docBF.newDocumentBuilder();
            doc = docB.newDocument();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Add root element to document
        Element root = doc.createElement("viergewinnt");
        doc.appendChild(root);

        // save the board
        Element columns = doc.createElement("columns");
        root.appendChild(columns);
        for (int i = 0; i < GameConstants.COLUMNS; i++) {
            byte[] bs = manager.getBoard()[i];

            Element col = doc.createElement("column");
            col.setAttribute("no", String.valueOf(i));
            columns.appendChild(col);
            for (byte b : bs) {
                Element row = doc.createElement("row");
                row.setAttribute("owner", Byte.toString(b));
                col.appendChild(row);
            }
        }

        Element turn = doc.createElement("turn");
        turn.setAttribute("player", String.valueOf(manager.isFirstPLayer() ? GameConstants.FIRST_PLAYER : GameConstants.SECOND_PLAYER));
        root.appendChild(turn);

        //set up a transformer
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans;
        try {
            trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            //create string from xml tree
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);

            FileUtils.write(new File("./savegame_1.xml"), sw.toString());
        } catch (IOException | TransformerConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actionLoadGame() {
        try {
            Document doc = null;
            DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docB = docBF.newDocumentBuilder();
            doc = docB.parse("./savegame_1.xml");

            XPath xpath = XPathFactory.newInstance().newXPath();
            byte[][] board = new byte[GameConstants.COLUMNS][GameConstants.ROWS];
            for (int i = 0; i < GameConstants.COLUMNS; i++) {
                NodeList res = (NodeList) xpath.evaluate("//columns/column[@no='" + i + "']/row", doc, XPathConstants.NODESET);
                for (int j = 0; j < res.getLength(); j++) {
                    Node row = res.item(j);
                    board[i][j] = Byte.parseByte(row.getAttributes().getNamedItem("owner").getNodeValue());
                }
            }

            NodeList res = (NodeList) xpath.evaluate("//turn", doc, XPathConstants.NODESET);
            byte turn = Byte.valueOf(res.item(0).getAttributes().getNamedItem("player").getNodeValue());

            // resume the game with loaded data
            manager.resumeGame(board, turn);
        } catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        gameCard.refreshGameObjects();
        saveItem.setEnabled(true);
        switchToCard(gameCard);
    }
}
