package org.ks.math;

import org.ks.sf.math.Vector;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kevin Sapper 2011
 */
public class VectorTest {

    Vector v;

    Vector w;

    double lambda;

    @Before
    public void setup() {
        v = new Vector(1.0, 2.0);
        w = new Vector(3.0, 4.0);
        lambda = 5.0;
    }

    @Test
    public void testAdd() {
        double expectedVectorX = 4.0;
        double expectedVectorY = 6.0;
        Vector actualVector;

        actualVector = v.add(w);
        Assert.assertEquals(expectedVectorX, actualVector.getX());
        Assert.assertEquals(expectedVectorY, actualVector.getY());
    }

    @Test
    public void testSubtract() {
        double expectedVectorX = 2.0;
        double expectedVectorY = 2.0;
        Vector actualVector;

        actualVector = w.subtract(v);
        Assert.assertEquals(expectedVectorX, actualVector.getX());
        Assert.assertEquals(expectedVectorY, actualVector.getY());
    }

    @Test
    public void testMultipyLambda() {
        double expectedVectorX = 5.0;
        double expectedVectorY = 10.0;
        Vector actualVector;

        actualVector = v.mult(lambda);
        Assert.assertEquals(expectedVectorX, actualVector.getX());
        Assert.assertEquals(expectedVectorY, actualVector.getY());
    }

    @Test
    public void testMultiply() {
        double expected = 11.0;
        double actual;

        actual = v.mult(w);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLenght() {
        double expectedLenght = 5.0;
        Vector actualVector = new Vector(4, 3);

        Assert.assertEquals(expectedLenght, actualVector.getLength());
    }
    
    @Test
    public void testInverse() {
        double expectedX = -2.;
        double expectedY = -3.;
        Vector v = new Vector(2, 3);
        Vector inversed = v.inverse();
        Assert.assertEquals(expectedX, inversed.getX());
        Assert.assertEquals(expectedY, inversed.getY());
    }
}