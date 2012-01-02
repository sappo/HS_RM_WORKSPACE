package org.ks.sf.draw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Figure;
import org.ks.sf.shape.FigureContainer;
import org.ks.sf.shape.Rectangle;
import org.ks.sf.shape.Triangle;

public class SpielFenster extends JFrame {

    private FigureContainer figures;

    private JPanel meinPanel = new JPanel() {

        public Dimension getPreferredSize() {
            return new Dimension(800, 600);
        }

        protected void paintComponent(Graphics g) {
            figures.draw(g);
        }
    };

    public SpielFenster(int kapazitaet) {
        figures = new FigureContainer();
        System.out.println(meinPanel);
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
        figures.move();
        repaint();
    }

    public void fuegeEin(Figure figur) {
        figures.add(figur);
        meinPanel.repaint();
    }

    public static void main(String[] args) {
        SpielFenster sf = new SpielFenster(20);
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 4; j++) {
                Vector fusspunkt = new Vector(i * 100., j * 100.);
                Vector geschwindigkeit = new Vector(
                        (Math.random() - .5) * 15.0, (Math.random() - .5) * 15.);
                if (Math.random() > .5) {
                    sf.fuegeEin(new Triangle(fusspunkt, geschwindigkeit,
                            new Vector(5. + Math.random() * 10., -5.
                            - Math.random() * 10.), new Vector(
                            5. + Math.random() * 20., 0.)));
                } else {
                    sf.fuegeEin(new Rectangle(fusspunkt, geschwindigkeit,
                            new Vector(5. + Math.random() * 10., 5. + Math.
                            random() * 10.)));
                }
            }
        }

        FigureContainer fb = new FigureContainer();
        Vector geschwindigkeit = new Vector(-10., 10.);
        fb.add(new Rectangle(new Vector(400., 400.), geschwindigkeit,
                new Vector(50., 50.)));
        fb.add(new Triangle(new Vector(400., 400.), geschwindigkeit, new Vector(
                50., 0.), new Vector(25., -25.)));
        fb.setCheckContainerCollusions(false);
        sf.fuegeEin(fb);
        
        sf.fuegeEin(new Rectangle(new Vector(0, 0), new Vector(0, 600)));
        sf.fuegeEin(new Rectangle(new Vector(0, 600), new Vector(800, 0)));
        sf.fuegeEin(new Rectangle(new Vector(800, 0), new Vector(0, 600)));
        sf.fuegeEin(new Rectangle(new Vector(0, 0), new Vector(800, 0)));
//        sf.fuegeEin(new Rectangle(new Vector(800, 600), new Vector(0, -600)));
//        sf.fuegeEin(new Rectangle(new Vector(800, 0), new Vector(-800, 0)));
    }
}