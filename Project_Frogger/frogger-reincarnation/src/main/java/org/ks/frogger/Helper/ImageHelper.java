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
 *
 * @author Kevin Sapper 2011
 */
public class ImageHelper {

    private static int i = 0;

    public static Image load(String path) {
        Image image = null;
        try {
            MediaTracker media = new MediaTracker(new JPanel());

            image = ImageIO.read(getFile(path));
            media.addImage(image, 0);
            media.waitForID(0);
        } catch (InterruptedException ex) {
        } catch (IOException ex) {
        }
        return image;
    }

    public static Image loadAndResizeWidth(String path, int width) {
        Image source = load(path);

        // force height to be a double
        double ratio = new Integer(width).doubleValue() / source.getWidth(
                null);
        int height = (int) (source.getHeight(null) * ratio);

        return loadAndResize(source, width, height);
    }

    public static Image loadAndResizeHeight(String path, int height) {
        Image source = load(path);

        // force height to be a double
        double ratio = new Integer(height).doubleValue() / source.getHeight(
                null);
        int width = (int) (source.getWidth(null) * ratio);

        return loadAndResize(source, width, height);
    }

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
        }
        return resizedImage;
    }

    private static File getFile(String path) {
        File file = new File(FileUtils.getTempDirectoryPath() + "frog-rein-tmp.file" + i++);
        file.deleteOnExit();
        String newPath = path.substring(1);
        try {
            FileUtils.copyInputStreamToFile(ClassLoader.
                    getSystemResourceAsStream(newPath), file);
        } catch (IOException ex) {
            Logger.getLogger(ImageHelper.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return file;
    }
}