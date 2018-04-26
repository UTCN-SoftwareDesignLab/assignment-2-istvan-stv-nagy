package bookStore.unit;

import bookstore.entity.Book;
import bookstore.service.google_books.GoogleBooksService;
import bookstore.service.google_books.GoogleBooksServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GoogleBooksTest {

    GoogleBooksService googleBooksService;

    @Before
    public void setup() {
        googleBooksService = new GoogleBooksServiceImpl();
    }

    @Test
    public void find() {
        List<Book> bookList = googleBooksService.findByTitle("A Song of Ice and Fire");
        Assert.assertTrue(bookList.size() > 0);
    }
}
