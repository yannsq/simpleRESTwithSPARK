package fr.iutinfo;

public class Book {

  protected String author;
  protected String title;
  protected int id;
  protected static int counter = 0;

  public Book(String author, String title) {
    this.id     = ++counter;
    this.author = author;
    this.title  = title;
  }

  // to deal with serialization
  public Book() {
  }

  public void setId(int id) {
    this.id = id;
  }
  public int getId() {
    return this.id;
  }

  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return this.title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

}
