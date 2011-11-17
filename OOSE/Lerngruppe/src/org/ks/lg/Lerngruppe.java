package org.ks.lg;

import java.util.List;

/**
 * The class represents a Lerngruppe
 * @author Kevin Sapper
 */
public class Lerngruppe {

  private String name;

  private List<Student> studentenListe;

  public Lerngruppe(String name,
          List<Student> studentenListe) {
    this.name = name;
    this.studentenListe = studentenListe;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void drucke() {
    StringBuilder drucke = new StringBuilder();
    drucke.append("Die Lerngruppe ").append(name).append(
            " besteht aus den Studenten ");
    for (int i = 0; i < studentenListe.size(); i++) {
      Student student = studentenListe.get(i);
      if (i == studentenListe.size() - 1) {
        drucke.append(student.toString());
      } else {
        drucke.append(student.toString()).append(" und ");

      }
    }
    System.out.println(drucke.append(".").toString());
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append("Lerngruppe ").append(name);
    return objDesc.toString();
  }
}
