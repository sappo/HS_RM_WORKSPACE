package org.ks.frogger.Helper;

import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper
 */
public enum MobileGameObject {

  FROGGER(new Vector(32, 32)), GREENCAR(new Vector(62, 30));

  private Vector size;

  private MobileGameObject(Vector size) {
    this.size = size;
  }

  public Vector getSize() {
    return size;
  }
}
