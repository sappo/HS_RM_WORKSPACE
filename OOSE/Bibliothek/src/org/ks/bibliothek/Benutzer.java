package org.ks.bibliothek;

/**
 * This class represents a Benutzer.
 * @author Kevin Sapper 2011
 */
public class Benutzer {

  private String name;

  private String vorname;

  private String strasse;

  private String hausnummer;

  private String plz;

  private String ort;

  public Benutzer(String name, String vorname, String strasse, String hausnummer, String plz, String ort) {
    this.name = name;
    this.vorname = vorname;
    this.strasse = strasse;
    this.hausnummer = hausnummer;
    this.plz = plz;
    this.ort = ort;
  }

  public String getHausnummer() {
    return hausnummer;
  }

  public String getName() {
    return name;
  }

  public String getOrt() {
    return ort;
  }

  public String getPlz() {
    return plz;
  }

  public String getStrasse() {
    return strasse;
  }

  public String getVorname() {
    return vorname;
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append("Benutzer hat folgende Eigenschaften (vorname=").append(vorname).
            append(";name=").append(name).append(";straße=").append(strasse).
            append(";hausnummer=").append(hausnummer).append(";plz=").append(plz).
            append(";ort=").append(ort).append(")");
    return objDesc.toString();
  }
}
