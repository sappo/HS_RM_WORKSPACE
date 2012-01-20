package org.ks.frogger.stages;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Stage {

  private List<StageRow> stageRowList;

  private long highscore;

  private long goldMedalEffort;

  private long silverMedalEffort;

  private long bronzeMedalEffort;

  private long playerLives;

  private long timeout;

  private int stageNo;

  private String stageName;

  private String stageObjective;

  private Image stageImage;

  private GameMode gameMode;

  private Vector froggerStartPos;

  private Stage(Builder builder) {
    this.stageRowList = builder.stageRowList;
    this.highscore = builder.highscore;
    this.playerLives = builder.playerLives;
    this.timeout = builder.timeout;
    this.stageNo = builder.stageNo;
    this.stageName = builder.stageName;
    this.stageObjective = builder.stageObjective;
    this.stageImage = builder.stageImage;
    this.gameMode = builder.gameMode;
    this.goldMedalEffort = builder.goldMedalEffort;
    this.silverMedalEffort = builder.silverMedalEffort;
    this.bronzeMedalEffort = builder.bronzeMedalEffort;
    this.froggerStartPos = builder.froggerStartPos;
  }

  public boolean hasGoldMedal() {
    return highscore >= goldMedalEffort;
  }

  public boolean hasSilverMedal() {
    return highscore >= silverMedalEffort;
  }

  public boolean hasBronzeMedal() {
    return highscore >= bronzeMedalEffort;
  }

  public boolean isNextStageUnlocked() {
    return hasGoldMedal();
  }

  public boolean isFirstStage() {
    return stageNo == 1;
  }

  public List<StageRow> getStageRowList() {
    return stageRowList;
  }

  public GameMode getGameMode() {
    return gameMode;
  }

  public int getStageNo() {
    return stageNo;
  }

  public Image getStageImage() {
    return stageImage;
  }

  public String getStageName() {
    return stageName;
  }

  public long getHighscore() {
    return highscore;
  }

  public long getPlayerLives() {
    return playerLives;
  }

  public String getStageObjective() {
    return stageObjective;
  }

  public long getGoldMedalEffort() {
    return goldMedalEffort;
  }

  public long getSilverMedalEffort() {
    return silverMedalEffort;
  }

  public long getBronzeMedalEffort() {
    return bronzeMedalEffort;
  }

  public void setHighscore(long highscore) {
    this.highscore = highscore;
  }

  public Vector getFroggerStartPos() {
    return froggerStartPos;
  }
  
  public static class Builder {

    //Mandatory
    private int stageNo;

    private GameMode gameMode;

    //Optional 
    private long goldMedalEffort;

    private long silverMedalEffort;

    private long bronzeMedalEffort;

    private long playerLives;

    private long timeout;

    private List<StageRow> stageRowList;

    private String stageName;

    private String stageObjective;

    private long highscore;

    private Image stageImage;
    
    private Vector froggerStartPos;

    public Builder(int stageNo, GameMode gameMode) {
      this.stageNo = stageNo;
      this.gameMode = gameMode;
      this.highscore = 0;
      this.stageRowList = new ArrayList<StageRow>();
    }

    public Builder addStageRow(StageRow stageRow) {
      stageRowList.add(stageRow);
      return this;
    }

    public Builder setStageName(String stageName) {
      this.stageName = stageName;
      return this;
    }

    public Builder setStageObjective(String stageObjective) {
      this.stageObjective = stageObjective;
      return this;
    }

    public Builder setPlayerLives(long playerLives) {
      this.playerLives = playerLives;
      return this;
    }

    public Builder setTimeout(long timeout) {
      this.timeout = timeout;
      return this;
    }

    public Builder addHighscore(long highscore) {
      this.highscore = highscore;
      return this;
    }

    public Builder addStageImage(Image image) {
      this.stageImage = image;
      return this;
    }

    public Builder setGoldMedalEffort(long goldMedalEffort) {
      this.goldMedalEffort = goldMedalEffort;
      return this;
    }

    public Builder setSilverMedalEffort(long silverMedalEffort) {
      this.silverMedalEffort = silverMedalEffort;
      return this;
    }

    public Builder setBronzeMedalEffort(long bronzeMedalEffort) {
      this.bronzeMedalEffort = bronzeMedalEffort;
      return this;
    }
    
    public Builder setFroggerStartPos(Vector froggerStartPos) {
      this.froggerStartPos = froggerStartPos;
      return this;
    }

    public Stage build() {
      return new Stage(this);
    }
  }
}