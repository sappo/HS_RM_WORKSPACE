package org.ks.frogger.Helper;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Kevin Sapper 2011
 */
public class ImageHelper {

  public static Image load(Component component, String path) {
    Image image = null;
    try {
      MediaTracker media = new MediaTracker(component);

      image = ImageIO.read(new File(path));
      media.addImage(image, 0);
      media.waitForID(0);
    } catch (InterruptedException | IOException ex) {
    }
    return image;
  }

  public static Image load(String path) {
    Image image = null;
    try {
      MediaTracker media = new MediaTracker(new JPanel());

      image = ImageIO.read(new File(path));
      media.addImage(image, 0);
      media.waitForID(0);
    } catch (InterruptedException | IOException ex) {
    }
    return image;
  }

  public static Image loadAndResizeWidth(Component component, String path,
          int width) {
    Image source = load(component, path);

    // force height to be a double
    double ratio = new Integer(width).doubleValue() / source.getWidth(
            component);
    int height = (int) (source.getHeight(component) * ratio);

    return loadAndResize(component, source, width, height);
  }

  public static Image loadAndResizeHeight(Component component, String path,
          int height) {
    Image source = load(component, path);

    // force height to be a double
    double ratio = new Integer(height).doubleValue() / source.getHeight(
            component);
    int width = (int) (source.getWidth(component) * ratio);

    return loadAndResize(component, source, width, height);
  }

  public static Image loadAndResize(Component component, String path, int width,
          int heigt) {
    return loadAndResize(component, load(component, path), width, heigt);
  }

  private static Image loadAndResize(Component component, Image source,
          int width, int height) {
    Image resizedImage = null;

    try {
      MediaTracker media = new MediaTracker(component);

      ImageFilter replicate = new ReplicateScaleFilter(width, height);
      ImageProducer prod =
              new FilteredImageSource(source.getSource(), replicate);
      resizedImage = component.createImage(prod);
      media.addImage(resizedImage, 0);
      media.waitForID(0);
    } catch (InterruptedException ex) {
    }
    return resizedImage;
  }
}
