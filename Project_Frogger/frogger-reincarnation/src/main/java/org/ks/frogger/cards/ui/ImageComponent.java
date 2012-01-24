package org.ks.frogger.cards.ui;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JComponent;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The ImageComponent is a JComponent to represent and easily add an image the a
 * JPanel or other swing container.
 *
 * @author Kevin Sapper 2011
 */
public class ImageComponent extends JComponent {

  private Image image;

  private int x;

  private int y;

  private boolean opaque = false;

  private float alpha;

  /**
   * Create a new ImageComponent and wraps the parameter image.
   *
   * @param image the image to wrap
   */
  public ImageComponent(Image image) {
    this.image = image;
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    setBounds(0, 0, image.getWidth(this), image.getHeight(this));
    x = 0;
    y = 0;
  }

  /**
   * Create a new ImageComponent and wraps the parameter image.
   *
   * @param image the image to wrap
   * @param width width of the image
   * @param height of the image
   */
  public ImageComponent(Image image, int width, int height) {
    this.image = image;
    x = width;
    y = height;
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    setBounds(0, 0, image.getWidth(this) + width,
            image.getHeight(this) + height);
  }

  /**
   * Create a new ImageComponent and wraps the parameter image.
   *
   * @param image the image to wrap
   * @param width width of the image
   * @param height of the image
   * @param alpha the transparancy level of the image. Has to be between 0 and
   * 1.
   */
  public ImageComponent(Image image, int width, int height, float alpha) {
    this.image = image;
    x = width;
    y = height;
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    setBounds(0, 0, image.getWidth(this) + width,
            image.getHeight(this) + height);
    this.opaque = true;
    this.alpha = alpha;
  }

  /**
   * {@inheritDoc}
   * This implementation paints the image this components wraps.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (opaque) {
      Graphics2D g2D = (Graphics2D) g;
      AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
              alpha);
      g2D.setComposite(ac);
      g2D.drawRect(10, 10, 300, 300);
    }
    g.drawImage(image, x, y, this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append(getPreferredSize()).
            append(getBounds());
    return builder.toString();
  }
}
