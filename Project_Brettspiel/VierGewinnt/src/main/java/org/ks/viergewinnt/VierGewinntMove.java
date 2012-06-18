package org.ks.viergewinnt;

/**
 *
 * @author Kevin Sapper Expression 2012
 */
public class VierGewinntMove {

    private byte column;

    public VierGewinntMove(byte column) {
        this.column = column;
    }

    public byte getColumn() {
        return column;
    }
    
    public boolean equals(String strColumn) {
        return column == Byte.parseByte(strColumn);
    }
}    
