package bookstore.service.report;

import java.io.*;

public class PDFLoader {

    public byte[] loadPdf(String path) throws IOException {
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = new FileInputStream(path);
            byte[] buffer = new byte[8192];
            byteArrayOutputStream = new ByteArrayOutputStream();
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1)
                byteArrayOutputStream.write(buffer, 0, bytesRead);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

}
