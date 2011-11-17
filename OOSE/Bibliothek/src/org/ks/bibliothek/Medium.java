package org.ks.bibliothek;

/**
 * This class represents a Medium
 * @author Kevin Sapper 2011
 */
public class Medium {

  private String titel;

  private String author;

  public Medium(String title, String author) {
    this.titel = title;
    this.author = author;
  }

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return titel;
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append("Medium hat folgende Eigenschaften: (titel=").append(titel).
            append(";author=").append(author).append(");");
    return objDesc.toString();
  }
}
