package org.ks.math.shape;

import junit.framework.Assert;
import org.junit.Test;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Rectangle;

/**
 *
 * @author Kevin Sapper 2011
 */
public class RectangleTest {

    @Test
    public void testMass() {
        Vector basePoint = new Vector(2, 2);
        Vector v = new Vector(2, 2);
        Rectangle rectangle = new Rectangle(basePoint, v);
        Assert.assertEquals(8.0, rectangle.getMass());
    }

    @Test
    public void testBoundingBox() {
        Vector basePoint = new Vector(1, 1);
        Vector diagonal = new Vector(5, 5);

        Rectangle line = new Rectangle(basePoint, diagonal);

        Vector expectedBasePoint = new Vector(1, 1);
        Vector expectedDiagonal = new Vector(5, 5);

        Vector actualBasePoint = line.getBoundingBox().getBasePoint();
        Vector actualDiagonal = line.getBoundingBox().getDiagonal();

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
