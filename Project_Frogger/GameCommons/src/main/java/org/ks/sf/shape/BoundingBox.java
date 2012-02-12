package org.ks.sf.shape;

import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper
 */
public class BoundingBox {

  private Vector basePoint;

  private Vector diagonal;

  public BoundingBox(Vector basePoint, Vector diagonal) {
    this.basePoint = basePoint;
    this.diagonal = diagonal;
  }

  public Vector getBasePoint() {
    return basePoint;
  }

  public Vector getDiagonal() {
    return diagonal;
  }

  public boolean beruehrt(BoundingBox bb) {

    //Wenn sich eine Figur Links/rechts/unter/ueber der anderen Figur befindet, koennen sich die Figuren nicht beruehren.
    if (this.istLinksVon(bb) || bb.istLinksVon(this) || this.istUnter(bb) || bb.
            istUnter(this)) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Kontrolliert, ob eine BoundingBox links neben ihr ist.
   *
   * @return true, wenn die BoundingBox links daneben ist, sonst false.
   */
  public boolean istLinksVon(BoundingBox b) {
    //kleinster x-Wert der eigenen Boundingbox > groester X-Wert der anderen Boundingbox:  
    if (this.getBasePoint().
            getX() > b.getBasePoint().
            add(b.getDiagonal()).
            getX()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Kontrolliert, ob eine BoundingBox unter ihr ist.
   *
   * @return true, wenn die BoundingBox darunter ist, sonst false.
   */
  public boolean istUnter(BoundingBox b) {
    //kleinster y-Wert(Fuspunkt) der anderen Boundingbox > groester Y-Wert der eigenen Boundingbox (=Fusspunkt + Ausdehnung)
    if (b.getBasePoint().
            getY() > this.getBasePoint().
            add(this.getDiagonal()).
            getY()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Check intersect of the bounding boxes with the following conditions. <p>The
   * left edge of B is to the left of right edge of R.</p> <p>The top edge of B
   * is above the R bottom edge.</p> <p>The right edge of B is to the right of
   * left edge of R.</p> <p>The bottom edge of B is below the R upper edge.</p>
   *
   * @param that figure to compare this to.
   * @return true if all conditions are met, else false.
   */
  public final boolean intersects(Figure collidingFigure) {
    Vector thatPointC = getPointC(collidingFigure.getBoundingBox());
    Vector thisPointC = getPointC(this);
    return ((thisPointC.getX() >= collidingFigure.getBoundingBox().
            getBasePoint().
            getX())
            && (thisPointC.getY() >= collidingFigure.getBoundingBox().
            getBasePoint().
            getY())
            && (this.getBasePoint().
            getX() <= thatPointC.getX())
            && (this.getBasePoint().
            getY() <= thatPointC.getY()));
  }

  private Vector getPointC(BoundingBox boundingBox) {
    return boundingBox.getBasePoint().
            add(boundingBox.getDiagonal());
  }
}
