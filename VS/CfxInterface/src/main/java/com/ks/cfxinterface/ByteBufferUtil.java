package com.ks.cfxinterface;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Convenience class to read from a ByteBuffer.
 *
 * @author Kevin Sapper (2012)
 */
public class ByteBufferUtil {

    /**
     * Reads from ByteBuffer charwise until the delimiter is found or ByteBuffer
     * has no remaining chars left.
     *
     * @param buffer StringBuffer to store read chars in.
     * @param byteBuffer ByteBuffer to read from.
     * @param delimiter delimiter to look for.
     * @param charset the Charset to use for decoding chars.
     *
     * @return true if a delimiter has been found else false
     *
     */
    public static boolean readChars(StringBuffer buffer, ByteBuffer byteBuffer, char delimiter, Charset charset) {
        boolean delimiterFound = false;
        if (byteBuffer.hasRemaining()) {
            try {
                // mark current postion
                byteBuffer.mark();
                    CharBuffer charBuffer = charset.newDecoder().decode(byteBuffer);
                
                while (charBuffer.hasRemaining() && !delimiterFound) {
                    char c = charBuffer.get();
                    if (c != delimiter) {
                        buffer.append(c);
                    } else {
                        delimiterFound = true;
                    }
                }
                // restore position
                byteBuffer.reset();
                // set position according to read chars
                byteBuffer.position(byteBuffer.position() + charBuffer.position());
            } catch (CharacterCodingException ex) {
                Logger.getLogger(ByteBufferUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return delimiterFound;
    }

    /**
     * Reads to hole ByteBuffer into the given StringBuffer until the ByteBuffer
     * has no remaining bytes left. Only characters A-Z, a-z, 0-9, -, and space
     * are read
     *
     * @param buffer StringBuffer to store read bytes in.
     * @param byteBuffer ByteBuffer to read from.
     *
     * @return the given StringBuffer with the read chars appended.
     *
     */
    public static void readBytes(StringBuffer buffer, ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining()) {
            char c = (char) byteBuffer.get();
            if ((c >= 'A' && c <= 'z') || c == ' ' || c == '-') {
                buffer.append(c);
            }
        }
    }
}
