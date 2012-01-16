package org.ks.frogger.stages;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.Timer;
import org.jboss.weld.util.collections.ArraySet;
import org.ks.frogger.Helper.ImageHelper;
import org.ks.frogger.gameobjects.FrogNest;
import org.ks.frogger.gameobjects.GameObjectContainer;
import org.ks.frogger.gameobjects.Streetobject;
import org.ks.frogger.gameobjects.TargetGrassStrip;
import org.ks.frogger.gameobjects.Waterobject;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class StageManager implements ActionListener {

  @Inject
  private GameObjectContainer gameObjectContainer;

  private Set<Stage> stages;

  private Stage currentStage;

  private Timer timer;

  @PostConstruct
  public void initialize() {
    timer = new Timer(100, this);
    stages = new ArraySet<>();
  }

  public void setupStages(Component component) {
    setupAutobahnStage(ImageHelper.loadAndResize(component,
            "src/main/resources/pictures/autobahn.jpg", 250));
  }

  public void setupAutobahnStage(Image stageImage) {
    Stage autobahn = new Stage.Builder(1, GameMode.TIME).setStageName(
            "Autobahn").
            setStageObjective("Save as many frogs as possible in 1 Minute!").
            addStageImage(stageImage).
            setPlayerLives(3).
            setGoldMedalEffort(10).
            setSilverMedalEffort(7).
            setBronzeMedalEffort(5).
            addStageRow(new StageRow(1, 1)).
            addStageRow(new StageRow(2, 3)).
            addStageRow(new StageRow(3, -1)).
            addStageRow(new StageRow(4, -2)).
            addStageRow(new StageRow(6, 1)).
            addStageRow(new StageRow(7, 2)).
            addStageRow(new StageRow(8, -1)).
            addStageRow(new StageRow(9, -3)).
            build();

    stages.add(autobahn);
    currentStage = autobahn;

    gameObjectContainer.addImmobileGameobject(new TargetGrassStrip(new Rectangle(
            new Vector(0, 0), new Vector(500, 50))));
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (StageRow stageRow : currentStage.getStageRowList()) {
      int accleration = Math.abs(stageRow.getGameObjectAcceleration());
      long timePuffer = (50 / accleration) * 100;
      if (stageRow.getLastObjectAdded() + timePuffer < System.currentTimeMillis()) {
        Random random = new Random();
        int randomNo = random.nextInt(20);
        if (randomNo == 1) {
          gameObjectContainer.addMobileGameObject(createCar(
                  stageRow.getRowLevel(),
                  stageRow.getGameObjectAcceleration()));
          stageRow.setLastObjectAdded(System.currentTimeMillis());
        }
      }
    }
  }

  public Stage getCurrentStage() {
    return currentStage;
  }

  private Streetobject createCar(int streetLvl, int acceleration) {
    int yLevel = getLevel(streetLvl);
    int xLevel = acceleration > 0 ? -50 : 500;

    return new Streetobject(new Rectangle(new Vector(xLevel, yLevel),
            new Vector(acceleration, 0), new Vector(50, 30)));
  }

  private Waterobject createTreeTrunk(int waterLvl) {
    return new Waterobject(new Rectangle(new Vector(-50, waterLvl * 200),
            new Vector(2, 0), new Vector(50, 25)));
  }

  private FrogNest createFrogNest(int level) {
    int yLevel = getLevel(level);
    return new FrogNest(new Rectangle(new Vector(215, yLevel),
            new Vector(50, 50)));
  }

  private int getLevel(int level) {
    return 10 + (level * 50);
  }
}
