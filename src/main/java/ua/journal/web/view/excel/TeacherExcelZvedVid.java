package ua.journal.web.view.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import ua.journal.entities.Group;
import ua.journal.entities.Mark;
import ua.journal.entities.Student;
import ua.journal.entities.Subject;
import ua.journal.web.view.Vidomist_zved;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by troll on 31.05.14.
 */
public class TeacherExcelZvedVid extends AbstractExcelView implements Vidomist_zved {
    private Integer allMarkSum = 0;
    private Integer allMarkK = 0;

    private Map<Subject, List<Integer>> subjMarksMap = new TreeMap<Subject, List<Integer>>();

    private Integer colNum = 3;

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {


        Integer sum = (Integer) model.get("cur_sum");
        Group grp = (Group) model.get("group");
        Map<Student, Map<String, Map<Subject, Mark>>> marksMap = (Map<Student, Map<String, Map<Subject, Mark>>>) model.get("marks_map");
        Map<String, Map<Subject, Mark>> marksMapEx = null;
        for (Student s : marksMap.keySet()) {
            marksMapEx = marksMap.get(s);
            if (marksMapEx != null)
                break;
        }


        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);

        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        HSSFSheet sheet = createSheet(workbook, grp, marksMapEx, sum, style);

        int rowNum = 3;
        int i = 1;
        for (Student stud : marksMap.keySet()) {
            rowNum = addRow(sheet, style, rowNum, marksMap.get(stud), stud, i++);
        }

        createFooter(sheet, style, rowNum, marksMapEx);

    }

    private void createFooter(HSSFSheet sheet, CellStyle style, int rowNum, Map<String, Map<Subject, Mark>> marksMapEx) {
        HSSFRow footer1 = sheet.createRow(rowNum);
        for (int j = 0; j < colNum; j++)
            createCell(footer1, j, "", style);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 2, colNum - 2));
        CellStyle style3 = sheet.getWorkbook().createCellStyle();
        style3.cloneStyleFrom(style);
        style3.setAlignment(CellStyle.ALIGN_RIGHT);
        createCell(footer1, 2, AVG_GROUP_MARK, style3);

        if (allMarkK > 0) {
            float sa = (float) allMarkSum / allMarkK;
            createCell(footer1, rowNum - 1, String.valueOf(Math.round(sa * 100) / 100.0d), style);
        }

        HSSFRow footer2 = sheet.createRow(++rowNum);
        for (int j = 0; j < colNum; j++)
            createCell(footer2, j, "", style);
        createCell(footer2, 1, SUCCESS, style3);
        int i = 3;
        try {
            for (Subject subj : marksMapEx.get(Subject.EXAM).keySet()) {
                createCell(footer2, i++, Mark.success(subjMarksMap.get(subj)), style);

            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                createCell(footer2, i++, Mark.success(subjMarksMap.get(subj)), style);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.ZALIK).keySet()) {
                createCell(footer2, i++, "-", style);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.OTHER).keySet()) {
                createCell(footer2, i++, Mark.success(subjMarksMap.get(subj)), style);
            }
            ;
        } catch (NullPointerException e) {
        }

        HSSFRow footer3 = sheet.createRow(++rowNum);
        for (int j = 0; j < colNum; j++)
            createCell(footer3, j, "", style);
        createCell(footer3, 1, QUALITY, style3);
        i = 3;
        try {
            for (Subject subj : marksMapEx.get(Subject.EXAM).keySet()) {
                createCell(footer3, i++, Mark.quality(subjMarksMap.get(subj)), style);

            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                createCell(footer3, i++, Mark.quality(subjMarksMap.get(subj)), style);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.ZALIK).keySet()) {
                createCell(footer3, i++, "-", style);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.OTHER).keySet()) {
                createCell(footer3, i++, Mark.quality(subjMarksMap.get(subj)), style);
            }
            ;
        } catch (NullPointerException e) {
        }


        HSSFRow footer4 = sheet.createRow(++rowNum);
        CellStyle style4 = sheet.getWorkbook().createCellStyle();
        style4.cloneStyleFrom(style);
        style4.setRotation((short) 90);
        style4.setAlignment(CellStyle.ALIGN_LEFT);
        for (int j = 0; j < colNum; j++)
            createCell(footer4, j, "", style);
        i = 3;
        try {
            for (Subject subj : marksMapEx.get(Subject.EXAM).keySet()) {
                createCell(footer4, i++, subj.getTeacher().toString(), style4);

            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                createCell(footer4, i++, subj.getTeacher().toString(), style4);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.ZALIK).keySet()) {
                createCell(footer4, i++, subj.getTeacher().toString(), style4);
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMapEx.get(Subject.OTHER).keySet()) {
                createCell(footer4, i++, subj.getTeacher().toString(), style4);
            }
            ;
        } catch (NullPointerException e) {
        }

    }


    private int addRow(HSSFSheet sheet, CellStyle style, int rowNum, Map<String, Map<Subject, Mark>> marksMap, Student stud, Integer studNum) {
        HSSFRow row = sheet.createRow(rowNum++);

        for (int j = 0; j < colNum; j++)
            createCell(row, j, "", style);
        createCell(row, 0, studNum.toString(), style);
        CellStyle style2 = sheet.getWorkbook().createCellStyle();
        style2.cloneStyleFrom(style);
        style2.setAlignment(CellStyle.ALIGN_LEFT);
        style2.setBorderBottom(CellStyle.BORDER_MEDIUM);
        style2.setBorderLeft(CellStyle.BORDER_MEDIUM);
        style2.setBorderRight(CellStyle.BORDER_MEDIUM);
        style2.setBorderTop(CellStyle.BORDER_MEDIUM);
        createCell(row, 1, stud.getFullName(), style2);
        createCell(row, 2, stud.getEducForm(), style);
        Integer i = 3;
        Integer markSum = 0, markK = 0;
        try {
            for (Subject subj : marksMap.get(Subject.EXAM).keySet()) {
                Mark mark = marksMap.get(Subject.EXAM).get(subj);
                if (mark != null) {
                    markSum += mark.getMark();
                    markK++;
                    createCell(row, i++, mark.getMark().toString(), style);
                    subjMarksMap.get(subj).add(mark.getMark());
                } else {
                    createCell(row, i++, "", style);
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
                    createCell(row, i++, mark.getMark().toString(), style);
                    subjMarksMap.get(subj).add(mark.getMark());
                } else {
                    createCell(row, i++, "", style);
                }
            }
            ;
        } catch (NullPointerException e) {
        }
        try {
            for (Subject subj : marksMap.get(Subject.ZALIK).keySet()) {
                Mark mark = marksMap.get(Subject.ZALIK).get(subj);

                if (mark != null) {
                    createCell(row, i++, mark.getZalMark(), style);
                } else {
                    createCell(row, i++, "", style);
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
                    createCell(row, i++, mark.getMark().toString(), style);
                    subjMarksMap.get(subj).add(mark.getMark());
                } else {
                    createCell(row, i++, "", style);
                }
            }
            ;
        } catch (NullPointerException e) {
        }

        if (markK > 0) {
            float sa = (float) markSum / markK;
            createCell(row, i, String.valueOf(Math.round(sa * 100) / 100.0d), style);
        }


        this.allMarkSum += markSum;
        this.allMarkK += markK;

        return rowNum;
    }

    private HSSFSheet createSheet(HSSFWorkbook workbook, Group grp, Map<String, Map<Subject, Mark>> marksMapEx, Integer sum, CellStyle style) {

        HSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(grp + " (" + sum + "й сим.)"));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 2, /*Column*/0, 0));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 2, /*Column*/1, 1));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 2, /*Column*/2, 2));
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

        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 0, /*Column*/3, colN - 1));
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
        int i = 3;
        if (ispN != 0) {
            sheet.addMergedRegion(new CellRangeAddress(/*Row*/1, 1, /*Column*/i, i + ispN - 1));
            i = i + ispN;
        }
        if (dzN != 0) {
            sheet.addMergedRegion(new CellRangeAddress(/*Row*/1, 1, /*Column*/i, i + dzN - 1));
            i = i + dzN;
        }
        if (zalN != 0) {
            sheet.addMergedRegion(new CellRangeAddress(/*Row*/1, 1, /*Column*/i, i + zalN - 1));
        }

        sheet.setColumnWidth(0, 900);
        sheet.setColumnWidth(1, 6000);


        HSSFRow header1 = sheet.createRow(0);
        for (int j = 0; j < colN; j++)
            createCell(header1, j, "", style);


        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.cloneStyleFrom(style);
        style1.setRotation((short) 90);
        style1.setAlignment(CellStyle.ALIGN_LEFT);
        createCell(header1, 0, TABLE_HEADERS[0], style1);
        createCell(header1, 1, TABLE_HEADERS[1], style);
        createCell(header1, 2, TABLE_HEADERS[2], style);
        createCell(header1, 3, TABLE_HEADERS[3], style);

        HSSFRow header2 = sheet.createRow(1);
        for (int j = 0; j < colN; j++)
            createCell(header2, j, "", style);

        i = 3;
        if (ispN != 0) {
            createCell(header2, i, F_CONTROL[0], style);
            i = i + ispN;
        }
        if (dzN != 0) {
            createCell(header2, i, F_CONTROL[1], style);
            i = i + dzN;
        }
        if (zalN != 0) {
            createCell(header2, i, F_CONTROL[2], style);
        }

        HSSFRow header3 = sheet.createRow(2);
        for (int j = 0; j < colN; j++)
            createCell(header3, j, "", style);
        i = 3;
        try {
            for (Subject s : marksMapEx.get(Subject.EXAM).keySet()) {
                createCell(header3, i++, s.toString(), style1);
                subjMarksMap.put(s, new ArrayList<Integer>());
            }
        } catch (NullPointerException e) {
        }

        try {
            for (Subject s : marksMapEx.get(Subject.DUF_ZALIK).keySet()) {
                createCell(header3, i++, s.toString(), style1);
                subjMarksMap.put(s, new ArrayList<Integer>());
            }
        } catch (NullPointerException e) {
        }

        try {
            for (Subject s : marksMapEx.get(Subject.ZALIK).keySet()) {
                createCell(header3, i++, s.toString(), style1);
            }
        } catch (NullPointerException e) {
        }
        try {
            for (Subject s : marksMapEx.get(Subject.OTHER).keySet()) {
                createCell(header3, i++, s.toString(), style1);
                subjMarksMap.put(s, new ArrayList<Integer>());
            }
        } catch (NullPointerException e) {
        }
        createCell(header3, i, AVG_MARK, style1);

        HSSFRow indexes = sheet.createRow(3);
        for (int j = 0; j < colN; j++)
            createCell(indexes, j, "", style);


        this.colNum = colN;

        return sheet;
    }

    private Cell createCell(HSSFRow row, int i, String value, CellStyle style) {
        Cell c = row.createCell(i);
        c.setCellStyle(style);
        c.setCellValue(value);
        return c;
    }
}
