package org.ks.sf.draw;

import java.awt.*;
import javax.swing.*;
import org.ks.sf.math.Vektor;
import org.ks.sf.shape.Dreieck;
import org.ks.sf.shape.Figur;
import org.ks.sf.shape.Linie;
import org.ks.sf.shape.Rechteck;

public class SpielFenster extends JFrame {

  private Figur[] figuren;

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
    figuren = new Figur[kapazitaet];
    anzahlFiguren = 0;

    add(meinPanel);
    pack();
    setVisible(true);
  }

  public void fuegeEin(Figur figur) {
    if (anzahlFiguren == figuren.length) {
      return;
    }
    figuren[anzahlFiguren] = figur;
    anzahlFiguren++;
    meinPanel.repaint();
  }

  public static void main(String[] args) {
    SpielFenster sf = new SpielFenster(20);
    sf.fuegeEin(new Linie(new Vektor(100.0, 100.0), new Vektor(100.0, 0.0)));
    sf.fuegeEin(new Linie(new Vektor(200.0, 100.0), new Vektor(0.0, 200.0)));
    sf.fuegeEin(new Linie(new Vektor(200.0, 300.0), new Vektor(-50.0, 25.0)));
    sf.fuegeEin(new Linie(new Vektor(150.0, 325.0), new Vektor(-50.0, -25.0)));
    
    sf.fuegeEin(new Linie(new Vektor(285.5, 212.5), new Vektor(-37.5, 112.5)));
    sf.fuegeEin(new Linie(new Vektor(360.5, 212.5), new Vektor(37.5, 112.5)));

    sf.fuegeEin(new Dreieck(new Vektor(285.5, 212.5), new Vektor(37.5, -112.5),
            new Vektor(75.0, 0.0)));

    sf.fuegeEin(new Rechteck(new Vektor(75.0, 75.0), new Vektor(500.0, 200.0)));
  }
}