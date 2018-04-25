package bookstore.controller;

import bookstore.service.google_books.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GoogleApiController {

    @Autowired
    GoogleBooksService googleBooksService;

    @RequestMapping(value = "/google-books", method = RequestMethod.GET)
    public String findByTitle() {
        return "book-search-google";
    }

    @RequestMapping(value = "/google-books", method = RequestMethod.POST)
    public String findByTitle(Model model, @RequestParam("bookTitle") String title) {
        model.addAttribute("bookList", googleBooksService.findByTitle(title));
        return "google-books";
    }
}
