package org.ks.viergewinnt.interfaces;

import java.util.Collection;

/**
 *
 * @author Eric Panitz
 * @param <M>
 */
public interface Game<M> {

    Collection<M> moves();

    Game<M> doMove(M m);

    boolean isFirstPLayer();

    boolean lastHasWon();
}