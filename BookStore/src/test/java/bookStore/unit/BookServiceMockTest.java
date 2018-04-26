package bookStore.unit;

import bookstore.dto.BookDto;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.entity.builder.BookBuilder;
import bookstore.repository.AuthorRepository;
import bookstore.repository.BookRepository;
import bookstore.repository.GenreRepository;
import bookstore.service.book.BookService;
import bookstore.service.book.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BookServiceMockTest {

    private BookService bookService;

    @Before
    public void init() {
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        Book defaultBook = new BookBuilder()
                .setAuthor(new Author("author"))
                .setTitle("title")
                .setGenre(new Genre("genre"))
                .setQuantity(10)
                .setPrice(1)
                .build();
        when(bookRepository.findOne(1)).thenReturn(defaultBook);
        bookService = new BookServiceImpl(bookRepository, Mockito.mock(AuthorRepository.class), Mockito.mock(GenreRepository.class));
    }

    @Test
    public void findAll() {
        Assert.assertNotNull(bookService.findAll());
    }

    @Test
    public void validSell() {
        Assert.assertTrue(bookService.sale(1, 2));
    }

    @Test
    public void invalidSell() {
        Assert.assertFalse(bookService.sale(1, 20));
    }

}
