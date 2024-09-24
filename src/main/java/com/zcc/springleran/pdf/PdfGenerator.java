package com.zcc.springleran.pdf;

/**
 * @author <a href="#">Allen.zww</a>
 * @version v1.0
 * @since PdfGenerator.java v1.0 2024年09月23日 19:49 devin
 */

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.FileNotFoundException;

public class PdfGenerator {

    public static void main(String[] args) {
        // PDF 文件的路径
        String pdfFilePath = "E:\\新桌面\\pdf\\output.pdf";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 创建 PDF 文件
        try {
            // 1. 创建 PdfWriter 对象，用于将 PDF 内容写入文件
            PdfWriter writer = new PdfWriter(pdfFilePath);
            //PdfWriter writer = new PdfWriter(outputStream);

            // 2. 创建 PdfDocument 对象，它是 PDF 文档的表示
            PdfDocument pdfDocument = new PdfDocument(writer);

            // 3. 创建 Document 对象，这是文档的主类
            Document document = new Document(pdfDocument);

            // 4. 添加段落内容
            Text text = new Text("Hello, this is a sample PDF created using iText.")
                    .setFontSize(12)
                    .setBold()
                    .setFontColor(ColorConstants.BLUE);
            Paragraph paragraph = new Paragraph(text).setTextAlignment(TextAlignment.CENTER);

            Paragraph paragraph1 = new Paragraph("H123.");

            document.add(paragraph);
            document.add(paragraph1);

            // 5. 关闭文档
            document.close();

            System.out.println("PDF generated successfully!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
