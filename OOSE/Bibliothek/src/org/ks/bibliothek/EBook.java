package org.ks.bibliothek;

/**
 *
 * @author Kevin Sapper 2011
 */
public class EBook extends Medium {

  private String url;

  public EBook(String title, String author, String url) {
    super(title, author);
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    StringBuilder objDesc = new StringBuilder();
    objDesc.append("eBook hat folgende Eigenschaften: (titel=").append(getTitle()).
            append(";author=").append(getAuthor()).append(";url=").append(getUrl()).append(");");
    return objDesc.toString();
  }
  
  
}
