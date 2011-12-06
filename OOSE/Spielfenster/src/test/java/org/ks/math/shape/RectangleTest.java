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
        Vector v = new Vector(4, 4);
        Rectangle rectangle = new Rectangle(basePoint, v);
        Assert.assertEquals(8.0, rectangle.getMass());
    }
}
