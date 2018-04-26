package bookstore.service.author;

import bookstore.dto.AuthorDto;
import bookstore.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
    Author findById(int authorId);
    Author create(AuthorDto authorDto);

}
