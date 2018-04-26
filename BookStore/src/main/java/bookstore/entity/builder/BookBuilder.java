package bookstore.entity.builder;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;

public class BookBuilder {

    private Book book;

    public BookBuilder() {
        this.book = new Book();
    }

    public BookBuilder setId(int id) {
        book.setId(id);
        return this;
    }

    public BookBuilder setAuthor(Author author) {
        book.setAuthor(author);
        return this;
    }

    public BookBuilder setGenre(Genre genre) {
        book.setGenre(genre);
        return this;
    }

    public BookBuilder setTitle(String title) {
        book.setTitle(title);
        return this;
    }

    public BookBuilder setPrice(double price) {
        book.setPrice(price);
        return this;
    }

    public BookBuilder setQuantity(int quantity) {
        book.setQuantity(quantity);
        return this;
    }

    public Book build() {
        return book;
    }

}
