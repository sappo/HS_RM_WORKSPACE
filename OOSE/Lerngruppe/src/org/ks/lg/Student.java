package org.ks.lg;

/**
 * The class represent a student.
 * @author Kevin Sapper 2011
 */
public class Student {

  private String name;

  private String matrikelNummber;

  private Professor mentor;

  public Student(String name, String matrikelNummber, Professor mentor) {
    this.name = name;
    this.matrikelNummber = matrikelNummber;
    this.mentor = mentor;
  }

  public Professor getMentor() {
    return mentor;
  }

  public String getName() {
    return name;
  }

  public String getMatrikelNummber() {
    return matrikelNummber;
  }

  public void drucke() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append("Der Student ").append(this.toString()).append(
            " wird unterrichtet von dem ").
            append(mentor.toString()).append(".");
    System.out.println(objDesc.toString());
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append(name).append(" (Matr. ").append(matrikelNummber).
            append(")");
    return objDesc.toString();
  }
}
