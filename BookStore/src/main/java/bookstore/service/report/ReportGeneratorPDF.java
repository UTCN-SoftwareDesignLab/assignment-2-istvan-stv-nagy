package bookstore.service.report;

import bookstore.entity.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class ReportGeneratorPDF implements ReportGenerator {

    @Override
    public void generate(List<Book> outOfStockBooks) {
        String fileName = "report.pdf";

        try {
            PDDocument doc = new PDDocument();

            PDPage page = new PDPage();

            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();
            content.setLeading(20.5f);
            content.setFont(PDType1Font.TIMES_BOLD, 26);
            content.newLineAtOffset(25, 700);
            content.showText("List of Out of stock books");

            content.newLine();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.showText("# of out-of-stock books = " + outOfStockBooks.size());
            content.newLine();

            for (Book book : outOfStockBooks) {
                content.setFont(PDType1Font.HELVETICA_BOLD, 16);
                content.showText(book.getTitle());
                content.newLineAtOffset(150, 0);

                content.setFont(PDType1Font.TIMES_ITALIC, 16);
                content.showText(book.getAuthor().getName());
                content.newLineAtOffset(150, 0);

                content.setFont(PDType1Font.HELVETICA, 16);
                content.showText("[" + book.getGenre().getName() + "]");
                content.newLineAtOffset(-300, 0);

                content.newLine();
            }

            content.endText();
            content.close();

            doc.save(fileName);

            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
