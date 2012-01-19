package org.ks.frogger.cards.ui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author Kevin Sapper 2011
 */
public class TransparentBox extends JComponent {

  private float alpha;

  private int x;

  private int y;

  private int width;

  private int height;

  public TransparentBox(float alpha, int x, int y, int width, int height) {
    super();
    this.alpha = alpha;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    setBounds(x, y, width, height);
    setOpaque(true);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
            alpha);
    g2D.setComposite(ac);

    g2D.setColor(getBackground());
    g2D.fill(new Rectangle(x, y, width, height));
  }
}
