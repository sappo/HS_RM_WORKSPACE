package org.ks.bibliothek;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a Ausleihe.
 * @author Kevin Sapper 2011
 */
public class Ausleihe {
  
  private Medium medium;
  
  private Benutzer entleiher;
  
  private Date ausleihdatum;
  
  public Ausleihe(Medium medium, Benutzer entleiher, Date ausleihdatum) {
    this.medium = medium;
    this.entleiher = entleiher;
    this.ausleihdatum = ausleihdatum;
  }
  
  public Date getAusleihdatum() {
    return ausleihdatum;
  }
  
  public Medium getBuch() {
    return medium;
  }
  
  public Benutzer getEntleiher() {
    return entleiher;
  }
  
  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    objDesc.append("Ausleihe hat folgende Eigenschaften (buch=[").append(medium.
            toString()).append("];entleiher=[").append(entleiher.toString()).
            append("];ausleihdatum=").append(sdf.format(ausleihdatum)).append(")");
    return objDesc.toString();
  }
}
