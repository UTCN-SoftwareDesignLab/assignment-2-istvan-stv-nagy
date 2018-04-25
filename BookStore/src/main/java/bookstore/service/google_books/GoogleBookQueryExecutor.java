package bookstore.service.google_books;

import bookstore.entity.Author;
import bookstore.entity.Book;
import bookstore.entity.Genre;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GoogleBookQueryExecutor {

    private static final String APPLICATION_NAME = "";
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();

   public List<Book> queryGoogleBooks(String query) throws Exception {
        List<Book> bookList = new ArrayList<>();

        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
        Books.Volumes.List volumesList = books.volumes().list(query);
        volumesList.setFilter("ebooks");

        // Execute the query.
        Volumes volumes = volumesList.execute();
        if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
            return bookList;
        }

        // Output results.
        for (Volume volume : volumes.getItems()) {
            Book book = new Book();
            book.setTitle("N/A");
            book.setAuthor(new Author("N/A"));
            book.setGenre(new Genre("N/A"));

            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
            Volume.SaleInfo saleInfo = volume.getSaleInfo();
            //get title
            book.setTitle(volumeInfo.getTitle());
            //get author
            List<String> authors = volumeInfo.getAuthors();
            if (authors != null && !authors.isEmpty()) {
                book.setAuthor(new Author(authors.get(0)));
            }

            //category
            if (volumeInfo.getCategories() != null && !volumeInfo.getCategories().isEmpty()) {
                book.setGenre(new Genre(volumeInfo.getCategories().get(0)));
            }

            //price
            if (saleInfo != null && saleInfo.getListPrice() != null) {
                book.setPrice(saleInfo.getListPrice().getAmount());
            }
            bookList.add(book);
        }
        return bookList;
    }

}
