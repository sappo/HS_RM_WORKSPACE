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

/**
 *
 * @author Kevin Sapper 2011
 */
public class ImageHelper {

  public static Image loadAndResize(Component component, String path, int width) {
    Image source = null;
    Image resizedImage = null;

    try {
      MediaTracker media = new MediaTracker(component);
      // java how-to image for example, can be JPG
      source = ImageIO.read(new File(path));
      media.addImage(source, 0);
      media.waitForID(0);
      // scale down to 300 in width
      int height = source.getHeight(component) * 250 / source.getWidth(component);
      ImageFilter replicate = new ReplicateScaleFilter(250, height);
      ImageProducer prod =
              new FilteredImageSource(source.getSource(), replicate);
      resizedImage = component.createImage(prod);
      media.addImage(resizedImage, 1);
      media.waitForID(1);
    } catch (InterruptedException | IOException ex) {
    }
    return resizedImage;
  }

  ;
  
  public static Image loadAndResize(String path, int width, int heigt) {
    return null;
  }
}
