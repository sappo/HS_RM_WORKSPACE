package org.ks.frogger.factory;

import java.awt.Image;
import org.ks.frogger.Helper.MobileGameObject;
import org.ks.frogger.Helper.ImageHelper;
import org.ks.frogger.Helper.ImmobileGameObject;
import org.ks.frogger.gameobjects.CollusionAction;
import org.ks.frogger.gameobjects.FrogNest;
import org.ks.frogger.gameobjects.Frogger;
import org.ks.frogger.gameobjects.GameObject;
import org.ks.frogger.gameobjects.Streetobject;
import org.ks.frogger.gameobjects.Strip;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Rectangle;

/**
 * This Factory creates new GameObjects.
 *
 * @author Kevin Sapper 2012
 */
public class GameObjectFactory {

  /**
   * Create a new Frogger at the passed start postition.
   *
   * @param startPosition the froggers start position
   * @return the new Frogger object.
   */
  public static Frogger createNewFroggerAt(Vector startPosition) {
    Image up = ImageHelper.load("/pictures/gameobjects/frogger_up_1.png");
    Image down = ImageHelper.load("/pictures/gameobjects/frogger_down_1.png");
    Image left = ImageHelper.load("/pictures/gameobjects/frogger_left_1.png");
    Image right = ImageHelper.load("/pictures/gameobjects/frogger_right_1.png");

    return new Frogger(new Rectangle(startPosition, MobileGameObject.FROGGER.
            getSize()), up, down,
            left, right);
  }

  /**
   * Create a new Frogger inside a FroggerNest.
   *
   * @param nest the nest the Frogger is sitting in.
   * @return the new Frogger object inside the nest.
   */
  public static Frogger createFroggerInNest(FrogNest nest) {
    return Frogger.newInstanceInNest(MobileGameObject.FROGGER.getSize(), nest);
  }

  /**
   * Create a new mobile GameObject.
   *
   * @param mobileGameObjectType the type of mobile game object to create.
   * @param lvl the level on the game panel.
   * @param acceleration the acceleration of the game object.
   * @return the new mobile game objects.
   */
  public static GameObject createMobileGameObject(
          MobileGameObject mobileGameObjectType, int lvl, int acceleration) {
    GameObject gameObject = null;
    switch (mobileGameObjectType) {
      case GREENCAR:
        gameObject = createCar(lvl, acceleration);
        break;
    }
    return gameObject;
  }

  private static Streetobject createCar(int streetLvl, int acceleration) {
    Image car = null;
    Vector carSize = MobileGameObject.GREENCAR.getSize();
    int yLevel = getLevel(streetLvl);
    int xLevel = 0;

    if (acceleration > 0) {
      xLevel = (int) (carSize.getX() * (-1));
      car = ImageHelper.load("/pictures/gameobjects/car_green_right.png");
    } else {
      xLevel = 500;
      car = ImageHelper.load("/pictures/gameobjects/car_green_left.png");
    }

    return new Streetobject(new Rectangle(new Vector(xLevel, yLevel),
            new Vector(acceleration, 0), carSize), car);
  }

  /**
   * Create a new immobile GameObject.
   *
   * @param mobileGameObjectType the type of mobile game object to create.
   * @param stageRowLvl the level of the stage row
   *
   */
  public static GameObject createImmobileGameObject(
          ImmobileGameObject immobileGameObject, int stageRowLvl) {
    Strip strip = null;
    switch (immobileGameObject) {
      case GRASSSTRIP:
        strip = createStrip(stageRowLvl, ImageHelper.load(
                "/pictures/gameobjects/grass.png"));
        strip.setCollusionAction(CollusionAction.NOTHING);
        break;
      case STREET:
        strip = createStrip(stageRowLvl, ImageHelper.load(
                "/pictures/gameobjects/highway_1.png"));
        strip.setCollusionAction(CollusionAction.NOTHING);
        break;
      case TARGETSTRIP:
        strip = createStrip(stageRowLvl, ImageHelper.load(
                "/pictures/gameobjects/grass.png"));
        strip.setCollusionAction(CollusionAction.OBJECTIVEREACHED);
        break;
      case WATER:
        strip = createStrip(stageRowLvl, ImageHelper.load(
                "/pictures/gameobjects/water_1.png"));
        strip.setCollusionAction(CollusionAction.KILL);
        break;
    }
    return strip;
  }

  private static Strip createStrip(int lvl, Image image) {
    return new Strip(new Rectangle(
            new Vector(0, lvl * 50), new Vector(image.getWidth(null),
            image.getHeight(null))), image);
  }

  private static int getLevel(int level) {
    return 10 + (level * 50);
  }
}
