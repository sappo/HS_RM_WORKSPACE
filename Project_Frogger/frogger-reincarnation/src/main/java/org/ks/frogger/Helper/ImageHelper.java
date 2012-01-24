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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import org.apache.commons.io.FileUtils;

/**
 * Helper to load and resize images.
 *
 * @author Kevin Sapper 2011
 */
public class ImageHelper {

  private static int i = 0;

  /**
   * Loads an image from the param path. If the path is relative it's assumed
   * your're referring to the jar root directory.
   *
   * @param path where to load the image from
   * @return the image, on fail null
   */
  public static Image load(String path) {
    Image image = null;
    try {
      MediaTracker media = new MediaTracker(new JPanel());

      image = ImageIO.read(getFile(path));
      media.addImage(image, 0);
      media.waitForID(0);
    } catch (InterruptedException ex) {
      Logger.getLogger(ImageHelper.class.getName()).
              log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ImageHelper.class.getName()).
              log(Level.SEVERE, null, ex);
    }
    return image;
  }

  /**
   * Loads an image from the param path and resize it's width. The width height
   * ratio is preserved. If the path is relative it's assumed your're referring
   * to the jar root directory.
   *
   * @param path where to load the image from
   * @param width the width to resize to
   * @return the image, on fail null
   */
  public static Image loadAndResizeWidth(String path, int width) {
    Image source = load(path);

    // force height to be a double
    double ratio = new Integer(width).doubleValue() / source.getWidth(
            null);
    int height = (int) (source.getHeight(null) * ratio);

    return loadAndResize(source, width, height);
  }

  /**
   * Loads an image from the param path and resize it's height. The heigt width
   * ratio is preserved. If the path is relative it's assumed your're referring
   * to the jar root directory.
   *
   * @param path where to load the image from
   * @param height the height to resize to
   * @return the image, on fail null
   */
  public static Image loadAndResizeHeight(String path, int height) {
    Image source = load(path);

    // force height to be a double
    double ratio = new Integer(height).doubleValue() / source.getHeight(
            null);
    int width = (int) (source.getWidth(null) * ratio);

    return loadAndResize(source, width, height);
  }

  /**
   * Loads an image from the param path and resize it's height and width. This
   * may destroy the width / height ratio. If the path is relative it's assumed
   * your're referring to the jar root directory.
   *
   * @param path where to load the image from
   * @param width the width to resize to
   * @param heigt the height to resize to
   * @return the image, on fail null
   */
  public static Image loadAndResize(String path, int width, int heigt) {
    return loadAndResize(load(path), width, heigt);
  }

  private static Image loadAndResize(Image source, int width, int height) {
    Image resizedImage = null;
    Component component = new JPanel();
    try {
      MediaTracker media = new MediaTracker(component);

      ImageFilter replicate = new ReplicateScaleFilter(width, height);
      ImageProducer prod =
              new FilteredImageSource(source.getSource(), replicate);
      resizedImage = component.createImage(prod);
      media.addImage(resizedImage, 0);
      media.waitForID(0);
    } catch (InterruptedException ex) {
      Logger.getLogger(ImageHelper.class.getName()).
              log(Level.SEVERE, null, ex);
    }
    return resizedImage;
  }

  private static File getFile(String path) {
    File file = new File(
            FileUtils.getTempDirectoryPath() + "frog-rein-tmp.file" + i++);
    file.deleteOnExit();
    String newPath = path.substring(1);
    try {
      FileUtils.copyInputStreamToFile(ClassLoader.getSystemResourceAsStream(
              newPath), file);
    } catch (IOException ex) {
      Logger.getLogger(ImageHelper.class.getName()).
              log(Level.SEVERE, newPath, ex);
    }
    return file;
  }
}