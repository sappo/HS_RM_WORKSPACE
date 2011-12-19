package org.ks.sf.shape;

import java.awt.Graphics;
import org.ks.sf.math.Vector;

/**
 *
 * @author Kevin Sapper 2011
 */
public interface Figure {

    void draw(Graphics g);

    void move();
    
    void turn();
    
    boolean intersects(Figure f);
    
    Vector getBasePoint();
    
    Vector getAcceleration();
    
    double getMass();
    
    BoundingBox getBoundingBox();
    
    boolean isAbove(Figure that);
    
    boolean isLeftOf(Figure that);
}
