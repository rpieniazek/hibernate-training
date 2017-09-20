package pl.sda.hibernatetraining.model;

import java.util.HashSet;
import java.util.Set;

public class Library {

  private Long id;

  private String name;

  private Set<Book> books = new HashSet<>();

  public Library() {

  }

  public Long getId() {

    return this.id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return this.name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public Set<Book> getBooks() {

    return this.books;
  }

  public void setBooks(Set<Book> books) {

    this.books = books;
  }

  public void addBook(Book book) {

//    book.setLibrary(this);
    this.books.add(book);
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Library other = (Library) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    return true;
  }
}
