package org.ks.lg;

/**
 * The class represant a professor
 * @author Kevin Sapper 2011
 */
public class Professor {

  private String name;

  private String fachbereich;

  public Professor(String name, String fachbereich) {
    this.name = name;
    this.fachbereich = fachbereich;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void drucke() {
    StringBuilder drucke = new StringBuilder();
    drucke.append("Der Professor ").append(name).append(" hat den Fachbereich ").
            append(fachbereich);
    System.out.println(drucke.toString());
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append("Mentor ").append(name);
    return objDesc.toString();
  }
}
