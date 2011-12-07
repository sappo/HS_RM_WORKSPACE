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
    
    boolean isTouches(Figure f);
    
    Vector getBasePoint();
    
    Vector getAcceleration();
    
    double getMass();
    
    Rectangle getBoundingBox();
}
