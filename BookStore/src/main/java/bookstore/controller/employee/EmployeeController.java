package bookstore.controller.employee;

import bookstore.service.author.AuthorService;
import bookstore.service.book.BookService;
import bookstore.service.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public EmployeeController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @RequestMapping(value = "/book/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "book-search";
    }

    @RequestMapping(value = "/book/search", method = RequestMethod.POST)
    public String search(Model model,
                         @RequestParam(value="textField", required = false) String query){
        model.addAttribute("bookList", bookService.search(query));
        return "book-inventory-emp";
    }


    @RequestMapping(value="/book/sell/{bookId}", method = RequestMethod.POST)
    public String sell(Model model, @PathVariable(value="bookId") Integer id, @RequestParam("bookAmount") Integer amount) {
        bookService.sale(id, amount);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String showUserPage() {
        return "employee-page";
    }
}
