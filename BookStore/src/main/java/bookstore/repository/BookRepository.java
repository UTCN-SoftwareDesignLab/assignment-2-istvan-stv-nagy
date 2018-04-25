package bookstore.repository;

import bookstore.dto.BookDto;
import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(Genre genre);

    @Query("select b from Book b where b.quantity = 0")
    List<Book> findOutOfStockBooks();

    @Query("select b from Book b where (b.title LIKE ?1) OR (b.author.name LIKE ?1) OR (b.genre.name LIKE ?1)")
    List<Book> searchForBook(String fieldText);
}
