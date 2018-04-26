package bookstore.service.report;

import bookstore.entity.Book;
import bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public ReportServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void generateReport(String type) {
        ReportFactory reportFactory = new ReportFactory();
        List<Book> outOfStockBooks = bookRepository.findOutOfStockBooks();
        reportFactory.getReportGenerator(type).generate(outOfStockBooks);
    }
}
