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

    @Test
    public void testCollusion() {
        Rectangle bb = new Rectangle(new Vector(0., 0.), new Vector(2., 3.));

        testRectangle(bb, new Rectangle(new Vector(-2., 1.), new Vector(1.,
                2.)), false);
        testRectangle(bb, new Rectangle(new Vector(-1., 3.), new Vector(-1.,
                -2.)), false);

        testRectangle(bb, new Rectangle(new Vector(1., -3.), new Vector(-1.,
                2.)), false);
        testRectangle(bb, new Rectangle(new Vector(0., -1.), new Vector(1.,
                -2.)), false);

        testRectangle(bb, new Rectangle(new Vector(-2., 1.), new Vector(2.,
                2.)), true);
        testRectangle(bb, new Rectangle(new Vector(0., 3.), new Vector(-2.,
                -2.)), true);

        testRectangle(bb, new Rectangle(new Vector(1., -3.), new Vector(1.,
                3.)), true);
        testRectangle(bb, new Rectangle(new Vector(2., 0.), new Vector(-1.,
                -3.)), true);

        testRectangle(bb, new Rectangle(new Vector(-2., -1.), new Vector(2.,
                1.)), true);
        testRectangle(bb, new Rectangle(new Vector(0., 0.), new Vector(-2.,
                -1.)), true);

        testRectangle(bb, new Rectangle(new Vector(2., 2.), new Vector(2.,
                -1.)), true);
        testRectangle(bb, new Rectangle(new Vector(0., 3.), new Vector(-2.,
                1.)), true);

        testRectangle(bb, new Rectangle(new Vector(-2., -1.), new Vector(3.,
                2.)), true);
        testRectangle(bb, new Rectangle(new Vector(1., 1.), new Vector(-3.,
                -2.)), true);

        testRectangle(bb, new Rectangle(new Vector(1., 2.), new Vector(2.,
                3.)), true);
        testRectangle(bb, new Rectangle(new Vector(3., 5.), new Vector(-2.,
                -3.)), true);

        testRectangle(bb, bb, true);

        testRectangle(bb, new Rectangle(new Vector(1., 1.), new Vector(.5,
                .5)), true);
        testRectangle(bb, new Rectangle(new Vector(1.5, 1.5), new Vector(
                -.5, -.5)), true);
        
        bb = new Rectangle(new Vector(50.0, 0.0), new Vector(10., 9.));
        testRectangle(bb, new Rectangle(new Vector(800., 0.),new Vector(-800.,0)), true);
    }

    private static void testRectangle(Rectangle bb1, Rectangle bb2,
            boolean expectedResult) {
        Assert.assertEquals(bb1.toString() + " intersects " + bb2.toString(),
                expectedResult, bb1.intersects(bb2));
        Assert.assertEquals(bb2.toString() + " intersects " + bb1.toString(),
                expectedResult, bb2.intersects(bb1));
    }
}