package bookstore.service.google_books;

import bookstore.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A sample application that demonstrates how Google Books Client Library for
 * Java can be used to query Google Books. It accepts queries in the command
 * line, and prints the results to the console.
 *
 * $ java com.google.sample.books.BooksSample [--author|--isbn|--title] "<query>"
 *
 * Please start by reviewing the Google Books API documentation at:
 * http://code.google.com/apis/books/docs/getting_started.html
 */
@Service
public class GoogleBooksService {

    private GoogleBookQueryExecutor queryExecutor;

    public GoogleBooksService() {
        queryExecutor = new GoogleBookQueryExecutor();
    }

    public List<Book> findByTitle(String title) {
        try {
            return queryExecutor.queryGoogleBooks("intitle:" + title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}