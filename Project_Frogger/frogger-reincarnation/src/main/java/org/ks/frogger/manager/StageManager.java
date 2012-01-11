package org.ks.frogger.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.Timer;
import org.jboss.weld.util.collections.ArraySet;
import org.ks.frogger.gameobjects.FrogNest;
import org.ks.frogger.gameobjects.GameObjectContainer;
import org.ks.frogger.gameobjects.Streetobject;
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

  private Set<StageRow> stageRows;

  private Timer timer;

  @PostConstruct
  public void initialize() {
    timer = new Timer(100, this);
    stageRows = new ArraySet<>();
  }

  public void setupAutobahnStage() {
    stageRows.add(new StageRow(1, 1));
    stageRows.add(new StageRow(2, 3));
    stageRows.add(new StageRow(3, -1));
    stageRows.add(new StageRow(4, -2));

    stageRows.add(new StageRow(6, 1));
    stageRows.add(new StageRow(7, 2));
    stageRows.add(new StageRow(8, -1));
    stageRows.add(new StageRow(9, -3));
    
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (StageRow stageRow : stageRows) {
      int accleration = Math.abs(stageRow.getGameObjectAcceleration());
      long timePuffer = (50 / accleration) * 50 * 3;
      if (stageRow.getLastObjectAdded() + timePuffer < System.currentTimeMillis()) {
        gameObjectContainer.addMobileGameObject(createCar(stageRow.getRowLevel(),
                stageRow.getGameObjectAcceleration()));
        stageRow.setLastObjectAdded(System.currentTimeMillis());
      }
    }
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

  private FrogNest createFrogNest(int number) {
    return new FrogNest(new Rectangle(new Vector(100 * number, 350),
            new Vector(2, 0), new Vector(75, 75)));
  }

  private int getLevel(int level) {
    return 10 + (level * 50);
  }
}
