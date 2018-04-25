package bookstore.service.report;

public class ReportFactory {

    public ReportGenerator getReportGenerator(String type) {
        switch (type) {
            case "csv" : return new ReportGeneratorCSV();
            case "pdf" : return new ReportGeneratorPDF();
            default: return null;
        }
    }

}
