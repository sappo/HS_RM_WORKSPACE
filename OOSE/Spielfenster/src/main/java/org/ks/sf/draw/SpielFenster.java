package org.ks.sf.draw;

import java.awt.*;
import javax.swing.*;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Triangle;
import org.ks.sf.shape.Figure;
import org.ks.sf.shape.Line;
import org.ks.sf.shape.Rectangle;

public class SpielFenster extends JFrame {

  private Figure[] figuren;

  private int anzahlFiguren;

  private JPanel meinPanel = new JPanel() {

    public Dimension getPreferredSize() {
      return new Dimension(800, 600);
    }

    protected void paintComponent(Graphics g) {
      for (int i = 0; i < anzahlFiguren; i++) {
        figuren[i].zeichne(g);
      }
    }
  };

  public SpielFenster(int kapazitaet) {
    if (kapazitaet < 0) {
      kapazitaet = 0;
    }
    figuren = new Figure[kapazitaet];
    anzahlFiguren = 0;

    add(meinPanel);
    pack();
    setVisible(true);
  }

  public void fuegeEin(Figure figur) {
    if (anzahlFiguren == figuren.length) {
      return;
    }
    figuren[anzahlFiguren] = figur;
    anzahlFiguren++;
    meinPanel.repaint();
  }

  public static void main(String[] args) {
    SpielFenster sf = new SpielFenster(20);
    sf.fuegeEin(new Line(new Vector(100.0, 100.0), new Vector(100.0, 0.0)));
    sf.fuegeEin(new Line(new Vector(200.0, 100.0), new Vector(0.0, 200.0)));
    sf.fuegeEin(new Line(new Vector(200.0, 300.0), new Vector(-50.0, 25.0)));
    sf.fuegeEin(new Line(new Vector(150.0, 325.0), new Vector(-50.0, -25.0)));
    
    sf.fuegeEin(new Line(new Vector(285.5, 212.5), new Vector(-37.5, 112.5)));
    sf.fuegeEin(new Line(new Vector(360.5, 212.5), new Vector(37.5, 112.5)));

    sf.fuegeEin(new Triangle(new Vector(285.5, 212.5), new Vector(37.5, -112.5),
            new Vector(75.0, 0.0)));

    sf.fuegeEin(new Rectangle(new Vector(75.0, 75.0), new Vector(500.0, 200.0)));
  }
}