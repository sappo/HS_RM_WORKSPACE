package org.ks.frogger.gameobjects;

import com.google.common.base.Optional;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper 2011
 */
public class FrogNest extends GameObject {

  private Optional<GameObject> invader = Optional.absent();

  public FrogNest(Rectangle pictureBoundingBox) {
    super(pictureBoundingBox);
  }

  public FrogNest(Rectangle pictureBoundingBox, ImageIcon image) {
    super(pictureBoundingBox, image);
  }

  public void addInvader(GameObject invader) {
    this.invader = Optional.fromNullable(invader);
  }

  public void removeInvader() {
    this.invader = Optional.absent();
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    if (invader.isPresent()) {
      invader.get().draw(g);
    }
  }

  @Override
  public CollusionAction getCollusionAction() {
    CollusionAction action;
    if (invader.isPresent()) {
      action = invader.get().getCollusionAction();
    } else {
      action = CollusionAction.OBJECTIVEREACHED;
    }
    return action;
  }
}
