package bookstore.service.report;

import bookstore.entity.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGeneratorCSV implements ReportGenerator {

    @Override
    public void generate(List<Book> outOfStockBooks) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("report.csv"));
            bw.write("Title,Author,Genre,Price,Quantity\n");
            for (Book book : outOfStockBooks) {
                StringBuilder sb = new StringBuilder();
                sb.append(book.getTitle());
                sb.append(",");
                sb.append(book.getAuthor().getName());
                sb.append(",");
                sb.append(book.getGenre().getName());
                sb.append(",");
                sb.append(book.getPrice());
                sb.append(",");
                sb.append(book.getQuantity());
                sb.append("\n");
                bw.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
