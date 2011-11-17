package org.ks.sf.math;

/**
 * Utility class for vector opperations.
 * @author Kevin Sapper 2011
 */
public class Vektor {

  private double x;

  private double y;

  public Vektor(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * <p>Sums up two vectors<p/>
   * 
   * <code>this.x + that.x</code><br/>
   * <code>this.y + that.y</code>
   * @param that the vector to add to this.
   * @return a new vector with the sum of boths.
   */
  public Vektor add(Vektor that) {
    return new Vektor(this.x + that.x, this.y + that.y);
  }

   /**
   * <p>Multiplies this vectors with lambda<p/>
   * 
   * <code>this.x * that.x</code>
   * <code>this.y * that.y</code>
   * @param that the vector to mutlipy with this.
   * @return a new vector with the result of the multipication.
   */
  public Vektor mult(double lambda) {
    return new Vektor(this.x * lambda, this.y * lambda);
  }

    /**
   * <p>Multiplies two vectors<p/>
   * 
   * <code>this.x * that.x</code> +
   * <code>this.y * that.y</code>
   * @param that the vector to mutlipy with this.
   * @return the sum of both vector multiplications
   */
  public double mult(Vektor that) {
    return (this.x * that.x) + (this.y * that.y);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
