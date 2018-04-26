package bookStore.integration;

import bookstore.Application;
import bookstore.dto.BookDto;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.service.author.AuthorService;
import bookstore.service.book.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootApplication
public class BookServiceSpringTest {

    @Autowired
    BookService bookService;

    @Test
    public void findAll() {
        List<Book> bookList = bookService.findAll();
        Assert.assertTrue(bookList.size() > 0);
    }

    @Test
    public void createAndDelete() {
        Book book = bookService.create(new BookDto("title", "George", 1, 0, 0));
        Integer id = book.getId();
        Assert.assertTrue(book != null);
        bookService.delete(id);
        book = bookService.findById(id);
        Assert.assertTrue(book == null);
    }

    @Test
    public void invalidSell() {
        Book book = bookService.create(new BookDto("title","author",1, 20, 10));
        Integer id = book.getId();
        Assert.assertFalse(bookService.sale(id, 20));
        bookService.delete(id);
    }

    @Test
    public void validSell() {
        Book book = bookService.create(new BookDto("title","author",1, 20, 10));
        Integer id = book.getId();
        Assert.assertTrue(bookService.sale(id, 5));
        bookService.delete(id);
    }

    @Test
    public void delete() {
        Book book = bookService.create(new BookDto("title", "George", 1, 0, 0));
        Integer id = book.getId();
        bookService.delete(id);
        Book oldBook = bookService.findById(id);
        Assert.assertTrue(oldBook == null);
    }

}
