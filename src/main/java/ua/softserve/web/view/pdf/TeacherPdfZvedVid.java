package ua.softserve.web.view.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import ua.softserve.entities.Group;
import ua.softserve.entities.Mark;
import ua.softserve.entities.Student;
import ua.softserve.entities.Subject;
import ua.softserve.web.view.Vidomist_zved;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class TeacherPdfZvedVid extends AbstractPdfView implements Vidomist_zved {

    private Integer allMarkSum = 0;
    private Integer allMarkK = 0;

    private Map<Subject, java.util.List<Integer>> subjMarksMap = new TreeMap<Subject, java.util.List<Integer>>();

    private Integer colNum = 3;

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter pdfWriter,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        Integer sum = (Integer) model.get("cur_sum");
        Group grp = (Group) model.get("group");
        Map<Student, Map<String, Map<Subject, Mark>>> marksMap = (Map<Student, Map<String, Map<Subject, Mark>>>) model.get("marks_map");
        Map<String, Map<Subject, Mark>> marksMapEx = null;
        for (Student s : marksMap.keySet()) {
            marksMapEx = marksMap.get(s);
            if (marksMapEx != null)
                break;
        }


        BaseFont times =
                BaseFont.createFont(request.getSession().getServletContext().getRealPath("/WEB-INF/times.ttf"), "cp1251", BaseFont.EMBEDDED);

        document.setPageSize(PageSize.A4);

        Paragraph p = new Paragraph(new Phrase(HEADER + "\n\"" + grp.getSpec().getViddil() + "\"\n_________" + grp.getSpec().getZavViddil(), new Font(times, 8)));
        p.setAlignment(Element.ALIGN_LEFT);
        p.setIndentationLeft(350);
        document.add(p);


        p = new Paragraph(TITLE, new Font(times, 8, Font.BOLD));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(20);
        document.add(p);

        p = new Paragraph(new Phrase(SPECIALITY + " \"" + grp.getSpec().getSpecName() + "\"", new Font(times, 8)));
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);

        String stYear = "    ";
        String fYear = "    ";
        if (grp.getStartDate() != null) stYear = grp.getStartDate().substring(0, 4);
        if (grp.getFinishDate() != null) fYear = grp.getFinishDate().substring(0, 4);
        p = new Paragraph(new Phrase(
                GROUP + " " + grp.getName() + " " + SYM[0] + ua.softserve.logic.Number.arabic2roman(sum) + SYM[1] + " " + stYear + "-" + fYear + " " + ED_YEAR
                , new Font(times, 8)));
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(20);
        document.add(p);


        PdfPTable vid;

            /*Vidomist*/
        vid = createTable(grp, marksMapEx, sum, times);
        int i = 1;
        for (Student stud : marksMap.keySet()) {
            addRow(vid, times, marksMap.get(stud), stud, i++);
        }
        createFooter(vid, times, marksMapEx);
        document.add(vid);


        Table t = new Table(2);

        Cell cell = new Cell(new Paragraph(CURATOR, new Font(times, 8)));
        cell.setBorder(Rectangle.NO_BORDER);
        t.addCell(cell);
        cell = new Cell(new Paragraph("/" + grp.getCurator() + "/", new Font(times, 8)));
        cell.setBorder(Rectangle.NO_BORDER);
        t.addCell(cell);
        t.setBorder(Table.NO_BORDER);
        t.setWidth(100);
        document.add(t);


    }

    private void createFooter(PdfPTable t, BaseFont font, Map<String, Map<Subject, Mark>> marksMapEx) throws BadElementException {
        t.addCell(createCell("", font));
        t.addCell(createCell("", font));
        PdfPCell c = createCell(1, colNum - 3, AVG_GROUP_MARK, font);
        c.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(c);
        if (allMarkK > 0) {
            float sa = (float) allMarkSum / allMarkK;
            t.addCell(createCell(String.valueOf(Math.round(sa * 100) / 100.0d), font));
        } else {
            t.addCell(createCell("", font));
        }
        t.addCell(createCell("", font));
        c = createCell(SUCCESS, font);
        c.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(c);
        t.addCell(createCell("", font));

        try {
            for (Subject subj : marksMapEx.get(Subject.EXAM).keySet()) {
                t.addCell(createCell(Mark.success(subjMarksMap.get(subj)), font));

            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                t.addCell(createCell(Mark.success(subjMarksMap.get(subj)), font));
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.ZALIK).keySet()) {
                t.addCell(createCell("-", font));
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.OTHER).keySet()) {
                t.addCell(createCell(Mark.success(subjMarksMap.get(subj)), font));
            }
            ;
        } catch (NullPointerException e) {
        }

        t.addCell(createCell("", font));
        t.addCell(createCell("", font));
        c = createCell(QUALITY, font);
        c.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(c);
        t.addCell(createCell("", font));

        try {
            for (Subject subj : marksMapEx.get(Subject.EXAM).keySet()) {
                t.addCell(createCell(Mark.quality(subjMarksMap.get(subj)), font));

            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                t.addCell(createCell(Mark.quality(subjMarksMap.get(subj)), font));
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.ZALIK).keySet()) {
                t.addCell(createCell("-", font));
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.OTHER).keySet()) {
                t.addCell(createCell(Mark.quality(subjMarksMap.get(subj)), font));
            }
            ;
        } catch (NullPointerException e) {
        }

        for (int j = 0; j < 4; j++) {
            t.addCell(createCell("", font));
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.EXAM).keySet()) {
                c = createCell(subj.getTeacher().toString(), font);
                c.setRotation(90);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(c);

            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                c = createCell(subj.getTeacher().toString(), font);
                c.setRotation(90);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(c);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.ZALIK).keySet()) {
                c = createCell(subj.getTeacher().toString(), font);
                c.setRotation(90);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(c);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.OTHER).keySet()) {
                c = createCell(subj.getTeacher().toString(), font);
                c.setRotation(90);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(c);
            }
            ;
        } catch (NullPointerException e) {
        }

        t.addCell(createCell("", font));


    }

/*
    private void createFooter(Table t, BaseFont times, Map<String,Map<Subject, Mark>> marksMapEx) {
    }
*/


    private void addRow(PdfPTable t, BaseFont font, Map<String, Map<Subject, Mark>> marksMap, Student stud, int studNum) throws BadElementException {
        t.addCell(createCell(Integer.toString(studNum), font));
        PdfPCell c = createCell(stud.getFullName(), font);
        c.setHorizontalAlignment(Element.ALIGN_LEFT);
        c.setBorderWidth(1.2f);
        t.addCell(c);
        t.addCell(createCell(stud.getEducForm(), font));


        Integer markSum = 0, markK = 0;
        try {
            for (Subject subj : marksMap.get(Subject.EXAM).keySet()) {
                Mark mark = marksMap.get(Subject.EXAM).get(subj);
                if (mark != null) {
                    markSum += mark.getMark();
                    markK++;
                    t.addCell(createCell(mark.getMark().toString(), font));
                    subjMarksMap.get(subj).add(mark.getMark());
                } else {
                    t.addCell(createCell("", font));
                }

            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMap.get(Subject.DUF_ZALIK).keySet()) {
                Mark mark = marksMap.get(Subject.DUF_ZALIK).get(subj);
                if (mark != null) {
                    markSum += mark.getMark();
                    markK++;
                    t.addCell(createCell(mark.getMark().toString(), font));
                    subjMarksMap.get(subj).add(mark.getMark());
                } else {
                    t.addCell(createCell("", font));
                }
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMap.get(Subject.ZALIK).keySet()) {
                Mark mark = marksMap.get(Subject.ZALIK).get(subj);
                if (mark != null) {
                    markSum += mark.getMark();
                    markK++;
                    t.addCell(createCell(mark.getZalMark(), font));
                    subjMarksMap.get(subj).add(mark.getMark());
                } else {
                    t.addCell(createCell("", font));
                }
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMap.get(Subject.OTHER).keySet()) {
                Mark mark = marksMap.get(Subject.OTHER).get(subj);
                if (mark != null) {
                    markSum += mark.getMark();
                    markK++;
                    t.addCell(createCell(mark.getMark().toString(), font));
                    subjMarksMap.get(subj).add(mark.getMark());
                } else {
                    t.addCell(createCell("", font));
                }
            }
            ;
        } catch (NullPointerException e) {
        }

        if (markK > 0) {
            float sa = (float) markSum / markK;
            t.addCell(createCell(String.valueOf(Math.round(sa * 100) / 100.0d), font));
        } else {
            t.addCell(createCell("", font));
        }


        this.allMarkSum += markSum;
        this.allMarkK += markK;

    }

    private PdfPTable createTable(Group grp, Map<String, Map<Subject, Mark>> marksMapEx, Integer sum, BaseFont font) throws DocumentException {
        Integer colN = 4;
        try {
            colN += marksMapEx.get(Subject.EXAM).size();
        } catch (NullPointerException e) {
            System.out.println("no exam subjects");
        }
        try {
            colN += marksMapEx.get(Subject.DUF_ZALIK).size();
        } catch (NullPointerException e) {

            System.out.println("no dufzal subjects");
        }
        try {
            colN += marksMapEx.get(Subject.ZALIK).size();
        } catch (NullPointerException e) {
            System.out.println("no zal subjects");
        }
        try {
            colN += marksMapEx.get(Subject.OTHER).size();
        } catch (NullPointerException e) {
            System.out.println("no other subjects");
        }

        int ispN, zalN, dzN;
        try {
            ispN = marksMapEx.get(Subject.EXAM).size();
        } catch (NullPointerException e) {
            ispN = 0;
        }
        try {
            zalN = marksMapEx.get(Subject.ZALIK).size();
        } catch (NullPointerException e) {
            zalN = 0;
        }
        try {

            dzN = marksMapEx.get(Subject.DUF_ZALIK).size();
        } catch (NullPointerException e) {
            dzN = 0;
        }

        PdfPTable t = new PdfPTable(colN);
        t.setWidthPercentage(100);
        int[] widths = new int[colN];
        widths[0] = 1;
        widths[1] = 14;
        for (int j = 2; j < colN; j++)
            widths[j] = 3;

        t.setWidths(widths);
        //    t.setBorder(Rectangle.NO_BORDER);

        t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        //   t.setPadding(2);

        /*header1*/


        PdfPCell c = createCell(3, 1, TABLE_HEADERS[0], font);
        c.setRotation(90);
        t.addCell(c);
        t.addCell(createCell(3, 1, TABLE_HEADERS[1], font));
        t.addCell(createCell(3, 1, TABLE_HEADERS[2], font));
        t.addCell(createCell(1, colN - 3, TABLE_HEADERS[3], font));
        if (ispN != 0)
            t.addCell(createCell(1, ispN, F_CONTROL[0], font));
        if (dzN != 0)
            t.addCell(createCell(1, dzN, F_CONTROL[1], font));
        if (zalN != 0)
            t.addCell(createCell(1, zalN, F_CONTROL[2], font));
        for (int j = 3 + ispN + dzN + zalN; j < colN; j++)
            t.addCell(createCell("", font));

        try {
            for (Subject s : marksMapEx.get(Subject.EXAM).keySet()) {
                c = createCell(s.toString(), font);
                c.setRotation(90);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(c);
                subjMarksMap.put(s, new ArrayList<Integer>());
            }
        } catch (NullPointerException e) {
        }

        try {
            for (Subject s : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                c = createCell(s.toString(), font);
                c.setRotation(90);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(c);
                subjMarksMap.put(s, new ArrayList<Integer>());
            }
        } catch (NullPointerException e) {
        }

        try {
            for (Subject s : marksMapEx.get(Subject.ZALIK).keySet()) {
                c = createCell(s.toString(), font);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                c.setRotation(90);
                t.addCell(c);
            }
        } catch (NullPointerException e) {
        }
        try {
            for (Subject s : marksMapEx.get(Subject.OTHER).keySet()) {
                c = createCell(s.toString(), font);
                c.setRotation(90);
                c.setHorizontalAlignment(Element.ALIGN_LEFT);
                t.addCell(c);
                subjMarksMap.put(s, new ArrayList<Integer>());
            }
        } catch (NullPointerException e) {
        }
        c = createCell(AVG_MARK, font);
        c.setRotation(90);
        c.setHorizontalAlignment(Element.ALIGN_LEFT);
        t.addCell(c);


        for (int i = 0; i < colN; i++)
            t.addCell(createCell(" ", font));

        this.colNum = colN;

        return t;
    }


    private static PdfPCell createCell(String value, BaseFont font) throws BadElementException {
        Phrase phr = new Paragraph(value, new Font(font, 8));
        PdfPCell c = new PdfPCell(phr);
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return c;
    }

    private static PdfPCell createCell(int rowsp, int colsp, String value, BaseFont font) throws BadElementException {
        Phrase phr = new Phrase(value, new Font(font, 8));

        PdfPCell c = new PdfPCell(phr);
        c.setRowspan(rowsp);
        c.setColspan(colsp);
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return c;
    }


}
