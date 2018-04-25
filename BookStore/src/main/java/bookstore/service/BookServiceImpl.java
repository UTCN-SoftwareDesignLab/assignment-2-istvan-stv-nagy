package bookstore.service;

import bookstore.dto.BookDto;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.repository.AuthorRepository;
import bookstore.repository.BookRepository;
import bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findByGenre(Integer genreId) {
        Genre genre = genreRepository.findOne(genreId);
        return bookRepository.findByGenre(genre);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findOutOfStockBooks() {
        return bookRepository.findOutOfStockBooks();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book create(BookDto bookDto) {
        Author author = authorRepository.findByName(bookDto.authorName);
        if (author == null)
            author = authorRepository.save(new Author(bookDto.authorName));
        Genre genre = genreRepository.findOne(bookDto.genreId);
        Book book = new Book(bookDto.title, author, genre, bookDto.price, bookDto.quantity);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Integer id, BookDto bookDto) {
        Book book = bookRepository.findOne(id);
        Author author = authorRepository.findByName(bookDto.authorName);
        if (author == null)
            author = authorRepository.save(new Author(bookDto.authorName));
        book.setAuthor(author);
        book.setGenre(genreRepository.findOne(bookDto.genreId));
        book.setTitle(bookDto.title);
        book.setPrice(bookDto.price);
        book.setQuantity(bookDto.quantity);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> search(String fieldText) {
        return bookRepository.searchForBook("%"+fieldText+"%");
    }

    @Override
    public void delete(Integer id) {
        bookRepository.delete(id);
    }

    @Override
    public void sale(Integer bookId, int quantity) {
        Book book = bookRepository.findOne(bookId);
        if (book.getQuantity() >= quantity) {
            book.setQuantity(book.getQuantity() - quantity);
        }
        bookRepository.save(book);
    }
}
