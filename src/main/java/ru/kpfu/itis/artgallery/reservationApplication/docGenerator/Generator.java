package ru.kpfu.itis.artgallery.reservationApplication.docGenerator;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.models.FileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Random;

public class Generator {
    private static int FONT_SIZE_SMALL = 14;
    private static int FONT_SIZE_BIG = 16;
    private static int OFFSET = 14;

    private String customer;
    private Integer numberOfVisitors = 1;
    private String date;
    private String time;
    private String city;
    private String exposition;
    private Exposition expositionModel;

    public Generator(String customer, Integer numberOfVisitors, String date, String time, String city, String exposition) {
        this.customer = customer;
        this.numberOfVisitors = numberOfVisitors;
        this.date = date;
        this.time = time;
        this.city = city;
        this.exposition = exposition;
    }

    public Generator(Exposition exposition) {
        expositionModel = exposition;
    }


    public void createTemplate() {
        Document document = new Document();
        try {
            BaseFont bf = BaseFont.createFont("src/main/resources/Arial.ttf", "cp1251", BaseFont.EMBEDDED);
            Font font2 = new Font(bf,
                    FONT_SIZE_SMALL);
            PdfWriter.getInstance(document,
                    new FileOutputStream("src/main/resources/template.pdf"));

            document.open();

            Paragraph placeField = new Paragraph("Выставочная галерея ARTGALLERY. Забронировано посещение", font2);
//            placeField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(placeField);

            Paragraph customerField = new Paragraph("Имя", font2);
//            customerField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(customerField);

            Paragraph amountField = new Paragraph("Количество посетителей", font2);
//            amountField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(amountField);

            // параграф с текстом
            Paragraph cityField = new Paragraph("Город", font2);
//            cityField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(cityField);

            // параграф с текстом
            Paragraph expositionField = new Paragraph("Название экспозиции", font2);
//            expositionField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(expositionField);

            // параграф с фразой, в которую добавлен чанк
            Paragraph DateField = new Paragraph("Дата посещения", font2);
//            DateField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(DateField);

            // параграф с текстом
            Paragraph timeField = new Paragraph("Время посещения", font2);
//            timeField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(timeField);


//        anchor.setReference("http://www.javenue.info");
//        footer.add(anchor);
//
//        footer.setAlignment(Element.ALIGN_CENTER);
//        footer.setSpacingBefore(FONT_SIZE_BIG);
//        document.add(footer);

            // картинка, загруженная по URL
//        String imageUrl = "http://www.javenue.info/files/sample.png";
//        // Image.getInstance("sample.png")
//        Image stamp = Image.getInstance(new URL(imageUrl));
//        stamp.setAlignment(Element.ALIGN_RIGHT);
//        document.add(stamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    private String cloudinaryUrl = "cloudinary://847789584675391:s0PdT9M68IZRG79aao3s6c1NbWc@mt21";

    public String fillData() {
        try {
            String filename = customer + "_ticket.pdf";
            PdfReader reader = new PdfReader(
                    new FileInputStream("src/main/resources/template.pdf"));
            PdfStamper stamper = new PdfStamper(reader,
                    new FileOutputStream(filename));

            PdfContentByte stream = stamper.getOverContent(1);
            stream.beginText();
            stream.setColorFill(BaseColor.BLACK);

            BaseFont bf = BaseFont.createFont("src/main/resources/Arial.ttf", "cp1251", BaseFont.EMBEDDED);
            float pageWidth = reader.getPageSize(1).getWidth();
            float pageHeight = reader.getPageSize(1).getHeight();
            stream.setFontAndSize(bf, FONT_SIZE_SMALL);

//        float v = stream.getEffectiveStringWidth(
//                customer, false);

//        float fitSize = (pageWidth - OFFSET * 2) * FONT_SIZE_SMALL / v;
//        stream.setFontAndSize(font, fitSize);
//        stream.setTextMatrix(OFFSET, 680);

            float v = stream.getEffectiveStringWidth(
                    customer, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 2 * FONT_SIZE_SMALL + 3 * FONT_SIZE_BIG);
            stream.showText(customer);


            v = stream.getEffectiveStringWidth(numberOfVisitors.toString(), false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 1.5 * FONT_SIZE_SMALL);
            stream.showText(numberOfVisitors.toString());

            v = stream.getEffectiveStringWidth(city, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 1.5 * FONT_SIZE_SMALL);
            stream.showText(city);

            v = stream.getEffectiveStringWidth(exposition, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 1.5 * FONT_SIZE_SMALL);
            stream.showText(exposition);

            v = stream.getEffectiveStringWidth(
                    date, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 1.5 * FONT_SIZE_SMALL);
            stream.showText(date);

            v = stream.getEffectiveStringWidth(
                    time, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 1.5 * FONT_SIZE_SMALL);
            stream.showText(time);


            stream.endText();
            stamper.setFullCompression();
            stamper.close();

            FileUpload fileUpload = new FileUpload();
            Map uploadResult;
            File file = new File(filename);

            MultipartFile multipartFile = new MockMultipartFile(file.getName(), new FileInputStream(file));
            fileUpload.setFile(multipartFile);
            Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
            uploadResult = cloudinary.uploader().upload(fileUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            fileUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                fileUpload.setVersion(new Long((Integer) version));
            } else {
                fileUpload.setVersion((Long) version);
            }
            fileUpload.setFormat((String) uploadResult.get("format"));
            fileUpload.setResourceType((String) uploadResult.get("resource_type"));

            file.delete();

            return "http://res.cloudinary.com/mt21/" + fileUpload.getPreloadedFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static void main(String[] args) {
//        Generator generator = new Generator();
//        generator.createTemplate();
//        System.out.println(generator.fillData());
//    }

    public String createProgram() {
        Document document = new Document();
//        Font font1 = new Font(Font.FontFamily.HELVETICA,
//                FONT_SIZE_BIG);

        try {
            BaseFont bf = BaseFont.createFont("Arial.ttf", "cp1251", BaseFont.EMBEDDED);
            Font font2 = new Font(bf,
                    FONT_SIZE_SMALL);
            int salt = new Random().nextInt();
            String filename = "program" + salt + ".pdf";
            PdfWriter.getInstance(document,
                    new FileOutputStream(filename));

            document.open();

            Paragraph placeField = new Paragraph("Выставочная галерея ARTGALLERY. Программа мероприятия", font2);
            document.add(placeField);

            for (Exhibit e : expositionModel.getExhibits()) {
                Paragraph id = new Paragraph("Номер экспоната: " + e.getId(), font2);
                document.add(id);
                Paragraph name = new Paragraph("Название: " + e.getName(), font2);
                document.add(name);
                Paragraph author = new Paragraph("Автор: " + e.getAuthor().getName(), font2);
                author.setSpacingAfter(2 * FONT_SIZE_BIG);
                document.add(author);
            }
            document.close();
            FileUpload fileUpload = new FileUpload();
            Map uploadResult;
            File file = new File(filename);

            MultipartFile multipartFile = new MockMultipartFile(file.getName(), new FileInputStream(file));
            fileUpload.setFile(multipartFile);
            Cloudinary cloudinary = new Cloudinary(cloudinaryUrl);
            uploadResult = cloudinary.uploader().upload(fileUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            fileUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");
            if (version instanceof Integer) {
                fileUpload.setVersion(new Long((Integer) version));
            } else {
                fileUpload.setVersion((Long) version);
            }
            fileUpload.setFormat((String) uploadResult.get("format"));
            fileUpload.setResourceType((String) uploadResult.get("resource_type"));
            file.delete();

            return "http://res.cloudinary.com/mt21/" + fileUpload.getPreloadedFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
