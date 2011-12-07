package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 * 
 * @author Kevin Sapper 2011
 */
public class Line extends AbstractFigure {

    private final Vector line;

    private final Rectangle boundingBox;

    public Line(Vector basepoint, Vector line) {
        super(basepoint);
        this.line = line;
        boundingBox = intiBoundingBox();
    }

    public Line(Vector basePoint, Vector acceleration, Vector line) {
        super(basePoint, acceleration);
        this.line = line;
        boundingBox = intiBoundingBox();
    }

    private Rectangle intiBoundingBox() {
        return new Rectangle(getBasePoint(), line);
    }

    @Override
    public void draw(Graphics g) {
        int x1 = (int) getBasePoint().getX();
        int y1 = (int) getBasePoint().getY();
        Vector pointB = getBasePoint().add(line);
        int x2 = (int) pointB.getX();
        int y2 = (int) pointB.getY();
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String toString() {
        return "Line<(" + getBasePoint().getX() + "," + getBasePoint().getY() + ")," + "(" + line.
                getX() + "," + line.getY() + ")" + ">";
    }

    @Override
    public boolean isTouches(Figure f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getMass() {
        return 1 * line.getLength();
    }

    public Vector getLine() {
        return line;
    }

    @Override
    public Rectangle getBoundingBox() {
        return boundingBox;
    }
}
