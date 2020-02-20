package com.ecarsm.preoday.mars.entity;

import java.io.Serializable;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Ecar. S. M.
 */
@Data
public class MyDate implements Serializable {

    private Date literal;
    private String clean;
    private String complete;

    public MyDate(Date date) {
        this.literal = date;
        convert();
    }

    public MyDate(String date) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            this.literal = format.parse(date);
            convert();
        } catch (ParseException ex) {
            Logger.getLogger(MyDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void convert() {
        this.clean = notNull(this.literal, new SimpleDateFormat("MMMM dd"));
        this.complete = notNull(this.literal, new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss"));
    }

    private static String notNull(Object obj, Format format) {
        if (obj != null) {
            return format.format(obj);
        } else {
            return "FORMAT ERROR!!!";
        }
    }
}
