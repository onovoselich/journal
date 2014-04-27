package ua.softserve.logic;

/**
 * Created by troll on 27.04.14.
 */
public class Number {
    public static String arabic2roman(int number){
        String roman = "";
        switch (number){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            default:
                return "";
        }
    }
}
