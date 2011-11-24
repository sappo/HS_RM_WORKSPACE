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
}
