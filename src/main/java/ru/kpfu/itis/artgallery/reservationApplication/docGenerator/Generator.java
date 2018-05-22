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
import ru.kpfu.itis.artgallery.models.FileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

public class Generator {
    private static int FONT_SIZE_SMALL = 16;
    private static int FONT_SIZE_BIG = 20;
    private static int OFFSET = 40;

    private String customer = "customer";
    private Integer numberOfVisitors = 1;
    private Date date = new Date();
    private String time = "08:00";
    private String city = "kazan";
    private String exposition = "exposition";

    public Generator(String customer, Integer numberOfVisitors, Date date, String time, String city, String exposition) {
        this.customer = customer;
        this.numberOfVisitors = numberOfVisitors;
        this.date = date;
        this.time = time;
        this.city = city;
        this.exposition = exposition;
    }


    public void createTemplate() {
        Document document = new Document();

//        Font font1 = new Font(Font.FontFamily.HELVETICA,
//                FONT_SIZE_BIG);
        Font font2 = new Font(Font.FontFamily.HELVETICA,
                FONT_SIZE_SMALL);
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("template.pdf"));

            document.open();

            Paragraph customerField = new Paragraph("Name", font2);
            customerField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(customerField);

            Paragraph amountField = new Paragraph("Number of visitors", font2);
            amountField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(amountField);

            // параграф с текстом
            Paragraph cityField = new Paragraph("City", font2);
            cityField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(cityField);

            // параграф с текстом
            Paragraph expositionField = new Paragraph("Exposition", font2);
            expositionField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(expositionField);

            // параграф с фразой, в которую добавлен чанк
            Paragraph DateField = new Paragraph("Date", font2);
            DateField.setSpacingAfter(FONT_SIZE_BIG);
            document.add(DateField);

            // параграф с текстом
            Paragraph timeField = new Paragraph("Time", font2);
            timeField.setSpacingAfter(FONT_SIZE_BIG);
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
                    new FileInputStream("template.pdf"));
            PdfStamper stamper = new PdfStamper(reader,
                    new FileOutputStream(filename));

            PdfContentByte stream = stamper.getOverContent(1);
            stream.beginText();
            stream.setColorFill(BaseColor.BLACK);

            BaseFont font = BaseFont.createFont();

            float pageWidth = reader.getPageSize(1).getWidth();
            float pageHeight = reader.getPageSize(1).getHeight();
            stream.setFontAndSize(font, FONT_SIZE_SMALL);

//        float v = stream.getEffectiveStringWidth(
//                customer, false);

//        float fitSize = (pageWidth - OFFSET * 2) * FONT_SIZE_SMALL / v;
//        stream.setFontAndSize(font, fitSize);
//        stream.setTextMatrix(OFFSET, 680);

            stream.setFontAndSize(font, FONT_SIZE_SMALL);

            float v = stream.getEffectiveStringWidth(
                    customer, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= FONT_SIZE_SMALL + 22 + FONT_SIZE_BIG);
            stream.showText(customer);


            v = stream.getEffectiveStringWidth(numberOfVisitors.toString(), false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= FONT_SIZE_SMALL + FONT_SIZE_BIG);
            stream.showText(numberOfVisitors.toString());

            v = stream.getEffectiveStringWidth(city, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= FONT_SIZE_SMALL + FONT_SIZE_BIG);
            stream.showText(city);

            v = stream.getEffectiveStringWidth(exposition, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 2 * FONT_SIZE_SMALL + FONT_SIZE_BIG);
            stream.showText(exposition);

            v = stream.getEffectiveStringWidth(
                    date.toString(), false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 2 * FONT_SIZE_SMALL + FONT_SIZE_BIG);
            stream.showText(date.toString());

            v = stream.getEffectiveStringWidth(
                    time, false);
            stream.setTextMatrix(pageWidth - v - OFFSET, pageHeight -= 2 * FONT_SIZE_SMALL + FONT_SIZE_BIG);
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

    public static void main(String[] args) {
        Generator generator = new Generator("customer", 1, new Date(), "08:00", "Kazan", "exposition");
        System.out.println(generator.fillData());
    }
}
