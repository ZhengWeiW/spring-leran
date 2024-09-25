package com.zcc.springleran.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="#">Allen.zww</a>
 * @version v1.0
 * @since PdfGenerator2.java v1.0 2024年09月24日 20:29 Allen.zww
 */
public class PdfGenerator2 {

    // 字体
    private final static String PDF_FONT = "Helvetica";
    //图片的左边距
    private final static float FIXED_POSITION_LEFT = 250;
    //图片的下边距
    private final static float FIXED_POSITION_BOTTOM = 750;
    //图片宽度
    private final static float IMAGE_WIDTH = 100;
    //图片高度
    private final static float IMAGE_HEIGHT = 100;

    public static void main(String[] args) {
        try {
            // 定义模板路径和输出路径
            String templatePath = "E:\\新桌面\\pdf\\20240924-logo.pdf"; // 模板文件路径
            String outputPath = "E:\\新桌面\\pdf\\20240924-new.pdf"; // 输出文件路径
            String imagePath = "E:\\新桌面\\pdf\\logo.png"; // 图片路径

            // 创建 PdfReader 从模板读取 PDF
            PdfReader reader = new PdfReader(templatePath);
            // 创建 PdfWriter 写入新的 PDF 文件
            PdfWriter writer = new PdfWriter(new FileOutputStream(outputPath));
            // 创建 PdfDocument
            PdfDocument pdfDoc = new PdfDocument(reader, writer);
            // 创建图片 Document
            Document document = new Document(pdfDoc);
            // 获取 PDF 表单
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

            // 填充数据的 Map
            Map<String, String> params = new HashMap<>();
            params.put("payAmount", "123");
            params.put("doneTimeZone", "456");
            params.put("logo", "456");

            //填充表单元素
            PdfFont font = PdfFontFactory.createFont(PDF_FONT);
            Map<String, PdfFormField> formFields = form.getFormFields();
            for (Map.Entry<String, PdfFormField> formField : formFields.entrySet()) {
                String key = formField.getKey();
                if(form.getField(key) == null){
                    continue;
                }
                PdfFormField field = form.getField(key).setValue(params.get(key));
                field.setFont(font);

            }
            // 添加图片
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);
            // 设置图片位置和大小
            // 设置图片左下角的位置 (x, y)
            image.setFixedPosition(FIXED_POSITION_LEFT, FIXED_POSITION_BOTTOM);
            // 设置图片缩放到最大宽高为 200x200
            image.scaleToFit(IMAGE_WIDTH, IMAGE_HEIGHT);
            // 将图片添加到 Document 对象
            document.add(image);
            // 可选：将表单字段设为不可编辑
            form.flattenFields();

            // 关闭文档
            document.close();
            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
