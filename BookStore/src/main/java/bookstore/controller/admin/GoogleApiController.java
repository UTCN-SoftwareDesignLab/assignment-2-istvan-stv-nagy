package bookstore.controller.admin;

import bookstore.entity.Book;
import bookstore.service.google_books.GoogleBooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GoogleApiController {

    @Autowired
    GoogleBooksServiceImpl googleBooksService;

    @RequestMapping(value = "/google-books", method = RequestMethod.GET)
    public String findByTitle() {
        return "book-search-google";
    }

    @RequestMapping(value = "/google-books", method = RequestMethod.POST)
    public String findByTitle(Model model, @RequestParam("textField") String query) {
        List<Book> bookList = googleBooksService.findByTitle(query);
        model.addAttribute("bookList", bookList);
        return "google-books";
    }
}
