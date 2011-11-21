package org.ks.woerterbuch.pseudoTests;

import org.ks.woerterbuch.Funktionen;

public class FunktionenIdiotTest {

  public static void testeWortPosition(String[] a, String s,
          int erwartetesErgebnis) {
    System.out.println(s + ": " + Funktionen.wortPosition(a, s) + " [" + erwartetesErgebnis + "]");
  }

  public static void testeEinfuegePosition(String[] a, String s,
          int erwartetesErgebnis) {
    System.out.println(s + ": " + Funktionen.einfuegePosition(a, s) + " [" + erwartetesErgebnis + "]");
  }

  public static void main(String[] args) {
    System.out.println("\t+------------------------------+");
    System.out.println("\t| Test fuer Klasse Funktionen. |");
    System.out.println("\t| -- erwartete Augabe in [] -- |");
    System.out.println("\t+------------------------------+");

    String[] a = new String[]{"Das", "ist", "ein", "Text", "zum", "Testen"};
    String[] sortiertesArray = new String[]{"Das", "Testen", "Text", "ein", "ist", "zum"};

    System.out.println("+---------------------+");
    System.out.println("| Funktionen.sortiere |");
    System.out.println("+---------------------+");
    Funktionen.sortiere(a);
    for (String s : a) {
      System.out.print(s);
      System.out.print(" ");
    }
    System.out.print("[");
    for (String s : sortiertesArray) {
      System.out.print(s);
      System.out.print(" ");
    }
    System.out.print("]");
    System.out.println();

    System.out.println("+-------------------------+");
    System.out.println("| Funktionen.wortPosition +");
    System.out.println("+-------------------------+");
    testeWortPosition(a, "Anton", -1);
    testeWortPosition(a, "Das", 0);
    testeWortPosition(a, "Test", -1);
    testeWortPosition(a, "Text", 2);
    testeWortPosition(a, "zum", 5);
    testeWortPosition(a, "zur", -1);

    System.out.println("---------------------------");
    System.out.println("Funktionen.einfuegePosition");
    System.out.println("---------------------------");
    testeEinfuegePosition(a, "Anton", 0);
    testeEinfuegePosition(a, "Das", -1);
    testeEinfuegePosition(a, "Test", 1);
    testeEinfuegePosition(a, "Text", -1);
    testeEinfuegePosition(a, "zum", -1);
    testeEinfuegePosition(a, "zur", 6);
  }
}
