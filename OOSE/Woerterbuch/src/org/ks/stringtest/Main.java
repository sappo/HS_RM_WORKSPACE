package org.ks.stringtest;

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
    Scanner scanner = new Scanner(System.in);

    String string1 = null;
    String string2 = null;

    System.out.println("Gib einen Zeichenkette ein:");
    string1 = scanner.nextLine();

    System.out.println("Gib eine weitere Zeichenkette ein:");
    string2 = scanner.nextLine();

    System.out.println("Länge Zeichenkette 1: " + string1.length());
    System.out.println("Länge Zeichenkette 2: " + string2.length());

    System.out.println("Zeichenkette 1 erstes Zeichen: " + string1.charAt(0));
    System.out.println("Zeichenkette 1 letztes Zeichen: " + string1.charAt(
            string1.length() - 1));
    System.out.println("Zeichenkette 2 erstes Zeichen: " + string2.charAt(0));
    System.out.println("Zeichenkette 2 letztes Zeichen: " + string2.charAt(string2.
            length() - 1));
    System.out.println("Erste Zeichenkette in zweiter enthalten = " + string2.contains(string1));
  }
}
