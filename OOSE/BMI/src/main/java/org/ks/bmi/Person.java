package org.ks.bmi;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Ein Objekt der Klasse Person ist eine Person mit Name und Koerpergroesse in cm.
 * Eine Person kann eine Begruessung und ihren Namen ausgeben.
 * Die Koerpergroesse einer Person kann mit wachse veraendert werden.
 * @author Bodo Igler
 * @author Kevin Sapper
 */
public class Person {

  private String name;

  private int groesse; // Koerpergroesse in cm

  private int koerpergewicht; // Gewicht in kg

  public Person(String name, int groesse, int koerpergewicht) {
    this.name = name;
    this.groesse = groesse;
    this.koerpergewicht = koerpergewicht;
  }

  public void begruesse() {
    System.out.println("Hallo!");
  }

  public void sagName() {
    System.out.println(name);
  }

  public void sagBmi() {
    MathContext mc = new MathContext(2);
    BigDecimal hundred = new BigDecimal(100);
    BigDecimal bmiGewicht = new BigDecimal(koerpergewicht);
    BigDecimal bmiGroesse = new BigDecimal(groesse);

    bmiGroesse = bmiGroesse.divide(hundred);
    BigDecimal bmi = bmiGewicht.divide(bmiGroesse.pow(2), mc);
    System.out.println(name + " dein BMI ist: " + bmi.toString());
    bmiAussage(bmi);
  }

  public void nimmZu(int gewicht) {
    koerpergewicht += gewicht;
  }

  /* Person wächst/schrumpft um delta cm.
   * delta>0: wachsen
   * delta<0: schrumpfen
   */
  public void wachse(int delta) {
    groesse += delta;
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    return objDesc.append("Person{Name=").append(name).append(";Größe=").append(
            groesse).append(";körpergewicht=").append(koerpergewicht).append("}").
            toString();
  }

  private void bmiAussage(BigDecimal bmi) {
    BigDecimal bmiStarkesUntergewicht = new BigDecimal(16);
    BigDecimal bmiMaessigesUntergewicht = new BigDecimal(17);
    BigDecimal bmiLeichtesUntergewicht = new BigDecimal(18.5);
    BigDecimal bmiNormalgewicht = new BigDecimal(25);
    BigDecimal bmiPraeadipositas = new BigDecimal(30);
    BigDecimal bmiAdipositasGradI = new BigDecimal(35);
    BigDecimal bmiAdipositasGradII = new BigDecimal(40);

    StringBuilder aussage = new StringBuilder(
            "Das entspricht der BMI Kategorie ");
    if (bmi.compareTo(bmiStarkesUntergewicht) == -1) {
      aussage.append("Starkes Untergewicht");
    } else if (bmi.compareTo(bmiMaessigesUntergewicht) == -1) {
      aussage.append("Mässiges Untergewicht");
    } else if (bmi.compareTo(bmiLeichtesUntergewicht) == -1) {
      aussage.append("Leichtes Untergewicht");
    } else if (bmi.compareTo(bmiNormalgewicht) == -1) {
      aussage.append("Normalgewicht");
    } else if (bmi.compareTo(bmiPraeadipositas) == -1) {
      aussage.append("Präedipositas");
    } else if (bmi.compareTo(bmiAdipositasGradI) == -1) {
      aussage.append("Adipositas Grad I");
    } else if (bmi.compareTo(bmiAdipositasGradII) == -1) {
      aussage.append("Adipositas Grad II");
    } else if (bmi.compareTo(bmiAdipositasGradII) > 0) {
      aussage.append("Adipositas Grad III");
    }
    System.out.println(aussage.toString());
  }
}