package org.ks.math;

import org.ks.sf.math.Vektor;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kevin Sapper 2011
 */
public class VectorTest {

  Vektor v;

  Vektor w;

  double lambda;

  @Before
  public void setup() {
    v = new Vektor(1.0, 2.0);
    w = new Vektor(3.0, 4.0);
    lambda = 5.0;
  }

  @Test
  public void testAdd() {
    double expectedVectorX = 4.0;
    double expectedVectorY = 6.0;
    Vektor actualVector;

    actualVector = v.add(w);
    Assert.assertEquals(expectedVectorX, actualVector.getX());
    Assert.assertEquals(expectedVectorY, actualVector.getY());
  }

  @Test
  public void testMultipyLambda() {
    double expectedVectorX = 5.0;
    double expectedVectorY = 10.0;
    Vektor actualVector;

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
