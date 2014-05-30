package ua.softserve.web.view.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
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
public class TeacherPdfVid extends AbstractPdfView implements Vidomist_H5_03 {
    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter pdfWriter,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        Integer sum = (Integer) model.get("cur_sum");
        Subject subj = (Subject) model.get("subject");
        Group grp = (Group) model.get("group");
        Map<Student, Mark> studMarkList = ((Map<Integer, Map<Student, Mark>>) model.get("stud_mark_list")).get(sum);


        BaseFont times =
                BaseFont.createFont(request.getSession().getServletContext().getRealPath("/WEB-INF/times.ttf"), "cp1251", BaseFont.EMBEDDED);

        document.setPageSize(PageSize.A4);

        Paragraph p = new Paragraph(HEADER, new Font(times, 8));
        p.setAlignment(Element.ALIGN_LEFT);
        p.setIndentationLeft(390);
        document.add(p);

        p = new Paragraph(FORM, new Font(times, 8, Font.BOLD));
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);

        p = new Paragraph(ORGANIZATION_NAME, new Font(times, 14, Font.UNDERLINE));
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph(ORGANIZATION_NAME_SUB + "\n\n", new Font(times, 10));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(20);
        document.add(p);

/*            p = new Paragraph(VIDDILENNYA+" "+grp.getSpec().getViddil()+"\n" +
                    SPECIALITY+" "+grp.getSpec().getSpecName()+"\n" +
                    COURSE+" "+grp.getEducYear()+" " +
                    GROUP+" "+grp.getName()+"\n"
                    ,new Font(times,14));
            document.add(p);*/

        document.add(newParagraph(VIDDILENNYA, grp.getSpec().getViddil(), times));
        document.add(newParagraph(SPECIALITY, grp.getSpec().getSpecName(), times));
        document.add(newParagraph(COURSE, ua.softserve.logic.Number.arabic2roman(grp.getEducYear()), GROUP, grp.getName(), times));

        String stYear = "    ";
        String fYear = "    ";
        if (grp.getStartDate() != null) stYear = grp.getStartDate().substring(0, 4);
        if (grp.getFinishDate() != null) fYear = grp.getFinishDate().substring(0, 4);
        p = new Paragraph(stYear + " - " + fYear + " " + ED_YEAR, new Font(times, 14));
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);


        //       Calendar c  = Calendar.getInstance();
        p = new Paragraph(TITLE + numberGen(grp, subj), new Font(times, 14, Font.BOLD));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(30);
        document.add(p);

        p = new Paragraph("\"___\"____________________ 20__року", new Font(times, 14, Font.BOLD));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(50);
        document.add(p);


        document.add(newParagraph(SUBJECT, subj.getName(), SUBJECT_SUB, times));
        document.add(newParagraph(SYM, ua.softserve.logic.Number.arabic2roman(sum), times));

        Table t = new Table(2);
        t.setWidth(100);
        t.setBorder(Rectangle.NO_BORDER);
        Cell cell = new Cell(newParagraph(CFORM, subj.getControlForm(), CFORM_SUB, times));
        cell.setBorder(Rectangle.NO_BORDER);
        t.addCell(cell);
        String hrs = "   ";
        if (subj.getHours() != null) hrs = subj.getHours().toString();
        cell = new Cell(newParagraph(HOURS, hrs, times));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(cell);

        document.add(t);

        String teacName = "     ";
        if (subj.getTeacher() != null) teacName = subj.getTeacher().toString();
        p = (Paragraph) newParagraph(TEACHER, teacName, TEACHER_SUB, times);
        p.setSpacingBefore(20);
        document.add(p);

            /*Vidomist*/
        t = createTable(times);
        int i = 1;
        for (Map.Entry<Student, Mark> entry : studMarkList.entrySet()) {
            addRow(t, entry, times, i++, subj.getControlForm());
        }

        document.add(t);


        t = new Table(2);
        t.setWidth(100);
        t.setBorder(Table.NO_BORDER);
        cell = new Cell(newParagraph(ZAVVID, "                 ", PIDP_SUB, times));
        cell.setBorder(Rectangle.NO_BORDER);
        t.addCell(cell);
        cell = new Cell(newParagraph("", grp.getSpec().getZavViddil().toString(), NAME_SUB, times));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        t.addCell(cell);
        document.add(t);

        p = new Paragraph("Підсумки складання екзамену(заліку)", new Font(times, 14, Font.BOLD));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(30);
        document.add(p);

        Map<Integer,Integer> statistics = Mark.getStatistics(studMarkList.values());
        t = examTable(statistics,times);

        //   t.setSpacing(40);
        document.add(t);

        t = new Table(2);

        cell = new Cell(newParagraph(EXAM, "                 ", PIDP_SUB, times));
        cell.setBorder(Rectangle.NO_BORDER);
        t.addCell(cell);
        cell = new Cell(newParagraph("", "                 ", NAME_SUB, times));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(cell);
        t.setBorder(Table.NO_BORDER);
        t.setWidth(100);
        document.add(t);


    }



    private String numberGen(Group grp, Subject subj) {

        return "______";
    }

    private static Table examTable(Map<Integer, Integer> statistics, BaseFont font) throws DocumentException {
        Table t = new Table(6);
        t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        t.setPadding(2);
        t.setWidth(100);
        Cell c;

        t.setWidths(new int[]{5, 5, 4, 9, 9, 5});
        Integer[][] collspan = new Integer[9][6];
        Integer[][] rowspan = new Integer[9][6];
        collspan[0][3] = 2;
        rowspan[2][3] = 5;
        rowspan[3][4] = 2;
        rowspan[5][4] = 2;
        rowspan[0][0] = 2;
        rowspan[0][1] = 2;
        rowspan[0][2] = 2;
        rowspan[0][5] = 2;


        //cells
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 6; j++) {
                try {
                    if (TABLE2_HEADERS[i][j] != null) {
                        c = new Cell(new Phrase(TABLE2_HEADERS[i][j], new Font(font, 10)));
                        if(j==0&&i>1)
                            c = new Cell(new Phrase(statistics.get(i-2).toString(), new Font(font, 10)));
                        if (collspan[i][j] != null) c.setColspan(collspan[i][j]);
                        if (rowspan[i][j] != null) c.setRowspan(rowspan[i][j]);

                        t.addCell(c);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

        return t;
    }

    private static void addRow(Table t, Map.Entry<Student, Mark> entry, BaseFont font, int i, String controlForm) throws BadElementException {
        Cell c = new Cell(new Phrase(i + ".", new Font(font, 14)));
        c.setHorizontalAlignment(Element.ALIGN_LEFT);
        t.addCell(c);
        String s = "   ";
        if (entry.getKey() != null) s = entry.getKey().toString();
        c = new Cell(new Phrase(s, new Font(font, 14)));
        c.setHorizontalAlignment(Element.ALIGN_LEFT);
        t.addCell(c);
        c = new Cell(new Phrase(entry.getKey().getGradebook(), new Font(font, 14)));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);
        String m = " ";
        if (entry.getValue() != null) {
            if (entry.getValue().getMark() != null) m = entry.getValue().getMark().toString();
            c = new Cell(new Phrase(entry.getValue().getNationalScaleMark(controlForm), new Font(font, 14)));
            t.addCell(c);
            c = new Cell(new Phrase(m, new Font(font, 14)));
            t.addCell(c);

            c = new Cell(new Phrase(entry.getValue().getFormatDate(), new Font(font, 14)));
            t.addCell(c);
        } else {
            for (int j = 0; j < 3; j++)
                t.addCell(new Cell());
        }
        t.addCell(new Cell());
    }

    private static Table createTable(BaseFont font) throws DocumentException {
        Table t = new Table(7);
        t.setWidth(100);

        t.setWidths(new int[]{2, 10, 6, 6, 6, 6, 6});
        //    t.setBorder(Rectangle.NO_BORDER);
        t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        t.setPadding(2);
        Cell c = new Cell(new Phrase(TABLE1_HEADERS[0], new Font(font, 10)));
        c.setRowspan(2);
        t.addCell(c);
        c = new Cell(new Phrase(TABLE1_HEADERS[1], new Font(font, 10)));
        c.setRowspan(2);
        t.addCell(c);
        c = new Cell(new Phrase(TABLE1_HEADERS[2], new Font(font, 10)));
        c.setRowspan(2);
        t.addCell(c);
        c = new Cell(new Phrase(TABLE1_HEADERS[3], new Font(font, 10)));
        c.setColspan(2);
        t.addCell(c);
        c = new Cell(new Phrase(TABLE1_HEADERS[4], new Font(font, 10)));
        c.setRowspan(2);
        t.addCell(c);
        c = new Cell(new Phrase(TABLE1_HEADERS[5], new Font(font, 10)));
        c.setRowspan(2);
        t.addCell(c);
        c = new Cell(new Phrase(TABLE1_HEADERS[6], new Font(font, 10)));
        t.addCell(c);
        c = new Cell(new Phrase(TABLE1_HEADERS[7], new Font(font, 10)));
        t.addCell(c);


        for (int i = 0; i < 7; i++)
            t.addCell(new Phrase((i + 1) + "", new Font(font, 10)));


        return t;
    }


    private static Element newParagraph(String s1, String s2, BaseFont font) {

        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        Phrase f = new Phrase(s1, new Font(font, 14));
        p.add(f);
        p.add(" ");
        f = new Phrase("    " + s2 + "        ", new Font(font, 14, Font.UNDERLINE));
        p.add(f);
        p.setSpacingAfter(10);
        return p;
    }

    private static Element newParagraph(String[] s1, String s2, BaseFont font) {

        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        Phrase f = new Phrase(s1[0], new Font(font, 14));
        p.add(f);
        p.add(" ");
        f = new Phrase("    " + s2 + "        ", new Font(font, 14, Font.UNDERLINE));
        p.add(f);
        p.add(" ");
        f = new Phrase(s1[1], new Font(font, 14));
        p.add(f);
        p.setSpacingAfter(10);
        return p;
    }

    private static Element newParagraph(String s1, String s2, String sub, BaseFont font) {

        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        Phrase f = new Phrase(s1, new Font(font, 14));
        p.add(f);
        p.add(" ");
        f = new Phrase("    " + s2 + "        ", new Font(font, 14, Font.UNDERLINE));
        p.add(f);
        p.add("\n");
        f = new Phrase("", new Font(font, 10));
        for (int i = 0; i < s1.length(); i++)
            f.add("   ");
        f.add(sub);
        p.add(f);
        p.setSpacingAfter(10);
        return p;
    }

    private static Element newParagraph(String s1, String s2, String s3, String s4, BaseFont font) {

        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        Phrase f = new Phrase(s1, new Font(font, 14));
        p.add(f);
        p.add(" ");
        f = new Phrase("    " + s2 + "     ", new Font(font, 14, Font.UNDERLINE));
        p.add(f);
        p.add("   ");
        f = new Phrase(s3, new Font(font, 14));
        p.add(f);
        p.add(" ");
        f = new Phrase("    " + s4 + "     ", new Font(font, 14, Font.UNDERLINE));
        p.add(f);
        p.setSpacingAfter(10);

        return p;
    }




}
