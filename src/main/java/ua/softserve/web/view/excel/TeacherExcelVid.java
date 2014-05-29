package ua.softserve.web.view.excel;

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
import ua.softserve.entities.Group;
import ua.softserve.entities.Mark;
import ua.softserve.entities.Student;
import ua.softserve.entities.Subject;
import ua.softserve.web.view.Vidomist_H5_03;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by troll on 13.05.14.
 */
public class TeacherExcelVid extends AbstractExcelView  implements Vidomist_H5_03{




    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook hssfWorkbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer sum =(Integer)model.get("cur_sum");
        Subject subj = (Subject)model.get("subject");
        Group grp = (Group)model.get("group");
        Map<Student,Mark> studMarkList = ((Map<Integer,Map<Student,Mark>>)model.get("stud_mark_list")).get(sum);

        HSSFSheet sheet = createSheet(hssfWorkbook,grp,subj,sum);

        HSSFCellStyle style = hssfWorkbook.createCellStyle();
            Font font = hssfWorkbook.createFont();
            font.setFontHeightInPoints((short)12);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());


        int rowNum = 3;
        int i = 1;
        for(Map.Entry<Student,Mark> entry : studMarkList.entrySet()){
            rowNum = addRow(sheet,style,rowNum,entry,i++);
        }

    }

    private int addRow(HSSFSheet sheet, HSSFCellStyle cellStyle, int rowNum, Map.Entry<Student, Mark> entry, int studNum) {
        HSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(studNum+".");
        row.createCell(1).setCellValue(entry.getKey().toString());
        row.createCell(2).setCellValue(entry.getKey().getGradebook());
        if(entry.getValue()!=null){
            row.createCell(3).setCellValue(entry.getValue().getMark());
            row.createCell(4).setCellValue(entry.getValue().getNationalScaleMark());
            row.createCell(5).setCellValue(entry.getValue().get100BaseMark());
            row.createCell(6).setCellValue(entry.getValue().getEctsMark());
            row.createCell(7).setCellValue(entry.getValue().getFormatDate());
        }
        row.createCell(8).setCellValue("");

        for(int i=0;i<3;i++){
            row.getCell(i).setCellStyle(cellStyle);
        }
        CellStyle cellStyleCenter = sheet.getWorkbook().createCellStyle();
        cellStyleCenter.cloneStyleFrom(cellStyle);
        cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);
        for(int i=3;i<9;i++){
            if(row.getCell(i)==null)
                row.createCell(i);
            row.getCell(i).setCellStyle(cellStyleCenter);

        }

        return rowNum;
    }


    private HSSFSheet createSheet(HSSFWorkbook workbook, Group grp, Subject subj, int sum) {

        HSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(grp+" : "+subj+" ("+sum+"й сим.)"));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 1, /*Column*/0, 0));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 1, /*Column*/1, 1));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 1, /*Column*/2, 2));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 0, /*Column*/3, 6));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 1, /*Column*/7, 7));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 1, /*Column*/8, 8));

        sheet.setColumnWidth(0,900);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 3500);
        sheet.setColumnWidth(3, 3500);
        sheet.setColumnWidth(4, 3500);
        sheet.setColumnWidth(5, 3500);
        sheet.setColumnWidth(6, 3500);
        sheet.setColumnWidth(7, 3500);
        sheet.setColumnWidth(8, 3500);


        Font font = workbook.createFont();
        font.setFontHeightInPoints((short)10);

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




        HSSFRow header1 = sheet.createRow(0);

        header1.createCell(0).setCellValue(TABLE_HEADERS[0]);
        header1.createCell(1).setCellValue(TABLE_HEADERS[1]);
        header1.createCell(2).setCellValue(TABLE_HEADERS[2]);
        header1.createCell(3).setCellValue(TABLE_HEADERS[3]);

        header1.createCell(7).setCellValue(TABLE_HEADERS[4]);
        header1.createCell(8).setCellValue(TABLE_HEADERS[5]);

        HSSFRow header2 = sheet.createRow(1);
        header2.createCell( 3).setCellValue(TABLE_HEADERS[6]);
        header2.createCell(4).setCellValue(TABLE_HEADERS[7]);
        header2.createCell(5).setCellValue(TABLE_HEADERS[8]);
        header2.createCell(6).setCellValue(TABLE_HEADERS[9]);


        HSSFRow indexes = sheet.createRow(2);
        for(int i=0;i<9;i++){
            Cell c = indexes.createCell(i);
            c.setCellValue(i + 1);
            c.setCellStyle(style)  ;
            if(header1.getCell(i)==null)
                header1.createCell(i);
            header1.getCell(i).setCellStyle(style);
            if(header2.getCell(i)==null)
                header2.createCell(i);
            header2.getCell(i).setCellStyle(style);
        }

        header2.setHeightInPoints(47);



        return sheet;

    }
}
