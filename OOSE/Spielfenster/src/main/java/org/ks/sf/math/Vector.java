package org.ks.sf.math;

/**
 * Utility class for vector operations.
 * @author Kevin Sapper 2011
 */
public class Vector {

    private final double x;

    private final double y;

    private double length;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * <p>Sums up two vectors<p/>
     * 
     * <code>this.x + that.x</code><br/>
     * <code>this.y + that.y</code>
     * @param that the vector to add to this.
     * @return a new vector with the sum of both.
     */
    public Vector add(Vector that) {
        return new Vector(this.x + that.x, this.y + that.y);
    }

    /**
     * <p>Multiplies this vectors with lambda<p/>
     * 
     * <code>this.x * that.x</code>
     * <code>this.y * that.y</code>
     * @param that the vector to multiply with this.
     * @return a new vector with the result of the multiplication.
     */
    public Vector mult(double lambda) {
        return new Vector(this.x * lambda, this.y * lambda);
    }

    /**
     * <p>Multiplies two vectors<p/>
     * 
     * <code>this.x * that.x</code> +
     * <code>this.y * that.y</code>
     * @param that the vector to multiply with this.
     * @return the sum of both vector multiplications
     */
    public double mult(Vector that) {
        return (this.x * that.x) + (this.y * that.y);
    }

    /**
     * Inverses this Vector on a given base point.
     * @param basePoint the base point Vector
     * @return the inversed Vector
     */
    public Vector inverse(Vector basePoint) {
        return new Vector(basePoint.getX() - this.getX(), basePoint.getY() - this.getY());
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
