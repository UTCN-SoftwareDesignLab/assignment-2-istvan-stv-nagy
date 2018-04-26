package bookstore.service.google_books;

import bookstore.entity.Book;

import java.util.List;

public interface GoogleBooksService {

    List<Book> findByTitle(String title);

}
