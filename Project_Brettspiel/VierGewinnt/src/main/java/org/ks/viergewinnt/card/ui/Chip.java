package org.ks.viergewinnt.card.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Chip a JComponent to represent a Chip for the game JPanel or other swing
 * container.
 *
 * @author Kevin Sapper 2011
 */
public class Chip extends JComponent {

    private Color color;

    private int size;

    private byte column;

    /**
     *
     *
     * @param image the image to wrap
     */
    private Chip(Color color, int size, byte column) {
        this.color = color;
        this.size = size;
        this.column = column;
        setPreferredSize(new Dimension(50, 50));
    }

    /**
     * {
     *
     * @inheritDoc} This implementation paints the image this components wraps.
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval(5, 5, size, size);
    }

    public byte getColumn() {
        return column;
    }

    /**
     *
     * @inheritDoc}
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append(getPreferredSize());
        return builder.toString();
    }

    public static Chip newInstanceNoPlayer(MouseListener listener, byte column) {
        Chip chip = new Chip(Color.WHITE, 90, column);
        chip.addMouseListener(listener);
        return chip;
    }

    public static Chip newInstancePlayer1(MouseListener listener, byte column) {
        Chip chip = new Chip(Color.RED, 90, column);
        chip.addMouseListener(listener);
        return chip;
    }

    public static Chip newInstancePlayer2(MouseListener listener, byte column) {
        Chip chip = new Chip(Color.YELLOW, 90, column);
        chip.addMouseListener(listener);
        return chip;
    }
}