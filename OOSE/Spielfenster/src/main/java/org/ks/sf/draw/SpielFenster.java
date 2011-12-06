package org.ks.sf.draw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.AbstractFigure;
import org.ks.sf.shape.FigureContainer;
import org.ks.sf.shape.Line;
import org.ks.sf.shape.Rectangle;
import org.ks.sf.shape.Triangle;

public class SpielFenster extends JFrame {

  private FigureContainer figures;

  private JPanel meinPanel = new JPanel() {

    public Dimension getPreferredSize() {
      return new Dimension(800, 600);
    }

    protected void paintComponent(Graphics g) {
      figures.zeichne(g);
    }
  };

  public SpielFenster(int kapazitaet) {
    figures = new FigureContainer(kapazitaet);

    add(meinPanel);
    pack();
    setVisible(true);

    Timer t = new Timer(100, new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        tick();
      }
    });
    t.start();
  }

  public void tick() {
    figures.bewege();
    repaint();
  }

  public void fuegeEin(AbstractFigure figur) {
    figures.add(figur);
    meinPanel.repaint();
  }

  public static void main(String[] args) {
    SpielFenster sf = new SpielFenster(20);
    sf.fuegeEin(new Line(new Vector(100.0, 100.0), new Vector(1.0, 0.0), new Vector(100.0, 0.0)));
    sf.fuegeEin(new Line(new Vector(200.0, 100.0), new Vector(1.0, 0.0), new Vector(0.0, 200.0)));
    sf.fuegeEin(new Line(new Vector(200.0, 300.0), new Vector(1.0, 0.0), new Vector(-50.0, 25.0)));
    sf.fuegeEin(new Line(new Vector(150.0, 325.0), new Vector(1.0, 0.0), new Vector(-50.0, -25.0)));

    sf.fuegeEin(new Triangle(new Vector(285.5, 212.5), new Vector(0.0, -1.0), new Vector(37.5, -112.5), new Vector(75.0, 0.0)));
    sf.fuegeEin(new Line(new Vector(285.5, 212.5), new Vector(0.0, -1.0), new Vector(-37.5, 112.5)));
    sf.fuegeEin(new Line(new Vector(360.5, 212.5), new Vector(0.0, -1.0), new Vector(37.5, 112.5)));

    sf.fuegeEin(new Rectangle(new Vector(75.0, 75.0), new Vector(5.0, 5.0), new Vector(500.0, 200.0)));
  }
}