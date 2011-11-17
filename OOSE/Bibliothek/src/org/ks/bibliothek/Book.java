package org.ks.bibliothek;

/**
 *
 * @author Kevin Sapper 2011
 */
public class Book extends Medium {

  private String signature;

  public Book(String title, String author, String signature) {
    super(title, author);
    this.signature = signature;
  }

  public String getSignature() {
    return signature;
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append("Book hat folgende Eigenschaften: (titel=").append(getTitle()).
            append(";author=").append(getAuthor()).append(";signature=").append(signature).append(");");
    return objDesc.toString();
  }

  
}
