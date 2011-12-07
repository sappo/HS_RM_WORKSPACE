package org.ks.math.shape;

import junit.framework.Assert;
import org.junit.Test;
import org.ks.sf.math.Vector;
import org.ks.sf.shape.Line;

/**
 *
 * @author Kevin Sapper
 */
public class LineTest {

    @Test
    public void testMass() {
        Vector basePoint = new Vector(1, 1);
        Vector lineVector = new Vector(4, 3);
        
        Line line = new Line(basePoint, lineVector);
        
        Assert.assertEquals(5.0, line.getMass());
    }

    @Test
    public void testBoundingBox() {
        Vector basePoint = new Vector(1, 1);
        Vector lineVector = new Vector(5, 5);

        Line line = new Line(basePoint, lineVector);

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
