package org.ks.viergewinnt.interfaces;

import java.util.List;

/**
 *
 * @author Sven Eric Panitz
 * @param <M>
 */
public interface Game<M> {

    List<M> moves();

    Game<M> doMove(M m);

    boolean isFirstPLayer();

    boolean otherPlayerHasWon();
}