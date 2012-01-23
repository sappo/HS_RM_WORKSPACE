package org.ks.frogger.stages;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.swing.Timer;
import org.jboss.weld.util.collections.ArraySet;
import org.ks.frogger.Helper.MobileGameObject;
import org.ks.frogger.Helper.ImageHelper;
import org.ks.frogger.Helper.ImmobileGameObject;
import org.ks.frogger.events.GameOver;
import org.ks.frogger.factory.GameObjectFactory;
import org.ks.frogger.gameobjects.GameObject;
import org.ks.frogger.gameobjects.GameObjectContainer;
import org.ks.frogger.manager.HighscoreManager;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
@ApplicationScoped
public class StageManager implements ActionListener {

    @Inject
    private GameObjectContainer gameObjectContainer;
    
    @Inject
    private HighscoreManager highscoreManager;

    private Set<Stage> stages;

    private Stage currentStage;

    private Timer timer;

    @PostConstruct
    public void initialize() {
        timer = new Timer(50, this);
        stages = new ArraySet<Stage>();
    }

    public void setupStages(Component component) {
        setupAutobahnStage(ImageHelper.load("/pictures/stages/stage1.png"));
    }

    public void setupAutobahnStage(Image stageImage) {
        MobileGameObject greenCar = MobileGameObject.GREENCAR;

        Stage autobahn = new Stage.Builder(1, GameMode.TIME).setStageName(
                "Autobahn").
                setStageObjective("Save as many frogs as possible in 1 Minute!").
                addStageImage(stageImage).
                setPlayerLives(5).
                setTimeout(100000).
                addHighscore(highscoreManager.getHighestScore(1)).
                setGoldMedalEffort(10).
                setSilverMedalEffort(7).
                setBronzeMedalEffort(5).
                setFroggerStartPos(new Vector(215, 510)).
                addStageRow(new StageRow(0, 0, null, ImmobileGameObject.TARGETSTRIP)).
                addStageRow(new StageRow(1, 1, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(2, 3, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(3, -1, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(4, -2, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(5, 0, null, ImmobileGameObject.GRASSSTRIP)).
                addStageRow(new StageRow(6, 1, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(7, 2, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(8, -1, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(9, -3, greenCar, ImmobileGameObject.STREET)).
                addStageRow(new StageRow(10, 0, null, ImmobileGameObject.GRASSSTRIP)).
                build();

        stages.add(autobahn);
        currentStage = autobahn;
    }

    public void initializeCurrentStage() {
        gameObjectContainer.addFrogger(GameObjectFactory.createNewFroggerAt(
                currentStage.getFroggerStartPos()));

        for (StageRow stageRow : currentStage.getStageRowList()) {
            if (stageRow.getImmobileGameObjectType() != null) {
                gameObjectContainer.addImmobileGameobject(GameObjectFactory.
                        createImmobileGameObject(stageRow.
                        getImmobileGameObjectType(),
                        stageRow.getRowLevel()));
            }
        }

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (StageRow stageRow : currentStage.getStageRowList()) {
            if (stageRow.getMobileGameObjectType() != null) {
                int accleration = Math.abs(stageRow.getGameObjectAcceleration());
                double movementFactor = stageRow.getMobileGameObjectSize().
                        getX() / accleration;
                long timePuffer = (int) movementFactor * 50;
                if (stageRow.getLastObjectAdded() + timePuffer < System.
                        currentTimeMillis()) {
                    Random random = new Random();
                    int randomNo = random.nextInt(100);
                    if (randomNo == 1) {
                        GameObject mobileGameObject = GameObjectFactory.
                                createMobileGameObject(
                                stageRow.getMobileGameObjectType(), stageRow.
                                getRowLevel(),
                                stageRow.getGameObjectAcceleration());
                        gameObjectContainer.addMobileGameObject(mobileGameObject);
                        stageRow.setLastObjectAdded(System.currentTimeMillis());
                    }
                }
            }
        }
    }

    public void listenToGameOver(@Observes @GameOver Long score) {
        timer.stop();
    }

    public Stage getCurrentStage() {
        return currentStage;
    }
}
