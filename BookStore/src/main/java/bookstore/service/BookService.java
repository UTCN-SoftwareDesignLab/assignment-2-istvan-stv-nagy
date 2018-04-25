package bookstore.service;

import bookstore.dto.BookDto;
import bookstore.entity.Author;
import bookstore.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String authorName);

    List<Book> findByGenre(Integer genreId);

    List<Book> findOutOfStockBooks();

    Book findById(Integer id);

    List<Book> findAll();

    Book create(BookDto book);

    Book update(Integer id, BookDto book);

    List<Book> search(String fieldText);

    void delete(Integer id);

    void sale(Integer bookId, int quantity);
}
