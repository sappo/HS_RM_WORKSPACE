package org.ks.frogger.cards.ui;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JComponent;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author Kevin Sapper 2011
 */
public class ImageComponent extends JComponent {

  private Image image;

  private int x;

  private int y;

  private boolean opaque = false;

  private float alpha;

  public ImageComponent(Image image) {
    this.image = image;
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    setBounds(0, 0, image.getWidth(this), image.getHeight(this));
    x = 0;
    y = 0;
  }

  public ImageComponent(Image image, int width, int height) {
    this.image = image;
    x = width;
    y = height;
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    setBounds(0, 0, image.getWidth(this) + width,
            image.getHeight(this) + height);
  }

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

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (opaque) {
      Graphics2D g2D = (Graphics2D) g;
      AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha);
      g2D.setComposite(ac);
      g2D.drawRect(10, 10, 300, 300);
    }
    g.drawImage(image, x, y, this);
  }

  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append(getPreferredSize()).
            append(getBounds());
    return builder.toString();
  }
}
