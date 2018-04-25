package bookStore.service;

import bookstore.dto.BookDto;
import bookstore.entity.Book;
import bookstore.repository.AuthorRepository;
import bookstore.repository.BookRepository;
import bookstore.repository.GenreRepository;
import bookstore.service.BookService;
import bookstore.service.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BookServiceTest {

    private BookService bookService;

    @Before
    public void init() {
        bookService = new BookServiceImpl(Mockito.mock(BookRepository.class), Mockito.mock(AuthorRepository.class), Mockito.mock(GenreRepository.class));
    }

    @Test
    public void add() {
        when(bookService.create(new BookDto("title", "author", 2, 25.5, 7))).thenReturn(new Book());
        Book book = bookService.create(new BookDto("title", "author", 2, 25.5, 7));
        Assert.assertTrue("could not create book", book != null);
    }

    @Test
    public void findAll() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        when(bookService.findAll()).thenReturn(books);
        Assert.assertTrue("no book found", bookService.findAll().size() == 1);
    }

}
