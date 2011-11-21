package org.ks.woerterbuch;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sappo
 */
public class FunktionenTest {

  String[] a;

  public FunktionenTest() {
  }

  @Before
  public void setUp() {
    a = new String[]{"Das", "ist", "ein", "Text", "zum", "Testen"};
  }

  @After
  public void tearDown() {
    a = null;
  }

  @Test
  public void testSorting() {
    String[] sortiertesArray = new String[]{"Das", "Testen", "Text", "ein", "ist", "zum"};

    Funktionen.sortiere(a);

    for (int i = 0; i < sortiertesArray.length; i++) {
      String sorted = sortiertesArray[i];
      String unsorted = a[i];
      Assert.assertEquals(sorted, unsorted);
    }
  }

  @Test
  public void testPos() {
    Funktionen.sortiere(a);

    Assert.assertEquals(-1, Funktionen.wortPosition(a, "Anton"));
    Assert.assertEquals(0, Funktionen.wortPosition(a, "Das"));
    Assert.assertEquals(-1, Funktionen.wortPosition(a, "Test"));
    Assert.assertEquals(2, Funktionen.wortPosition(a, "Text"));
    Assert.assertEquals(5, Funktionen.wortPosition(a, "zum"));
    Assert.assertEquals(-1, Funktionen.wortPosition(a, "zur"));
  }

  @Test
  public void testPutPos() {
    Funktionen.sortiere(a);

    Assert.assertEquals(0, Funktionen.einfuegePosition(a, "Anton"));
    Assert.assertEquals(-1, Funktionen.einfuegePosition(a, "Das"));
    Assert.assertEquals(1, Funktionen.einfuegePosition(a, "Test"));
    Assert.assertEquals(-1, Funktionen.einfuegePosition(a, "Text"));
    Assert.assertEquals(-1, Funktionen.einfuegePosition(a, "zum"));
    Assert.assertEquals(6, Funktionen.einfuegePosition(a, "zur"));
  }
}
