package ua.softserve.web.view.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.web.servlet.view.document.AbstractPdfView;
import ua.softserve.entities.Group;
import ua.softserve.entities.Mark;
import ua.softserve.entities.Student;
import ua.softserve.entities.Subject;
import ua.softserve.web.view.Vidomist_H5_03;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by troll on 12.05.2014.
 */
public class TeacherPdfVid extends AbstractPdfView implements Vidomist_H5_03{
    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter pdfWriter,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        Integer sum =(Integer)model.get("cur_sum");
        Subject subj = (Subject)model.get("subject");
        Group grp = (Group)model.get("group");
        Map<Student,Mark> studMarkList = (Map<Student,Mark>)model.get("stud_mark_list");

        document.setPageSize(PageSize.A4);

        Table table=new Table(9);


        Font[] fonts = {
            new Font(),
            new Font(Font.TIMES_ROMAN,10),
            new Font(Font.TIMES_ROMAN,14,Font.BOLD)


        };
//HeaderFooter header = new HeaderFooter(new Phrase("ЗАТВЕРДЖЕНО\nНаказ Міністерства освіти і науки"));
            document.add(table);
        document.setMargins(10,10,10,10);
    }
}
