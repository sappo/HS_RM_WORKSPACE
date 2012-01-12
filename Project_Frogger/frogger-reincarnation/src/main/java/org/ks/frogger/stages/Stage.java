package org.ks.frogger.stages;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

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

  private int stageNo;

  private String stageName;

  private String stageObjective;

  private Image stageImage;

  private GameMode gameMode;

  private Stage(Builder builder) {
    this.stageRowList = builder.stageRowList;
    this.highscore = builder.highscore;
    this.stageNo = builder.stageNo;
    this.stageName = builder.stageName;
    this.stageObjective = builder.stageObjective;
    this.stageImage = builder.stageImage;
    this.gameMode = builder.gameMode;
    this.goldMedalEffort = builder.goldMedalEffort;
    this.silverMedalEffort = builder.silverMedalEffort;
    this.bronzeMedalEffort = builder.bronzeMedalEffort;
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

  public static class Builder {

    //Mandatory
    private int stageNo;

    //Optional 
    private GameMode gameMode;

    private long goldMedalEffort;

    private long silverMedalEffort;

    private long bronzeMedalEffort;

    private List<StageRow> stageRowList;

    private String stageName;

    private String stageObjective;

    private long highscore;

    private Image stageImage;

    public Builder(int stageNo, GameMode gameMode) {
      this.stageNo = stageNo;
      this.gameMode = gameMode;
      this.highscore = 0;
      this.stageRowList = new ArrayList<>();
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

    public Stage build() {
      return new Stage(this);
    }
  }
}
