package bookstore.controller;

import bookstore.dto.BookDto;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import bookstore.service.AuthorService;
import bookstore.service.BookService;
import bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @RequestMapping(value = "/findall")
    public String findAll(Model model) {
        model.addAttribute("bookList", bookService.findAll());
        return "book-inventory";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBook(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("genres", genreService.findAll());
        return "book-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBook(Model model, @ModelAttribute("book") @Valid BookDto bookDto, BindingResult result) {
        if (!result.hasErrors()) {
            bookService.create(bookDto);
            return "redirect:findall";
        } else {
            model.addAttribute("messages", ErrorExtractor.constructErrors(result));
            model.addAttribute("genres", genreService.findAll());
            return "book-form";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "book-search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model,
                         @RequestParam(value="bookTitle", required = false) String title,
                         @RequestParam(value="bookAuthor", required = false) String authorName,
                         @RequestParam(value="bookGenreId", required = false) Integer genreId) {
        model.addAttribute("bookList", bookService.search(title));
        return "book-inventory-emp";
    }

    @RequestMapping(value ="/delete/{bookId}", method = RequestMethod.GET)
    public String delete(@PathVariable(value ="bookId") Integer id) {
        bookService.delete(id);
        return "redirect:/book/findall";
    }

    @RequestMapping(value="/update/{bookId}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable(value = "bookId") Integer id) {
        model.addAttribute("genres", genreService.findAll());
        Book book = bookService.findById(id);
        BookDto bookDto = new BookDto(book.getTitle(), book.getAuthor().getName(), book.getGenre().getId(), book.getPrice(), book.getQuantity());
        bookDto.setId(id);
        model.addAttribute("book", bookDto);
        return "book-update-form";
    }

    @RequestMapping(value="/update/{bookId}", method = RequestMethod.POST)
    public String update(@PathVariable(value="bookId") Integer id, @ModelAttribute(value="book") BookDto bookDto) {
        bookService.update(id, bookDto);
        return "redirect:/book/findall";
    }

    @RequestMapping(value="/sell/{bookId}", method = RequestMethod.GET)
    public String sell(Model model, @PathVariable(value="bookId") Integer id) {
        model.addAttribute("id", id);
        return "sell-form";
    }

    @RequestMapping(value ="/sell/{bookId}", method = RequestMethod.POST)
    public String sell(@PathVariable(value="bookId") Integer id, @RequestParam(value="bookAmount") Integer amount) {
        bookService.sale(id, amount);
        return "redirect:/employee";
    }
}
