package bookstore.controller.admin;

import bookstore.service.report.ReportService;
import bookstore.service.report.PDFLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/report_csv")
    public String generateCsvReport() {
        reportService.generateReport("csv");
        return "redirect:/admin";
    }

    @RequestMapping(value = "/report_pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdfReport() {
        reportService.generateReport("pdf");
        byte[] result = null;
        PDFLoader pdfLoader = new PDFLoader();
        try {
            result = pdfLoader.loadPdf("report.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
}
