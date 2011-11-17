package org.ks.stringtest;


import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sappo
 */
public class WoerterBuchTest {

  public WoerterBuchTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testNegativeCapacity() {
    WoerterBuch wb1 = new WoerterBuch(-17);
    wb1.put("public", "oeffentlich");
    
    Assert.assertNull(wb1.get("public"));
  }
  
  @Test
  public void testZeroCapacity() {
    WoerterBuch wb2 = new WoerterBuch(0);
		wb2.put("public","oeffentlich");
    
    Assert.assertNull(wb2.get("public"));
  }
  
  @Test
  public void testFunctionality() {
    WoerterBuch wb3 = new WoerterBuch(5);
    wb3.put("extends","erweitert");
		wb3.put("private","privat");
		wb3.put("public","oeffentlich");
		wb3.put("protected","geschuetzt");
		wb3.put("package","Paket");
		wb3.put("implements","implementiert");
    
    Assert.assertEquals(wb3.get("public"), "oeffentlich");
    Assert.assertEquals(wb3.get("protected"), "geschuetzt");
    Assert.assertEquals(wb3.get("private"), "privat");
    Assert.assertEquals(wb3.get("package"), "Paket");
    Assert.assertEquals(wb3.get("implements"), null);
    Assert.assertEquals(wb3.get("extends"), "erweitert");
  }
}
