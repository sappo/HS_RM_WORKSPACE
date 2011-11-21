package org.ks.woerterbuch;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Funktionen {

  public static int wortPosition(String[] woerter, String suchwort) {
    int pos = -1;

    int erstes = 0;
    int letztes = woerter.length - 1;

    while (erstes <= letztes) {
      int mitte = erstes + ((letztes - erstes) / 2);
      if (woerter[mitte].compareTo(suchwort) < 0) {
        erstes = mitte + 1;
      } else if (woerter[mitte].compareTo(suchwort) > 0) {
        letztes = mitte - 1;
      } else {
        return mitte;
      }
    }
    return pos;
  }

  public static int einfuegePosition(String[] woerter, String suchwort) {
    int pos = wortPosition(woerter, suchwort);
    if (pos != -1) {
      return -1;
    } else {
      int erstes = 0;
      int letztes = woerter.length - 1;

      while (erstes <= letztes) {
        pos = erstes + ((letztes - erstes) / 2);

        boolean biggerOrEqualsThanZero = pos == 0 ? true : 0 <= suchwort.
                compareTo(woerter[pos - 1]);
        boolean smallerOrEqualsThanZero = suchwort.compareTo(woerter[pos]) <= 0;

        if (biggerOrEqualsThanZero && smallerOrEqualsThanZero) {
          return pos;
        } else if (woerter[pos].compareTo(suchwort) < 0) {
          erstes = pos + 1;
        } else if (woerter[pos].compareTo(suchwort) > 0) {
          letztes = pos - 1;
        }
      }
    }
    // if pos equals array length add one for the einfuegePosition
    return pos == woerter.length - 1 ? woerter.length : pos;
  }

  public static void sortiere(String[] woerter) {
    boolean swapped;
    do {
      swapped = false;
      for (int i = 1; i < woerter.length; i++) {
        if (woerter[i - 1] != null && woerter[i] != null) {
          if (woerter[i - 1].compareTo(woerter[i]) > 0) {
            String temp = woerter[i - 1];
            woerter[i - 1] = woerter[i];
            woerter[i] = temp;
            swapped = true;
          }
        }
      }
    } while (swapped);

//      Arrays.sort(woerter);
  }
}
