package sancom.ltd.sancom.utils;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class AppFunctions {

    public static Date convertToDate(String strDate) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Time convertStringToTime(String inputTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        long ms = sdf.parse(inputTime).getTime();
        return new Time(ms);
    }


    public static String convertDate(Date datToFormat) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return dateFormat.format(datToFormat);
    }

}
 