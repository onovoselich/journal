package ua.softserve.logic;

import java.util.Calendar;

/**
 * Created by troll on 14.05.14.
 */
public abstract class CalendarFormat {
    public static final String[] MONTHSES = {
            "Січня",
            "Лютого",
            "Березня",
            "Квітня",
            "Травня",
            "Червня",
            "Липня",
            "Серпня",
            "Вересня",
            "Жовтня",
            "Листопада",
            "Грудня",
    };

    public static String format(Calendar c) {
        return "\"" + c.get(c.DAY_OF_MONTH) + "\" " + MONTHSES[c.get(c.MONTH)] + " " + c.get(c.YEAR) + " року";
    }
}
