package org.ks.math.shape;

import junit.framework.Assert;
import org.junit.Test;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Triangle;

/**
 *
 * @author Kevin Sapper 2011
 */
public class TriangleTest {

    @Test
    public void testMass() {
        Vector basePoint = new Vector(0, 0);
        Vector v = new Vector(5, 5);
        Vector w = new Vector(5, 0);
        Triangle triangle = new Triangle(basePoint, v, w);
        Assert.assertEquals(12.5, triangle.getMass());
    }

    @Test
    public void testBoundingBox() {
        Vector basePoint = new Vector(285.5,212.5);
        Vector lineC = new Vector(37.5,-112.5);
        Vector lineB = new Vector(75.0,0.0);

        Triangle triangle = new Triangle(basePoint, lineC, lineB);
        
        Vector expectedBasePoint = new Vector(285.5,100.0);
        Vector expectedDiagonal = new Vector(75.0,112.5);

        Vector actualBasePoint = triangle.getBoundingBox().getBasePoint();
        Vector actualDiagonal = triangle.getBoundingBox().getDiagonal();

        Assert.assertEquals("base point x-coordinate",
                expectedBasePoint.getX(), actualBasePoint.getX());
        Assert.assertEquals("base point y-coordinate", expectedBasePoint.getY(),
                actualBasePoint.getY());
        Assert.assertEquals("diagonal x-coordinate", expectedDiagonal.getX(),
                actualDiagonal.getX());
        Assert.assertEquals("diagonal y-coordinate", expectedDiagonal.getY(),
                actualDiagonal.getY());
    }
}
