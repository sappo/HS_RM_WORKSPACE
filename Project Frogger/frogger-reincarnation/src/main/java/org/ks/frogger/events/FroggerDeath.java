package org.ks.frogger.events;

/**
 *
 * @author Kevin Sapper 2011
 */
public class FroggerDeath {
  
  public static final String OVERRUN = "overrun";
  
  private String reason;

  public FroggerDeath(String reason) {
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }


}
