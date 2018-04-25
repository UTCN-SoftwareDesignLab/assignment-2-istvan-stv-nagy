package bookstore.service.report;

import bookstore.entity.Book;

import java.util.List;

public interface ReportGenerator {

    void generate(List<Book> outOfStockBooks);

}
