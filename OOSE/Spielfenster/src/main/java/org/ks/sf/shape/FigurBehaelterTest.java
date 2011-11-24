package org.ks.sf.shape;

import org.ks.sf.math.Vector;

public class FigurBehaelterTest {

  public static void main(String[] args) {
    Figure f1 = new Line(new Vector(1.0, 1.0), new Vector(0.5, 0.5));
    Figure f2 = new Rectangle(new Vector(2.0, 2.0), new Vector(0.5, 0.5));
    Figure f3 = new Triangle(new Vector(3.0, 3.0), new Vector(0.0, 0.5), new Vector(0.5, 0.0));

    System.out.println("+-----+");
    System.out.println("| fb1 |");
    System.out.println("+-----+");
    System.out.print("new FigurBehaelter(-17): ");
    try {
      FigureContainer fb1 = new FigureContainer(-17);
      System.out.println("Keine Exception [IllegalArgumentException]");
    } catch (IllegalArgumentException e) {
      System.out.println("IllegalArgumentException [IllegalArgumentException]");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + " [IllegalArgumentException]");
    }
    System.out.println();

    System.out.println("+-----+");
    System.out.println("| fb2 |");
    System.out.println("+-----+");
    FigureContainer fb2 = new FigureContainer(0);
    System.out.print("toString(): " + fb2.toString());
    System.out.println(" [leerer FigurBehaelter]");
    System.out.println("capacity(): " + fb2.size() + " [0]");
    System.out.println("size(): " + fb2.size() + " [0]");
    fb2.add(f1);
    fb2.add(f2);
    fb2.add(f3);
    System.out.print("toString(): " + fb2.toString());
    System.out.println(" [Linie<(1.0,1.0),(0.5,0.5)>, Rechteck<(2.0,2.0),(0.5,0.5)>, Dreieck<(3.0,3.0),(0.0,0.5),(0.5,0.0)>]");
    System.out.println("size(): " + fb2.size() + " [3]");
    System.out.print("add(null): ");
    try {
      fb2.add(null);
      System.out.println("Keine Exception [IllegalArgumentException]");
    } catch (IllegalArgumentException e) {
      System.out.println("IllegalArgumentException [IllegalArgumentException]");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + " [IllegalArgumentException]");
    }
    System.out.println();

    System.out.println("+-----+");
    System.out.println("| fb3 |");
    System.out.println("+-----+");
    FigureContainer fb3 = new FigureContainer(5);
    System.out.print("toString(): " + fb3.toString());
    System.out.println(" [leerer FigurBehaelter]");
    System.out.println("capacity(): " + fb3.size() + " [0]");
    System.out.println("size(): " + fb3.size() + " [0]");
    fb3.add(f1);
    fb3.add(f2);
    System.out.print("toString(): " + fb3.toString());
    System.out.println(" [Linie<(1.0,1.0),(0.5,0.5)>, Rechteck<(2.0,2.0),(0.5,0.5)>]");
    System.out.println("capacity(): " + fb3.capacity() + " [5]");
    System.out.println("size(): " + fb3.size() + " [2]");
    fb3.add(f2);
    fb3.add(f2);
    fb3.add(f2);
    fb3.add(f3);
    System.out.print("toString(): " + fb3.toString());
    System.out.println(" [Linie<(1.0,1.0),(0.5,0.5)>, Rechteck<(2.0,2.0),(0.5,0.5)>, Rechteck<(2.0,2.0),(0.5,0.5)>, Rechteck<(2.0,2.0),(0.5,0.5)>, Rechteck<(2.0,2.0),(0.5,0.5)>, Dreieck<(3.0,3.0),(0.0,0.5),(0.5,0.0)>]");
    System.out.println("capacity(): " + fb3.capacity() + " [>5]");
    System.out.println("size(): " + fb3.size() + " [6]");
    System.out.println("elementAt(0): " + fb3.elementAt(0) + " [Linie<(1.0,1.0),(0.5,0.5)>]");
    System.out.println("elementAt(1): " + fb3.elementAt(1) + " [Rechteck<(2.0,2.0),(0.5,0.5)>]");
    System.out.println("elementAt(4): " + fb3.elementAt(4) + " [Rechteck<(2.0,2.0),(0.5,0.5)>]");
    System.out.println("elementAt(5): " + fb3.elementAt(5) + " [Dreieck<(3.0,3.0),(0.0,0.5),(0.5,0.0)>]");
    System.out.print("elementAt(6): ");
    try {
      Figure f = fb3.elementAt(fb3.size());
      System.out.println("Keine Exception [ArrayIndexOutOfBoundsException]");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("ArrayIndexOutOfBoundsException [ArrayIndexOutOfBoundsException]");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + " [ArrayIndexOutOfBoundsException]");
    }
    System.out.println();

    System.out.println();
  }
}
