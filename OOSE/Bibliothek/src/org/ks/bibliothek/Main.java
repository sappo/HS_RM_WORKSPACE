  package org.ks.bibliothek;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Controller class for Bibliothek
 * @author Kevin Sapper
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Book book = new Book("Java ist auch eine Insel", "Christian Ullenboom",
            "30 BE 190 94 A.9");
    EBook eBook = new EBook("Java will nur spielen", "Sven Eric Panitz",
            "http://dx.doi.org/10.1007/978-3-8348-9931-6");
    Benutzer benutzer = new Benutzer("Maier", "Angela", "Schlossallee", "7",
            "65193", "Wiesbaden");
    Date date = new  GregorianCalendar(2011, 10,6).getTime();
    Ausleihe ausleihe = new Ausleihe(book, benutzer, date);

    System.out.println("buch: " + book);
    System.out.println("ebuch: " + eBook);
    System.out.println("benutzer: " + benutzer);
    System.out.println("ausleihe: " + ausleihe);
  }
}