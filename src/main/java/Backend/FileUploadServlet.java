package Backend;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@WebServlet("/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
    private static final String TEXTS_DIR = "shared_texts";
    private static final Random RANDOM = new Random();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if it's a text sharing request
        String textSharingParam = request.getParameter("textSharing");
        if (textSharingParam != null && textSharingParam.equals("true")) {
            handleTextSharing(request, response);
        } else {
            handleFileUpload(request, response);
        }
    }

    private void handleFileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        if (filePart != null) {
            String fileName = getFileName(filePart);
            String fileCode = generate4DigitCode();

            File uploads = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);
            if (!uploads.exists()) {
                uploads.mkdir();
            }

            File file = new File(uploads, fileCode + "_" + fileName);
            filePart.write(file.getAbsolutePath());

            // Send JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"status\": \"success\", \"message\": \"File uploaded successfully.\", \"fileCode\": \"" + fileCode + "\" }");
        } else {
            // Send JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"status\": \"error\", \"message\": \"No file uploaded.\" }");
        }
    }

    private void handleTextSharing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sharedText = request.getParameter("sharedText");
        if (sharedText != null && !sharedText.isEmpty()) {
            String textCode = generate4DigitCode();

            File textsDir = new File(getServletContext().getRealPath("") + File.separator + TEXTS_DIR);
            if (!textsDir.exists()) {
                textsDir.mkdir();
            }

            File textFile = new File(textsDir, textCode + ".txt");
            try (FileWriter writer = new FileWriter(textFile)) {
                writer.write(sharedText);
            }

            // Send JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"status\": \"success\", \"message\": \"Text shared successfully.\", \"textCode\": \"" + textCode + "\" }");
        } else {
            // Send JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"status\": \"error\", \"message\": \"No text shared.\" }");
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static String generate4DigitCode() {
        int code = 1000 + RANDOM.nextInt(9000); // Generate a random 4-digit number (from 1000 to 9999)
        return String.valueOf(code);
    }
}
