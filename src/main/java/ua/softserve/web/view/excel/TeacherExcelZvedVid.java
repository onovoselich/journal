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
import ua.softserve.web.view.Vidomist_zved;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by troll on 31.05.14.
 */
public class TeacherExcelZvedVid extends AbstractExcelView implements Vidomist_zved {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {


        Integer sum =(Integer)model.get("cur_sum");
        Group grp = (Group)model.get("group");
        Map<Student,Map<String,Map<Subject,Mark>>> marksMap =(Map<Student,Map<String,Map<Subject,Mark>>> )model.get("marks_map");
        Map<String,Map<Subject,Mark>>  marksMapEx = (Map<String,Map<Subject,Mark>>)model.get("marks_map_ex");



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

        HSSFSheet sheet = createSheet(workbook,grp,marksMapEx,sum,style);

    }

    private HSSFSheet createSheet(HSSFWorkbook workbook, Group grp, Map<String,Map<Subject,Mark>> marksMapEx, Integer sum, CellStyle style) {

        HSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(grp+" ("+sum+"й сим.)"));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 2, /*Column*/0, 0));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 2, /*Column*/1, 1));
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 2, /*Column*/2, 2));
        Integer colN = 4;
        try{
            colN+=marksMapEx.get(Subject.EXAM).size();
        }catch (NullPointerException e){
            System.out.println("no exam subjects");
        }
        try{
            colN+=marksMapEx.get(Subject.DUF_ZALIK).size();
        }catch (NullPointerException e){

            System.out.println("no dufzal subjects");
        }
        try{
            colN+=marksMapEx.get(Subject.ZALIK).size();
        }catch (NullPointerException e){
            System.out.println("no zal subjects");}
        try{
            colN+=marksMapEx.get(Subject.OTHER).size();
        }catch (NullPointerException e){
            System.out.println("no other subjects");}

        sheet.addMergedRegion(new CellRangeAddress(/*Row*/0, 0, /*Column*/3, colN-1));
        int ispN,zalN,dzN;
        try {
            ispN = marksMapEx.get(Subject.EXAM).size();
        }catch (NullPointerException e){
            ispN = 0;
        }
        try{
         zalN = marksMapEx.get(Subject.ZALIK).size();
        }catch (NullPointerException e){
            zalN = 0;
        }
        try {

         dzN = marksMapEx.get(Subject.DUF_ZALIK).size();
         }catch (NullPointerException e ){
            dzN = 0;
        }
        int i = 3;
        if(ispN != 0 ){
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/1, 1, /*Column*/i, i+ispN-1));
        i=i+ispN;
        }
        if(dzN != 0){
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/1, 1, /*Column*/i, i+dzN-1));
        i=i+dzN;
        }
        if(zalN != 0){
        sheet.addMergedRegion(new CellRangeAddress(/*Row*/1, 1, /*Column*/i, i+zalN-1));
        }

        sheet.setColumnWidth(0,900);
        sheet.setColumnWidth(1, 6000);




        HSSFRow header1 = sheet.createRow(0);
        for(int j=0;j<colN;j++)
            createCell(header1,j,"",style);


            HSSFCellStyle style1 = workbook.createCellStyle();
            style1.cloneStyleFrom(style);
            style1.setRotation((short) 90);
            style1.setAlignment(CellStyle.ALIGN_LEFT);
        createCell(header1, 0, TABLE_HEADERS[0], style1);
        createCell(header1,1,TABLE_HEADERS[1],style);
        createCell(header1, 2, TABLE_HEADERS[2], style);
        createCell(header1, 3, TABLE_HEADERS[3], style);

        HSSFRow header2 = sheet.createRow(1);
        for(int j=0;j<colN;j++)
            createCell(header2,j,"",style);

        i = 3;
        if(ispN!=0){
        createCell(header2, i, F_CONTROL[0], style);
        i=i+ispN;
        }
        if(dzN!=0){
        createCell(header2, i, F_CONTROL[1], style);
        i=i+dzN;
        }if(zalN!=0){
        createCell(header2, i, F_CONTROL[2], style);
        }

        HSSFRow header3 = sheet.createRow(2);
        for(int j=0;j<colN;j++)
            createCell(header3,j,"",style);
        i=3;
        try{
        for(Subject s : marksMapEx.get(Subject.EXAM).keySet()){
            createCell(header3,i++,s.toString(),style1);
        }
        }catch (NullPointerException e){}

        try{
            for(Subject s : marksMapEx.get(Subject.DUF_ZALIK).keySet()){
                createCell(header3, i++, s.toString(), style1);
            }
        }catch (NullPointerException e){}

        try{
            for(Subject s : marksMapEx.get(Subject.ZALIK).keySet()){
                createCell(header3, i++, s.toString(), style1);
            }
        }catch (NullPointerException e){}
        try{
            for(Subject s : marksMapEx.get(Subject.OTHER).keySet()){
                createCell(header3, i++, s.toString(), style1);
            }
        }catch (NullPointerException e){}
        createCell(header3, i, AVG_MARK, style1);

        HSSFRow indexes = sheet.createRow(3);
        for(int j = 0; j<colN; j++)
            createCell(indexes, j, "", style);




        return sheet;
    }

    private Cell createCell(HSSFRow row, int i, String value, CellStyle style) {
        Cell c = row.createCell(i);
        c.setCellStyle(style);
        c.setCellValue(value);
        return c;
    }
}
