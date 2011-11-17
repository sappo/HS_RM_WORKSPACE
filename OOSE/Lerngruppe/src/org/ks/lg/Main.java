package org.ks.lg;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for Lerngruppe
 * @author Kevin Sapper
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Professor mentor = new Professor("Bodo Igler", "Informatik");
    Student student1 = new Student("Kevin Sapper", "470407", mentor);
    Student student2 = new Student("Max Mustermann", "470487", mentor);

    List<Student> studentenListe = new ArrayList<Student>();
    studentenListe.add(student1);
    studentenListe.add(student2);
    Lerngruppe lg = new Lerngruppe("OOPSE", studentenListe);

    //print objects information on console
    mentor.drucke();
    student1.drucke();
    lg.drucke();
  }
}
