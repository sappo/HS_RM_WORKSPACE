package org.ks.bmi;

import java.util.Scanner;

/**
 *
 * @author Kevin Sapper
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    String name;
    int gewicht;
    int groesse;

    Scanner scanner = new Scanner(System.in);

    System.out.println("Gib einen Namen ein:");
    name = scanner.nextLine();
    System.out.println("Gib ein Gewicht in kg ein:");
    gewicht = readInt(scanner);
    System.out.println("Gib eine Größe in cm ein:");
    groesse = readInt(scanner);

    Person person = new Person(name, groesse, gewicht);
    person.sagBmi();
  }

  public static int readInt(Scanner scanner) {
    int wert = 0;
    while (!scanner.hasNextInt()) {
      System.out.println("Bitte geben Sie eine gültige Ganzzahl ein!");
      scanner.nextLine(); // skip to the next line
    }
    wert = scanner.nextInt();
    return wert;
  }
}
