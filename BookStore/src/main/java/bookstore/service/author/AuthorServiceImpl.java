package bookstore.service.author;

import bookstore.dto.AuthorDto;
import bookstore.entity.Author;
import bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int authorId) {
        return authorRepository.findOne(authorId);
    }

    @Override
    public Author create(AuthorDto authorDto) {
        return authorRepository.save(new Author(authorDto.name));
    }
}
