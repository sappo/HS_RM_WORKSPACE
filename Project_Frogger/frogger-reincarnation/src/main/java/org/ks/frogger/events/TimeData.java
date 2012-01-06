package org.ks.frogger.events;

/**
 *
 * @author Kevin Sapper 2011
 */
public class TimeData {

  private Long maxTime;

  private Long remainingTime;

  public TimeData(Long maxTime, Long remainingTime) {
    this.maxTime = maxTime;
    this.remainingTime = remainingTime;
  }

  public Long getMaxTime() {
    return maxTime;
  }

  public Long getRemainingTime() {
    return remainingTime;
  }
}
