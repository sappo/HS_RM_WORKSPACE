package org.ks.math.shape;

import org.ks.sf.math.Vector;
import org.ks.sf.shape.AbstractFigure;
import org.ks.sf.shape.Line;
import org.ks.sf.shape.Rectangle;
import org.ks.sf.shape.Triangle;

/**
 *
 * @author Kevin Sapper
 */
public class PseudoTest {

    private static void testeGibRectangle(AbstractFigure f,
            Rectangle expectedResult) {
        System.out.println(
                f + ".getRectangle(): " + f.getBoundingBox() + " [" + expectedResult + "]");
    }

    public static void main(String[] args) {

        testeGibRectangle(new Line(new Vector(100.0, 100.0), new Vector(1.0,
                0.0), new Vector(100.0, 0.0)),
                new Rectangle(new Vector(100., 100.), new Vector(100., 0.)));
        testeGibRectangle(new Line(new Vector(200.0, 100.0), new Vector(1.0,
                0.0), new Vector(0.0, 200.0)),
                new Rectangle(new Vector(200., 100.), new Vector(0., 200.)));
        testeGibRectangle(new Line(new Vector(200.0, 300.0), new Vector(1.0,
                0.0), new Vector(-50.0, 25.0)),
                new Rectangle(new Vector(200., 300.), new Vector(-50., 25.)));
        testeGibRectangle(new Line(new Vector(150.0, 325.0), new Vector(1.0,
                0.0), new Vector(-50.0, -25.0)),
                new Rectangle(new Vector(150., 325.), new Vector(-50., -25.)));

        testeGibRectangle(new Triangle(new Vector(285.5, 212.5), new Vector(0.0,
                -1.0), new Vector(37.5, -112.5), new Vector(75.0, 0.0)),
                new Rectangle(new Vector(285.5, 100.), new Vector(75., 112.5)));
        testeGibRectangle(new Triangle(new Vector(285.5, 212.5), new Vector(0.0,
                -1.0), new Vector(37.5, -112.5), new Vector(75.0, 75.0)),
                new Rectangle(new Vector(285.5, 100.), new Vector(75., 187.5)));

        testeGibRectangle(new Rectangle(new Vector(75.0, 75.0), new Vector(5.0,
                5.0), new Vector(500.0, 200.0)),
                new Rectangle(new Vector(75.0, 75.0), new Vector(500.0, 200.0)));
    }
}