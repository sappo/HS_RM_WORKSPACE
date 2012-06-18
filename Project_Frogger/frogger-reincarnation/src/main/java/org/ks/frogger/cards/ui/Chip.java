package org.ks.frogger.cards.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 * The TransparentBox is a JComponent to represent a transparent rectangle.
 *
 * @author Kevin Sapper 2012
 */
public class Chip extends JComponent {

  private float alpha;

  private int x;

  private int y;

  private int width;

  private int height;

  /**
   * Creates a new TransparentBox.
   *
   * @param alpha the transparancy level of the image. Has to be between 0 and
   * 1.
   * @param x base point x value
   * @param y base point y value
   * @param width width of the box
   * @param height height of the box
   */
  public Chip(float alpha, int x, int y, int width, int height) {
    super();
    this.alpha = alpha;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    setBounds(x, y, width, height);
    setOpaque(true);
  }

  /**
   * {@inheritDoc}
w   */
  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
            alpha);
    g2D.setComposite(ac);

    g2D.setColor(Color.GREEN);
    g2D.fill(new Rectangle(x, y, width, height));
  }
}
