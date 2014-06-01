package ua.softserve.web.view;

/**
 * Created by troll on 14.05.14.
 */
public interface Vidomist_H5_03 {
    public static final String HEADER = "ЗАТВЕРДЖЕНО\nНаказ Міністерства освіти і науки, молоді та спорту України\n9 березня 2012 року №384";
    public static final String FORM = "Форма № Н-5.03";
    public static final String ORGANIZATION_NAME = "КОЛЕДЖ ЕЛЕКТРОННИХ ПРИЛАДІВ\nІвано-Франківського національного технічного університету нафти і газу";
    public static final String ORGANIZATION_NAME_SUB = "(повне найменування вищого навчального закладу)";
    public static final String VIDDILENNYA = "Відділення";
    public static final String SPECIALITY = "Спеціальність";
    public static final String COURSE = "Курс";
    public static final String GROUP = "Група";
    public static final String ED_YEAR = "навчальний рік";
    public static final String TITLE = "ВІДОМІСТЬ ОБЛІКУ УСПІШНОСТІ № ";
    public static final String SUBJECT = "з дисципліни";
    public static final String SUBJECT_SUB = "(назва навчальної дисципліни)";
    public static final String[] SYM = {"За ", " навчальний семестр."};
    public static final String CFORM = "Форма контролю";
    public static final String CFORM_SUB = "(екзамен,залік)";
    public static final String HOURS = "Загальна кількість годин";
    public static final String TEACHER = "Викладач";
    public static final String TEACHER_SUB = "(вчене звання, прізвище та ініціали викладача, який виставляє підсумкову оцінку)";
    public static final String ZAVVID = "Завідувач відділення";
    public static final String PIDP_SUB = "(підпис)";
    public static final String NAME_SUB = "(прізвище та ініціали)";
    public static final String EXAM = "Екзаменатор(викладач)";

    public static final String[] TABLE1_HEADERS = {
            "№ з/п",
            "Прізвище та ініціали студента",
            "№ залікової книжки",
            "Оцінка",
            "Дата",
            "Підпис викладача",
            "за національною шкалою",
            "кількість балів за 12 бальною шкалою"


    };

    public static final String[][] TABLE2_HEADERS = {
            {"ВСЬОГО ОЦІНОК", "СУМА БАЛІВ", "ОЦІНКА ECTS", "ОЦІНКА ЗА НАЦІОНАЛЬНОЮ ШКАЛОЮ", null, "ОЦІНКА ЗА 12 БАЛЬНОЮ ШКАЛОЮ"},
            {null, null, null, "для заліку", "для екзамену, диференційованого заліку, курсового проекту(роботи), практики"},
            {"", "90-100", "A", "зараховано", "5(відмінно)", "10-12 балів"},
            {"", "82-89", "B", null, "4(добре)", "8-9 балів"},
            {"", "74-81", "C", null, null, "7 балів"},
            {"", "64-73", "D", null, "3(задовільно)", "5-6 балів"},
            {"", "60-63", "E", null, null, "4 бали"},
            {"", "35-59", "FX", "не зараховано з можливістю повторного складання", "незадовільно з можливістю повторного склаання", "2-3 бали"},
            {"", "0-34", "F", "не зараховано з обов'язковим повторним вивченням дисципліни", "незадовільно з обов'язковим повторним вивченням дисципліни", "1 бал"},
    };
}
