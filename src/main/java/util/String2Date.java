package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class String2Date {

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);

    }

    public String getDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return dateFormat.format(date);
    }

    public Date getDate(String strDate) {
        SimpleDateFormat textFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = textFormat.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
